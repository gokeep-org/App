<!DOCTYPE>
<html>
<head>
    <title>测试HL7平台</title>
    <meta charset="UTF-8"/>
    <script type="application/javascript" src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <script type="application/javascript">
        var globalMessageName = "ADT_A01";
        var intervals = [];
        var logNames = ["ADT_A01", "V231"];
        var host = "http://localhost";
        //var host = "http://118.89.240.60";
        // var port = 10001;
        // var url = host + ":" + port.toString();
        $(document).ready(function () {
            /**
             * 通过消息名称显示输出
             **/
            showLogByName("");
            // getNtpServerTime();

            function getNtpServerTime() {
                $.ajax({
                    // ADT_A01，
                    url: "/hl7/ct",
                    method: "POST",
                    success: function (result) {
                        $("#ntp_server_time").text(result['res']);
                    },
                    error: function (result) {
                        console.log("send get ntp server is fail");
                    }
                });
            }
            function showLogByName(name) {
                globalMessageName = name;
                var interval = setInterval(flushResult, 2000);
                intervals.push(interval);


                function flushResult() {
                    $('#main_div').scrollTop($('#main_div')[0].scrollHeight);
                    $.ajax({
                        // ADT_A01，
                        url: "/hl7/flush",
                        method: "POST",
                        success: function (result) {
                            for (var key in result) {
                                if (key == 0){
                                    continue;
                                }
                                var id = "#" + key;
                                if (result[key] != "no_value") {
                                    var time = new Date().toLocaleString(Number(key)).replace(/\//g, "-").replace("上午", "").replace("下午", "")
                                    $("#main_div").append("<li id='" + key + "' style='float: left;'>" + time + ": " + result[key] + "</li><br/>");
                                }
                            }
                        },
                        error: function (result) {
                            console.log("send flush result is fail");
                        }
                    });
                }
            }

            /**
             * 清空容器内的输出
             */
            function cleanResult() {
                $.ajax({
                    url: "/hl7/clean",
                    method: "DELETE",
                    success: function (result) {
                        console.log("clean result is success");
                        $("#main_div").empty();
                        $("#main_div").text(result['res'])

                    },
                    error: function (result) {
                        console.log("clean result is fail");
                    }
                });
            }

            /**
             * 根据消息名称发送
             * 服务端会是实现socket请求Hl7 server端
             * @param name
             */
            function sendMessageByName(name) {
                $.ajax({
                    url: "/hl7/send/" + name.toUpperCase(),
                    method: "POST",
                    success: function (result) {
                        console.log("send message is success");
                        $("#main_div").empty();
                        showLogByName(name);
                    },
                    error: function (result) {
                        console.log("send message is fail");
                    }
                });
            }

            function sendMessageByAddress(host, port,  message) {
                $.ajax({
                    data: JSON.stringify({"host": host, "port": port, "message": message}),
                    url: "/hl7/proxy",
                    type: "POST",
                    dataType: "json",
                    headers: {'Content-Type': 'application/json'},
                    success: function (result) {
                        console.log("send message by address is success");
                        stopAllInterval();
                        $("#main_div").append(result['res'] + "<br/>");
                    },
                    error: function (result) {
                        console.log("send message is fail");
                    }
                });
            }

            /**
             * 监听所有的消息输出
             */
            function startAllListenSocket() {
                for (var i = 0; i < logNames.length; i++) {
                    showLogByName(logNames[i]);
                }
            }

            /**
             * 停止监听所有的消息输出
             */
            function stopAllInterval() {
                for (var i = intervals.length - 1; i >= 0; i--) {
                    clearInterval(intervals[i]);
                }
            }

            function intervalSendMessage() {
                sendMessageByName("V231");
            }

            function startNewListen() {
                stopAllInterval();
                startAllListenSocket();
            }

            $("#STOP_LISTEN_BTN").click(function () {
                stopAllInterval();
            });

            $("#CLEAN_RESULT_BTN").click(function () {
                $("#main_div").text("");
            });

            $("#SEND_ADT01_BTN").click(function () {
                startNewListen();
                sendMessageByName("ADT_A01");
            });

            $("#SEND_V232_BTN").click(function () {
                startNewListen();
                sendMessageByName("V231");
            });

            $("#INTERVAL_SEND_BTN").click(function () {
                var interval_send = setInterval(intervalSendMessage, 5000);
                intervals.push(interval_send);
            });

            $("#SEND_INPUT_MESSAGE_BTN").click(function () {
                var targetHost = $("input[name='host']").val();
                var targetPort = $("input[name='port']").val();
                var message = $("textarea[name='message']").val();
                sendMessageByAddress(targetHost, targetPort, message);
            });

            $("#TEMPLATE_BTN").click(function () {
                $("textarea[name='message']").text(
                    "MSH|^~\\&|PAMSimulator|IHE|hinacom-pix|hinacom|20130424103105||ADT^A01^ADT_A01|20130424103105|P|2.5||||||8859/1[CR]\n" +
                        "EVN||20130424103105||||20130424103105[CR]\n" +
                        "PID|||DDS-43201^^^DDS&1.3.6.1.4.1.12559.11.1.4.1.2&ISO^PI||Bauer^Lothar^^^^^L|Schneider^^^^^^M|19951113050908|M|||Kirchenplatz^^Simbach a.Inn^^84359^AUT|||||||2436^^^IHEPAM&1.3.6.1.4.1.12559.11.1.2.2.5&ISO^AN|||||||||||||N[CR]\n" +
                        "PV1|||||||||||||||||||2437^^^IHEPAM&1.3.6.1.4.1.12559.11.1.2.2.5&ISO^VN|||||||||||||||||||||||||20130424103100|||||||V[CR"
                );
            });

            $("#FLUSH_NTP_TIME_BTN").click(function () {
                getNtpServerTime();
            });

        });
    </script>
</head>
<body>
<button id="STOP_LISTEN_BTN">停止监听消息</button>
<button id="SEND_V232_BTN">发送V232类型消息</button>
<button id="SEND_ADT01_BTN">发送ADT_01类型消息</button>
<button id="CLEAN_RESULT_BTN">清空控制台</button>
<button id="INTERVAL_SEND_BTN">定时发送/5s</button>
<button id="FLUSH_NTP_TIME_BTN">ntp server time<span id="ntp_server_time"></span></button>
<hr/>


<#--<center>-->
<div id="main_div"
     style="border:1px solid #000; width: 800px; height: 400px; background-color: aliceblue; overflow:auto;">
</div>
<br/>
<label>目标主机(TargeHost)：</label>
<input type="text" name="host" value="localhost">
<label>目标端口(TargePort)：</label>
<input type="text" name="port" value="9030"><br>
<#--<label>应用名称(ReceivingApplication)：</label>-->
<#--<input type="text" name="app_name" value=""><br>-->
<#--<label>设备名称(ReceivingFacility)：</label>-->
<#--<input type="text" name="facility_name" value=""><br>-->
<#--<label>Domain: </label>-->
<#--<input type="text" name="domain" value=""><br>-->


<label> 消息（Message）：</label><button id="TEMPLATE_BTN">模板</button><br/>
<textarea name="message" style="resize: none; width:  400px; height: 250px;"></textarea>
<button id="SEND_INPUT_MESSAGE_BTN">发送请求</button>
<#--</center>-->
</body>
</html>