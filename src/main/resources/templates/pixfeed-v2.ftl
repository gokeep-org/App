<!DOCTYPE>
<html>
<head>
    <title>测试HL7平台</title>
    <meta charset="UTF-8"/>
    <script type="application/javascript" src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <script type="application/javascript">
        $(document).ready(function () {

            function getBody() {
                return {
                    "host": $("input[name='host']").val(),
                    "port": $("input[name='port']").val(),
                    "receiver_application": $("input[name='receiver_application']").val(),
                    "facility_name": $("input[name='facility_name']").val(),
                    "id": $("input[name='id']").val(),
                    "rep_id": $("input[name='rep_id']").val(),
                    "domain": $("input[name='domain']").val(),
                    "first_name": $("input[name='first_name']").val(),
                    "last_name": $("input[name='last_name']").val(),
                    "birth": $("input[name='birth']").val(),
                    "sex": $("input[name='sex']").val(),
                    "address": $("input[name='address']").val(),
                    "sequence_number": $("input[name='sequence_number']").val()
                };
            }
           $("#SEND_ADD_PATIENT_MESSAGE_BTN").click(function(){
               sendaddPatientMessageByAddress(getBody());


           });

            $("#SEND_UPDATE_PATIENT_MESSAGE_BTN").click(function(){
                sendUpdatePatientMessageByAddress(getBody());
            });

            $("#SEND_MERGE_PATIENT_MESSAGE_BTN").click(function(){
                sendMergePatientMessageByAddress(getBody());
            });
        });

        function sendaddPatientMessageByAddress(body) {
            $.ajax({
                data: JSON.stringify(body),
                url: "/pix/save",
                type: "POST",
                dataType: "json",
                headers: {'Content-Type': 'application/json'},
                success: function (result) {
                    $("#main_div_in").text(result['in']);
                    $("#main_div_out").text(result['out']);
                },
                error: function (result) {
                    console.log("send message is fail");
                }
            });
        }


        function sendUpdatePatientMessageByAddress(body) {
            $.ajax({
                data: JSON.stringify(body),
                url: "/pix/update",
                type: "POST",
                dataType: "json",
                headers: {'Content-Type': 'application/json'},
                success: function (result) {
                    $("#main_div_in").text(result['in']);
                    $("#main_div_out").text(result['out']);
                },
                error: function (result) {
                    console.log("send message is fail");
                }
            });
        }

        function sendMergePatientMessageByAddress(body) {
            $.ajax({
                data: JSON.stringify(body),
                url: "/pix/merge",
                type: "POST",
                dataType: "json",
                headers: {'Content-Type': 'application/json'},
                success: function (result) {
                    $("#main_div_in").text(result['in']);
                    $("#main_div_out").text(result['out']);
                },
                error: function (result) {
                    console.log("send message is fail");
                }
            });
        }
    </script>
</head>
<body>

{
"host": "172.16.1.163",
"port": 9004,
"receiver_application_name": "hinacom-pix",
"receiver_facility_name": "hinacom",
"content":{
"patient_id": "cucu",
"sequence_number": "12121",
"patient_identify": "ertycbnm",
"ssn_code": "218-2122-1213",
"patient_account": "dtyftyfyt67",
"family_name": "xu",
"given_name": "ning",
"country": "china",
"province": "shandong",
"city": "jinan",
"street": "rong dong street xinyuan",
"post_code": "255000",
"mother_id": "13331421421",
"sex": "L",
"birth": "19920915122301",
"domain": "NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO",
"doctor_family_name": "doc_test",
"doctor_given_name": "doc_test_haa"
}
}
<br/>
<label>目标主机(TargeHost)：</label>
<input type="text" name="host" value="172.16.1.163">
<label>目标端口(TargePort)：</label>
<input type="text" name="port" value="9004"><br>
<label>应用名称(ReceivingApplication)：</label>
<input type="text" name="receiver_application_name" value="hinacom-pix"><br>
<label>设备名称(ReceivingFacility)：</label>
<input type="text" name="receiver_facility_name" value="hinacom"><br>
<label>Domain: </label>
<input type="text" name="domain" value="NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO"><br>


<h3>病人信息：</h3>
<label>ID </label>
<input type="text" name="patient_id" id="patient_id" value=""><br>

<label>病人标识符： </label>
<input type="text" name="patient_identify" id="patient_identify" value=""><br>

<label>社保号： </label>
<input type="text" name="ssn_code" id="ssn_code" value=""><br>


<label>账户： </label>
<input type="text" name="patient_account" id="patient_account" value=""><br>

<label>姓： </label>
<input type="text" name="family_name" id="family_name" value=""><br>


<label>名： </label>
<input type="text" name="given_name" id="given_name" value=""><br>

<label>性别： </label>
<input type="text" name="sex" id="sex" value=""><br>

<label>生日： </label>
<input type="text" name="birth" id="birth" value=""><br>

<label>国家：</label>
<input type="text" name="country" id="country" value=""><br>

<label>省份：</label>
<input type="text" name="province" id="province" value=""><br>


<label>城市：</label>
<input type="text" name="city" id="city" value=""><br>


<label>街道：</label>
<input type="text" name="street" id="street" value=""><br>


<label>邮编：</label>
<input type="text" name="post_code" id="post_code" value=""><br>

<label>监护人标识符：</label>
<input type="text" name="mother_id" id="mother_id" value=""><br>

<label>domain：</label>
<input type="text" name="domain" id="domain" value=""><br>


<label>医生姓：</label>
<input type="text" name="doctor_family_name" id="doctor_family_name" value=""><br>



<label>医生名：</label>
<input type="text" name="doctor_given_name" id="doctor_given_name" value=""><br>

<label>合并病人标识符： </label>
<input type="text" name="merge_patient_id" id="merge_patient_id" value=""><br>


<#--<label>姓</label>-->
<#--<input type="text" name="first_name" id="first_name" value=""><br>-->
<#--<label>名： </label>-->
<#--<input type="text" name="last_name" id="last_name" value=""><br>-->
<#--<label>出生年月: </label>-->
<#--<input type="text" name="birth" id="birth" value=""><br>-->
<#--<label>性别: </label>-->
<#--<input type="text" name="sex" id="sex" value="L"><br>-->
<#--<label>地址: </label>-->
<#--<input type="text" name="address" id="address" value=""><br>-->
<#--<label>社保号: </label>-->
<input type="text" name="sequence_number" value=""><br>
<button id="SEND_ADD_PATIENT_MESSAGE_BTN">添加</button>
<button id="SEND_UPDATE_PATIENT_MESSAGE_BTN">修改</button>
<button id="SEND_MERGE_PATIENT_MESSAGE_BTN">合并</button>
<br/>
<lable>消息输入：</lable>
<div id="main_div_in"
     style="border:1px solid #000; width: 1000px; height: 200px; background-color: aliceblue; overflow:auto;">
</div>

<lable>消息输出：</lable>
<div id="main_div_out"
     style="border:1px solid #000; width: 1000px; height: 200px; background-color: aliceblue; overflow:auto;">
</div>

</body>
</html>