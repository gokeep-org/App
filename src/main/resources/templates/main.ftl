<!DOCTYPE>
<html>
<head>
    <title>测试HL7平台</title>
    <meta charset="UTF-8"/>
    <script type="application/javascript" src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <script type="application/javascript">
        $("document").ready(function () {
            setInterval(flushResult, 2000);
            function flushResult() {
                $('#main_div').scrollTop($('#main_div')[0].scrollHeight);
                $.ajax({
                    url: "http://localhost:10001/hl7/flush?name=ADT_A01",
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

            $("#ADT_A01_BTN").click(function () {

            });
        });
    </script>
</head>
<body>
<button id="ADT_A01_BTN">ADT_A01</button>
<hr/>
<center>
    <div id="main_div"
         style="border:1px solid #000; width: 800px; height: 400px; background-color: aliceblue; overflow:auto;">

    </div>
</center>
</body>
</html>