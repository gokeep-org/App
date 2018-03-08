<!doctype html>
<html lang="en" xmlns="http://java.sun.com/jsf/core">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Hl7 Message Client</title>
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
            // /**
            //  * 通过消息名称显示输出
            //  **/
            // showLogByName("");
            // getNtpServerTime();
            //
            // function getNtpServerTime() {
            //     $.ajax({
            //         // ADT_A01，
            //         url: "/hl7/ct",
            //         method: "POST",
            //         success: function (result) {
            //             $("#ntp_server_time").text(result['res']);
            //         },
            //         error: function (result) {
            //             console.log("send get ntp server is fail");
            //         }
            //     });
            // }
            // function showLogByName(name) {
            //     globalMessageName = name;
            //     var interval = setInterval(flushResult, 2000);
            //     intervals.push(interval);
            //
            //
            //     function flushResult() {
            //         $('#main_div').scrollTop($('#main_div')[0].scrollHeight);
            //         $.ajax({
            //             // ADT_A01，
            //             url: "/hl7/flush",
            //             method: "POST",
            //             success: function (result) {
            //                 for (var key in result) {
            //                     if (key == 0){
            //                         continue;
            //                     }
            //                     var id = "#" + key;
            //                     if (result[key] != "no_value") {
            //                         var time = new Date().toLocaleString(Number(key)).replace(/\//g, "-").replace("上午", "").replace("下午", "")
            //                         $("#main_div").append("<li id='" + key + "' style='float: left;'>" + time + ": " + result[key] + "</li><br/>");
            //                     }
            //                 }
            //             },
            //             error: function (result) {
            //                 console.log("send flush result is fail");
            //             }
            //         });
            //     }
            // }
            //
            // /**
            //  * 清空容器内的输出
            //  */
            // function cleanResult() {
            //     $.ajax({
            //         url: "/hl7/clean",
            //         method: "DELETE",
            //         success: function (result) {
            //             console.log("clean result is success");
            //             $("#main_div").empty();
            //             $("#main_div").text(result['res'])
            //
            //         },
            //         error: function (result) {
            //             console.log("clean result is fail");
            //         }
            //     });
            // }
            //
            // /**
            //  * 根据消息名称发送
            //  * 服务端会是实现socket请求Hl7 server端
            //  * @param name
            //  */
            // function sendMessageByName(name) {
            //     $.ajax({
            //         url: "/hl7/send/" + name.toUpperCase(),
            //         method: "POST",
            //         success: function (result) {
            //             console.log("send message is success");
            //             $("#main_div").empty();
            //             showLogByName(name);
            //         },
            //         error: function (result) {
            //             console.log("send message is fail");
            //         }
            //     });
            // }
            //
            // function sendMessageByAddress(host, port,  message) {
            //     $.ajax({
            //         data: JSON.stringify({"host": host, "port": port, "message": message}),
            //         url: "/hl7/proxy",
            //         type: "POST",
            //         dataType: "json",
            //         headers: {'Content-Type': 'application/json'},
            //         success: function (result) {
            //             console.log("send message by address is success");
            //             stopAllInterval();
            //             $("#main_div").append(result['res'] + "<br/>");
            //         },
            //         error: function (result) {
            //             console.log("send message is fail");
            //         }
            //     });
            // }
            //
            // /**
            //  * 监听所有的消息输出
            //  */
            // function startAllListenSocket() {
            //     for (var i = 0; i < logNames.length; i++) {
            //         showLogByName(logNames[i]);
            //     }
            // }
            //
            // /**
            //  * 停止监听所有的消息输出
            //  */
            // function stopAllInterval() {
            //     for (var i = intervals.length - 1; i >= 0; i--) {
            //         clearInterval(intervals[i]);
            //     }
            // }
            //
            // function intervalSendMessage() {
            //     sendMessageByName("V231");
            // }
            //
            // function startNewListen() {
            //     stopAllInterval();
            //     startAllListenSocket();
            // }
            //
            // $("#STOP_LISTEN_BTN").click(function () {
            //     stopAllInterval();
            // });
            //
            // $("#CLEAN_RESULT_BTN").click(function () {
            //     $("#main_div").text("");
            // });
            //
            // $("#SEND_ADT01_BTN").click(function () {
            //     startNewListen();
            //     sendMessageByName("ADT_A01");
            // });
            //
            // $("#SEND_V232_BTN").click(function () {
            //     startNewListen();
            //     sendMessageByName("V231");
            // });
            //
            // $("#INTERVAL_SEND_BTN").click(function () {
            //     var interval_send = setInterval(intervalSendMessage, 5000);
            //     intervals.push(interval_send);
            // });
            //
            // $("#SEND_INPUT_MESSAGE_BTN").click(function () {
            //     var targetHost = $("input[name='host']").val();
            //     var targetPort = $("input[name='port']").val();
            //     var message = $("textarea[name='message']").val();
            //     sendMessageByAddress(targetHost, targetPort, message);
            // });
            //
            // $("#TEMPLATE_BTN").click(function () {
            //     $("textarea[name='message']").text(
            //         "MSH|^~\\&|PAMSimulator|IHE|hinacom-pix|hinacom|20130424103105||ADT^A01^ADT_A01|20130424103105|P|2.5||||||8859/1[CR]\n" +
            //         "EVN||20130424103105||||20130424103105[CR]\n" +
            //         "PID|||DDS-43201^^^DDS&1.3.6.1.4.1.12559.11.1.4.1.2&ISO^PI||Bauer^Lothar^^^^^L|Schneider^^^^^^M|19951113050908|M|||Kirchenplatz^^Simbach a.Inn^^84359^AUT|||||||2436^^^IHEPAM&1.3.6.1.4.1.12559.11.1.2.2.5&ISO^AN|||||||||||||N[CR]\n" +
            //         "PV1|||||||||||||||||||2437^^^IHEPAM&1.3.6.1.4.1.12559.11.1.2.2.5&ISO^VN|||||||||||||||||||||||||20130424103100|||||||V[CR"
            //     );
            // });
            //
            // $("#FLUSH_NTP_TIME_BTN").click(function () {
            //     getNtpServerTime();
            // });

            $('#requestType').change(function(){
                if ($(this).val() == "SUMMARY"){
                    $("#url").val("http://202.105.136.186:7016/source/IHERetrieveSummaryInfo");
                    $("#patientID_div").show();
                    $("#lowerDateTime_div").show();
                    $("#upperDateTime_div").show();
                    $("#mostRecentResults_div").show();
                    $("#documentUID_div").hide();
                    $("#preferredContentType_div").hide();
                }else {
                    $("#url").val("http://202.105.136.186:7016/source/IHERetrieveDocument");
                    $("#patientID_div").hide();
                    $("#lowerDateTime_div").hide();
                    $("#upperDateTime_div").hide();
                    $("#mostRecentResults_div").hide();
                    $("#documentUID_div").show();
                    $("#preferredContentType_div").show();
                }
            });


            $("#send").click(function () {
                var type = $("#requestType").val();
                var url = $("#url").val();
                if (type == "SUMMARY"){
                    url += "?requestType=SUMMARY";
                    url += "&patientID=" + $("#patientID").val();
                    url += "&lowerDateTime=" + $("#lowerDateTime").val();
                    url += "&upperDateTime=" + $("#upperDateTime").val();
                    url += "&mostRecentResults=" + $("#mostRecentResults").val();

                }else if (type == "DOCUMENT"){
                    url += "?requestType=DOCUMENT";
                    url += "&documentUID=" + $("#documentUID").val();
                    url += "&preferredContentType=" + $("#preferredContentType").val();

                }
                if ($("custom_params").length > 0){
                    url += "&"+$("#custom_params").val();
                }
                window.open(url);
            });

            function sendMessageByAddress(host, port,  type) {
                $.ajax({
                    data: JSON.stringify({"host": host, "port": port, "message": message}),
                    url: "/hl7/send",
                    type: "POST",
                    dataType: "json",
                    headers: {'Content-Type': 'application/json'},
                    success: function (result) {
                        console.log("send message by address is success");
                        $("#message_output").append("input:  "+result['input'] + "<br/>");
                        $("#message_output").append("output:  "+result['output'] + "<br/>");
                    },
                    error: function (result) {
                        console.log("send message is fail");
                    }
                });
            }
        });
    </script>
