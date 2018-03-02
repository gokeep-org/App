//package com.app.hl7.msg;
//
//import ca.uhn.hl7v2.protocol.ReceivingApplication;
//import com.app.hl7.PIXConsumeTest;
//import com.sun.org.apache.bcel.internal.generic.NEW;
//
//public class TestMessage {
//    public static final String PIX_CONSUME_ITI_9 = "MSH|^~\\&|SendingApplication|SendingFacility|"+ PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20101004145353||QBP^Q23^QBP_Q21|NIST-101004145353138|P|2.5\r" +
//            "QPD|IHE PIX Query|QRY1248968460880|PIXL1^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO\n" +
//            "RCP|I";
//
//    public static final String PIX_SOURCE_ITI8_A01 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20101004144709||ADT^A01^ADT_A01|NIST-101004144709009|P|2.3.1\r" +
//            "EVN||20101004144709\r" +
//            "PID|||PIX^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||ALPHA^ALAN^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||153-12-5432\n" +
//            "PV1||I";
//
//    public static final String PIX_SOURCES_ITI_8_A04 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20101004144709||ADT^A04^ADT_A01|NIST-101004144709009|P|2.3.1\r" +
//            "EVN||20101004144709\r" +
//            "PID|||PIX^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||ALPHA^ALAN^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||153-12-5432\n" +
//            "PV1||O";
//
//    public static final String PIX_SOURCES_ITI_8_A05 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20101004144709||ADT^A05^ADT_A01|NIST-101004144709009|P|2.3.1\r" +
//            "EVN||20101004144709\r" +
//            "PID|||PIX^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||ALPHA^ALAN^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||153-12-5432\n" +
//            "PV1||P";
//
//    public static final String PIX_SOURCES_ITI_8_A08 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20090527135020||ADT^A08^ADT_A01|NIST-090527135016929|P|2.3.1\r" +
//            "EVN||20090527\r" +
//            "PID|||PIX^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||TAU^TERI^^^^^L||19780510||||202 KEN HABOR^^NEW YORK CITY^NY^61000\n" +
//            "PV1||R";
//
//    public static final String ADT_A04_MESSAGE = "MSH|^~\\&|NES|NINTENDO|TESTSYSTEM|TESTFACILITY|20010101000000||ADT^A04|Q123456789T123456789X123456|P|2.5\r" +
//            "EVN|A04|20010101000000|||^KOOPA^BOWSER^^^^^^^CURRENT\r" +
//            "PID|1||123456789|0123456789^AA^^JP|BROS^MARIO^^^^||19850101000000|M|||123 FAKE STREET^MARIO \\T\\ LUIGI BROS PLACE^TOADSTOOL KINGDOM^NES^A1B2C3^JP^HOME^^1234|1234||||S|MSH|12345678|||||||0|||||N\n";
//}
