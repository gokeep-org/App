<!DOCTYPE>
<html>
<head>
    <title>测试HL7平台</title>
    <meta charset="UTF-8"/>
    <script type="application/javascript" src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <script type="application/javascript">
        var globalMessageName = "ADT_A01";
        $("document").ready(function () {
            function showLogByName(name) {
                globalMessageName = name;
                var interval = setInterval(flushResult, 2000);
                function flushResult() {
                    $('#main_div').scrollTop($('#main_div')[0].scrollHeight);
                    $.ajax({
                        // ADT_A01，
                        url: "http://localhost:10001/hl7/flush?name="+globalMessageName,
                        method: "POST",
                        success: function (result) {
                            for(var key in result){
                                var  id = "#"+key;
                                if ($(id).length <= 0){
                                    var time = new Date().toLocaleString(Number(key)).replace(/\//g, "-").replace("上午", "").replace("下午", "")
                                    $("#main_div").append("<li id='"+key+"' style='float: left;'>" +time + ": " + result[key] + "</li><br/>");
                                }
                            }
                        },
                        error: function (result) {
                            console.log("send flush result is fail");
                        }
                    });
                }
            }


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

            function sendMessageByName(name) {
                $.ajax({
                    url: "http://localhost:10001/hl7/send/"+name.toUpperCase(),
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


            $("#VIEW_RESULT_BTN").click(function () {

            });

            $("#CLEAN_RESULT_BTN").click(function () {
                cleanResult();
            });

            $("#SEND_ADT01_BTN").click(function () {
                sendMessageByName("ADT_A01");
            });

            $("#SEND_V232_BTN").click(function () {
                sendMessageByName("V232");
            });
        });
    </script>
</head>
<body>
    <button id="VIEW_RESULT_BTN">查看结果</button>
    <button id="SEND_V232_BTN">发送V232类型消息</button>
    <button id="SEND_ADT01_BTN">发送ADT_01类型消息</button>
    <button id="CLEAN_RESULT_BTN">清空控制台</button>
<hr/>
<#--<center>-->
    <div id="main_div" style="border:1px solid #000; width: 800px; height: 400px; background-color: aliceblue; overflow:auto;">
    </div>
<#--</center>-->
</body>
</html>