</head>
<body>
<div class="row">
    <div class="col-md-6">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-10 control-label">请求url</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="url" placeholder="url" value="http://202.105.136.186:7016/source/IHERetrieveSummaryInfo">
                </div>
            </div>
            <div class="form-group">
                <label  class="col-sm-6 control-label">请求类型</label>
                <select class="col-sm-6" id="requestType">
                    <option selected="selected">SUMMARY</option>
                    <option>DOCUMENT</option>
                </select>
            </div>

            <div class="form-group" id="patientID_div">
                <label  class="col-sm-6 control-label">patientID</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="patientID" placeholder="" value="1">
                </div>
            </div>
            <div class="form-group" style="display: none" id="documentUID_div">
                <label  class="col-sm-6 control-label">documentUID</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="documentUID" placeholder="" value="111">
                </div>
            </div>
            <div class="form-group" id="lowerDateTime_div">
                <label  class="col-sm-6 control-label">lowerDateTime</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="lowerDateTime" placeholder="" value="2018-01-08T00:00:00">
                </div>
            </div>
            <div class="form-group" id="upperDateTime_div">
                <label class="col-sm-6 control-label">upperDateTime</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="upperDateTime" placeholder="" value="2018-03-08T00:00:00">
                </div>
            </div>
            <div class="form-group" id="mostRecentResults_div">
                <label class="col-sm-6 control-label">mostRecentResults</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="mostRecentResults" placeholder="" value="0">
                </div>
            </div>
            <div class="form-group" style="display: none" id="preferredContentType_div">
                <label class="col-sm-6 control-label">preferredContentType</label>
                <select class="col-sm-6" id="preferredContentType">
                    <option selected="selected">application/jpg</option>
                    <option>application/pdf</option>
                    <option>application/error</option>
                </select>
                <!--<div class="col-sm-6">-->
                    <!--<input type="text" class="form-control" id="preferredContentType" placeholder="" value="application/jpg">-->
                <!--</div>-->
            </div>
        </form>
    </div>
    <div class="col-md-6">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-6 control-label">自定义参数</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="custom_params" placeholder="默认为null">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6 col-sm-12">
                    <button type="button" class="btn btn-success" id="send">发送请求</button>
                </div>
            </div>
            <!--<table class="table table-condensed">-->
                <!--<tr>-->
                    <!--<th>本地时间信息</th>-->
                    <!--<th>服务器时间信息</th>-->
                <!--</tr>-->
                <!--<tr>-->
                <!--<tr>-->
                    <!--<td>TIME:</td>-->
                    <!--<td>2017-03-03 21:21:12</td>-->
                <!--</tr>-->
                <!--<tr>-->
                    <!--<td>TIME:</td>-->
                    <!--<td>2017-03-03 21:21:12</td>-->
                <!--</tr>-->
                <!--</tr>-->
            <!--</table>-->
        </form>

    </div>

</div>
<!--<label>自定义消息：</label>-->
<!--<div class="jumbotron" id="custom_message_input">-->
    <!--&lt;!&ndash;<p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>&ndash;&gt;-->
<!--</div>-->
<!--<label>消息面板：</label>-->
<!--<div class="jumbotron" id="message_output">-->
    <!--&lt;!&ndash;<p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>&ndash;&gt;-->
<!--</div>-->
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>