package com.app.hl7.msg;

import ca.uhn.hl7v2.protocol.ReceivingApplication;
import com.app.hl7.PIXConsumeTest;
import com.app.hl7.PdqConsumeTest;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class TestMessage {
    private static final String SEND_APPLICATION_NAME = "CHAOSI_CLIENT";
    private static final String SEND_FACILIT_NAME_NAME = "CHAOSI_CLIENT";
    public static final String PIX_CONSUME_ITI_9 = "MSH|^~\\&|SendingApplication|SendingFacility|"+ PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20101004145353||QBP^Q23^QBP_Q21|NIST-101004145353138|P|2.5\r" +
            "QPD|IHE PIX Query|QRY1248968460880|PIXL1^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO\r" +
            "RCP|I";

    public static final String PIX_SOURCE_ITI8_A01 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20101004144709||ADT^A01^ADT_A01|NIST-101004144709009|P|2.3.1\r" +
            "EVN||20101004144709\r" +
            "PID|||PIX^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||ALPHA^ALAN^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||153-12-5432\r" +
            "PV1||I";

    public static final String PIX_SOURCES_ITI_8_A04 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20101004144709||ADT^A04^ADT_A01|NIST-101004144709009|P|2.3.1\r" +
            "EVN||20101004144709\r" +
            "PID|||PIX^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||ALPHA^ALAN^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||153-12-5432\r" +
            "PV1||O";

    public static final String PIX_SOURCES_ITI_8_A05 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20101004144709||ADT^A05^ADT_A01|NIST-101004144709009|P|2.3.1\r" +
            "EVN||20101004144709\r" +
            "PID|||PIX^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||ALPHA^ALAN^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||153-12-5432\r" +
            "PV1||P";

    public static final String PIX_SOURCES_ITI_8_A08 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20090527135020||ADT^A08^ADT_A01|NIST-090527135016929|P|2.3.1\r" +
            "EVN||20090527\r" +
            "PID|||PIX^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||TAU^TERI^^^^^L||19780510||||202 KEN HABOR^^NEW YORK CITY^NY^61000\r" +
            "PV1||R";

    public static final String PIX_SOURCES_ITI_8_A40 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PIXConsumeTest.applicationName+"|"+PIXConsumeTest.facilitName+"|20090528110025||ADT^A40^ADT_A39|NIST-090528110022806|P|2.3.1\r" +
            "EVN||20090528\r" +
            "PID|||PIXL^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||LINCOLN^MARY^^^^^L\r" +
            "MRG|PIXW^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO||||||WASHINGTON^MARY^^^^^L\r";

    public static final String ADT_A04_MESSAGE = "MSH|^~\\&|NES|NINTENDO|TESTSYSTEM|TESTFACILITY|20010101000000||ADT^A04|Q123456789T123456789X123456|P|2.5\r" +
            "EVN|A04|20010101000000|||^KOOPA^BOWSER^^^^^^^CURRENT\r" +
            "PID|1||123456789|0123456789^AA^^JP|BROS^MARIO^^^^||19850101000000|M|||123 FAKE STREET^MARIO \\T\\ LUIGI BROS PLACE^TOADSTOOL KINGDOM^NES^A1B2C3^JP^HOME^^1234|1234||||S|MSH|12345678|||||||0|||||N\n";

    public static final String PDQ_ITI_21_1_1 = "MSH|^~\\&|SendingApplication|NIST_RCVR_773|"+ PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"|20101004143744||QBP^Q22^QBP_Q21|NIST-101004143744188|P|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.5.1.1^MOORXE\r" +
            "RCP|I|1^RD";

    public static final String PDQ_ITI_21_2_1 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"|20101004143744||QBP^Q22^QBP_Q21|NIST-101004143744188|P|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.5.1.1^MOORXE\r" +
            "RCP|I|1^RD";

    public static final String PDQ_ITI_21_3_1 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"|20101004143744||QBP^Q22^QBP_Q21|NIST-101004143744188|T|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.11.4^MD|||||^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO\r" +
            "RCP|I";

    public static final String PDQ_ITI_21_3_2 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"|20101004143744||QBP^Q22^QBP_Q21|NIST-101004143744188|T|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.11.4^MD\r" +
            "RCP|I";

    public static final String PDQ_ITI_21_4_1 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"|20101004143744||QBP^Q22^QBP_Q21|NIST-101004143744188|T|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.5.1.1^NEAL~@PID.8^M|||||^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO\r" +
            "RCP|I";

    public static final String PDQ_ITI_21_4_2 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"|20101004143744||QBP^Q22^QBP_Q21|NIST-101004143744188|T|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.5.1.1^NEAL~@PID.8^M\r" +
            "RCP|I";

    public static final String PDQ_ITI_21_4_3 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"|20101004143744||QBP^Q22^QBP_Q21|NIST-101004143744188|T|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.5.1.1^NEAL~@PID.8^M\r" +
            "RCP|I";

    public static final String PDQ_ITI_21_5_1 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"|20101004143744||QBP^Q22^QBP_Q21|NIST-101004143744188|T|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.5.1.1^NEAL~@PID.7.1^19781013|||||^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO\r" +
            "RCP|I";

    public static final String PDQ_ITI_21_5_2 = "MSH|^~\\&|SendingApplication|SendingFacility|ReceivingApplication|"+PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"||QBP^Q22^QBP_Q21|NIST-101004143744188|T|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.5.1.1^NEAL~@PID.7.1^19781013\r" +
            "RCP|I";

    public static final String PDQ_ITI_21_6_1 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"|20101004143744||QBP^Q22^QBP_Q21|NIST-101004143744188|T|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.3.1^DV-301~@PID.3.4.1^NIST2010~@PID.3.4.2^2.16.840.1.113883.3.72.5.9.1~@PID.3.4.3^ISO|||||^^^NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO\r" +
            "RCP|I";


    public static final String PDQ_ITI_21_6_2 = "MSH|^~\\&|SendingApplication|SendingFacility|"+PdqConsumeTest.applicationName+"|"+PdqConsumeTest.facilitName+"|20101004143744||QBP^Q22^QBP_Q21|NIST-101004143744188|T|2.5\r" +
            "QPD|IHE PDQ Query|QRY124818486466|@PID.3.1^DV-301~@PID.3.4.1^NIST2010~@PID.3.4.2^2.16.840.1.113883.3.72.5.9.1~@PID.3.4.3^ISO\r" +
            "RCP|I";



}
