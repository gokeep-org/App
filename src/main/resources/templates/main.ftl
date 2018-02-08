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
        $("document").ready(function () {
            /**
             * 通过消息名称显示输出
             **/
            function showLogByName(name) {
                globalMessageName = name;
                var interval = setInterval(flushResult, 2000);
                intervals.push(interval);

                function flushResult() {
                    $('#main_div').scrollTop($('#main_div')[0].scrollHeight);
                    $.ajax({
                        // ADT_A01，
                        url: "http://localhost:10001/hl7/flush?name=" + globalMessageName,
                        method: "POST",
                        success: function (result) {
                            for (var key in result) {
                                var id = "#" + key;
                                if ($(id).length <= 0) {
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
                    url: "http://localhost:10001/hl7/clean",
                    method: "DELETE",
                    success: function (result) {
                        console.log("clean result is success");
                        $("#main_div").empty();
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
                    url: "http://localhost:10001/hl7/send/" + name.toUpperCase(),
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

            /**
             * 监听所有的消息输出
             */
            function startAllListenSocket() {
                for (var i = 0; i < logNames.length; i++) {
                    showLogByName(logNames[i])
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
            $("#VIEW_RESULT_BTN").click(function () {
                startAllListenSocket();
            });
            $("#CLEAN_RESULT_BTN").click(function () {
                stopAllInterval();
                cleanResult();
            });
            $("#SEND_ADT01_BTN").click(function () {
                startAllListenSocket();
                sendMessageByName("ADT_A01");
            });
            $("#SEND_V232_BTN").click(function () {
                stopAllInterval();
                sendMessageByName("V231");
            });
        });
    </script>
</head>
    <body>
        <button id="VIEW_RESULT_BTN">开始监听</button>
        <button id="SEND_V232_BTN">发送V232类型消息</button>
        <button id="SEND_ADT01_BTN">发送ADT_01类型消息</button>
        <button id="CLEAN_RESULT_BTN">清空控制台</button>
        <hr/>
        <#--<center>-->
        <div id="main_div"
             style="border:1px solid #000; width: 800px; height: 400px; background-color: aliceblue; overflow:auto;">
        </div>
        <#--</center>-->
    </body>
</html>