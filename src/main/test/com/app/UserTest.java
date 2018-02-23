package com.app;

import com.app.hl7.SendAndReceiveAMessage;
import com.app.hl7.entity.User;
import com.app.hl7.service.UserService;
import com.app.hl7.util.JdbcUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("徐宁");
        userService.addUser(user);
    }

    @Test
    public void testJdbcUtil(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(new Date());
        List<String> params = new ArrayList<>();
        params.add("1");
        params.add(UUID.randomUUID().toString());
        params.add("MSH|^~\\&|FORMENTRY|AMRS|LOCAL|LOCAL|20050217152845||ORU^R01|AMRS20050217152845|P|2.5|1||||||||1^AMRS-ELDORET\\^[http://schemas.openmrs.org/2006/FormEntry/formId]\\^URI\n" +
                "PID||1^\\^^AMRS|1MT^9^M10||Patient^Jonny^Dee{^}{^}DR|Patient^Momma^Thee^\\^MS|20040101000000^Y|M||B|555 Johnson Road^Apt.555^Indianapolis^IN^46202^USA|||||||||||Indianapolis, IN|||||||||||||||||TRIBE CODE\n" +
                "PV1|1|O|^^^^^^^^^1^AMRS|2|||1^Mamlin^Joseph^^^^^^^^8^M10^^AMRS|||||||||||||||||||||||||||||||||||||20050217140000|||||||V\n" +
                "ORC|RE||||||||20050221130000|1^Enterer^Ima^^^^^AMRS\n" +
                "OBR|1|||1238^MEDICAL RECORD OBSERVATIONS^DCT\n" +
                "OBX|1|CE|1082^REVIEW OF SYSTEMS, CENTRAL NERVOUS SYSTEM^DCT||207^DEPRESSION^DCT||||||F|||20050217204000\n" +
                "OBX|2|SN|5497^CD4 COUNT^DCT||<^10|cells/mm3|10-1500|L|||F|||20050217204000\n" +
                "OBX|3|NM|5089^WEIGHT (KG)^DCT||25|kg|20-300|L|||F|||20050217204000\n" +
                "OBX|4|TS|1191^HISTORICAL DRUG STOP DATE^DCT||20050101||||||F|||20050217204000");
        params.add("0");
        params.add(nowDate);
        params.add(UUID.randomUUID().toString());
        try {
            Connection connection = JdbcUtils.getConnection();
            JdbcUtils.update("insert into hl7_in_queue(hl7_source, hl7_source_key, hl7_data, message_state, date_created, uuid) values(?, ?, ?, ?, ?, ?);", params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close();
        }
    }

    @Test
    public void startSendmessage(){
        String msg = "MSH|^~\\&|NES|NINTENDO|TESTSYSTEM|TESTFACILITY|20010101000000||ADT^A04|Q123456789T123456789X123456|P|2.2\r" +
                "EVN|A04|20010101000000|||^KOOPA^BOWSER^^^^^^^CURRENT\r" +
                "PID|1||123456789|0123456789^AA^^JP|BROS^MARIO^^^^||19850101000000|M|||123 FAKE STREET^MARIO \\T\\ LUIGI BROS PLACE^TOADSTOOL KINGDOM^NES^A1B2C3^JP^HOME^^1234|1234|(555)555-0123^HOME^JP:1234567|||S|MSH|12345678|||||||0|||||N\r" +
                "NK1|1|PEACH^PRINCESS^^^^|SO|ANOTHER CASTLE^^TOADSTOOL KINGDOM^NES^^JP|(123)555-1234|(123)555-2345|NOK|||||||||||||\r" +
                "NK1|2|TOADSTOOL^PRINCESS^^^^|SO|YET ANOTHER CASTLE^^TOADSTOOL KINGDOM^NES^^JP|(123)555-3456|(123)555-4567|EMC|||||||||||||\r" +
                "PV1|1|O|ABCD^EFGH^|||^^|123456^DINO^YOSHI^^^^^^MSRM^CURRENT^^^NEIGHBOURHOOD DR NBR^|^DOG^DUCKHUNT^^^^^^^CURRENT||CRD|||||||123456^DINO^YOSHI^^^^^^MSRM^CURRENT^^^NEIGHBOURHOOD DR NBR^|AO|0123456789|1|||||||||||||||||||MSH||A|||20010101000000";
        SendAndReceiveAMessage.build().sendByCode(msg);
        System.out.println("");
    }
}
