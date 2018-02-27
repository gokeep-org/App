//package com.app;
//
//import com.app.hl7.SendAndReceiveAMessage;
//import com.app.hl7.entity.User;
//import com.app.hl7.service.UserService;
//import com.app.hl7.util.JdbcUtils;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//public class UserTest {
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void testAddUser(){
//        User user = new User();
//        user.setUsername("徐宁");
//        userService.addUser(user);
//    }
//
//    @Test
//    public void testJdbcUtil(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String nowDate = sdf.format(new Date());
//        List<String> params = new ArrayList<>();
//        params.add("1");
//        params.add(UUID.randomUUID().toString());
//        params.add("MSH|^~\\&|FORMENTRY|AMRS|LOCAL|LOCAL|20050217152845||ORU^R01|AMRS20050217152845|P|2.5|1||||||||1^AMRS-ELDORET\\^[http://schemas.openmrs.org/2006/FormEntry/formId]\\^URI\n" +
//                "PID||1^\\^^AMRS|1MT^9^M10||Patient^Jonny^Dee{^}{^}DR|Patient^Momma^Thee^\\^MS|20040101000000^Y|M||B|555 Johnson Road^Apt.555^Indianapolis^IN^46202^USA|||||||||||Indianapolis, IN|||||||||||||||||TRIBE CODE\n" +
//                "PV1|1|O|^^^^^^^^^1^AMRS|2|||1^Mamlin^Joseph^^^^^^^^8^M10^^AMRS|||||||||||||||||||||||||||||||||||||20050217140000|||||||V\n" +
//                "ORC|RE||||||||20050221130000|1^Enterer^Ima^^^^^AMRS\n" +
//                "OBR|1|||1238^MEDICAL RECORD OBSERVATIONS^DCT\n" +
//                "OBX|1|CE|1082^REVIEW OF SYSTEMS, CENTRAL NERVOUS SYSTEM^DCT||207^DEPRESSION^DCT||||||F|||20050217204000\n" +
//                "OBX|2|SN|5497^CD4 COUNT^DCT||<^10|cells/mm3|10-1500|L|||F|||20050217204000\n" +
//                "OBX|3|NM|5089^WEIGHT (KG)^DCT||25|kg|20-300|L|||F|||20050217204000\n" +
//                "OBX|4|TS|1191^HISTORICAL DRUG STOP DATE^DCT||20050101||||||F|||20050217204000");
//        params.add("0");
//        params.add(nowDate);
//        params.add(UUID.randomUUID().toString());
//        try {
//            Connection connection = JdbcUtils.getConnection();
//            JdbcUtils.update("insert into hl7_in_queue(hl7_source, hl7_source_key, hl7_data, message_state, date_created, uuid) values(?, ?, ?, ?, ?, ?);", params);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            JdbcUtils.close();
//        }
//    }
//
//    @Test
//    public void startSendmessage(){
//        String msg = "MSH|^~\\&|SendingApplication|SendingFacility|NIST_RCVR_877|NIST|20101004145353||QBP^Q23^QBP_Q21|NIST-101004145353138|P|2.2\r" +
//                "QPD|IHE PIX Query|QRY1248968460880|PIXL1^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO|^^^NIST2010-3&2.16.840.1.113883.3.72.5.9.3&ISO\r" +
//                "RCP|I";
//        SendAndReceiveAMessage.build().buildAddess("129.6.24.81", 9080).sendByCode(msg);
//        System.out.println("");
//    }
//}
