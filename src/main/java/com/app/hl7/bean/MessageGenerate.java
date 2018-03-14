//package com.app.hl7.bean;
//
//import ca.uhn.hl7v2.DefaultHapiContext;
//import ca.uhn.hl7v2.HapiContext;
//import ca.uhn.hl7v2.model.DataTypeException;
//import ca.uhn.hl7v2.model.Message;
//import ca.uhn.hl7v2.model.v24.message.ADT_A01;
//import ca.uhn.hl7v2.model.v24.segment.PID;
//import ca.uhn.hl7v2.parser.Parser;
//import com.app.hl7.SendAndReceiveAMessage;
//
//import java.util.Date;
//
//public class MessageGenerate extends BasicMessage{
//    private MessageGenerate (){}
//
//    public Message getAddPatientMessage(HttpHl7Message message){
//
//        String generateMessage = MSH +
//                "EVN||"+getDateFormat().format(new Date())+"\r" +
//                "PID|||PIX^^^"+getDomain()+"||FARNSWORTH ^ STEVE^^^^^L|BARNES^^^^^^L|19781208|M|||820 JORIE BLVD^^NEW YORK CITY^NY^10503||||||||153-12-5432\r" +
//                "PV1||I";
//        Message response = SendAndReceiveAMessage.build().buildAddess(message.getHost(), message.getPort()).sendMessage(generateMessage);
//        return response;
//
//    }
//
//    public static void main(String[] args) {
//        ADT_A01 adt = new ADT_A01();
//        adt.initQuickstart();
//
//        // Populate the MSH Segment
//        MSH mshSegment = adt.getMSH();
//        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
//        mshSegment.getSequenceNumber().setValue("123");
//
//        // Populate the PID Segment
//        PID pid = adt.getPID();
//        pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
//        pid.getPatientName(0).getGivenName().setValue("John");
//        pid.getPatientIdentifierList(0).getID().setValue("123456");
//
//    }
//    public Message getADT_A01(){
//        ADT_A01 adt_a01 = new ADT_A01();
//        adt_a01.
//        try {
//            adt_a01.getMSH().getSendingApplication().getNamespaceID().setValue("");
//        } catch (DataTypeException e) {
//            e.printStackTrace();
//        }
//        // Populate the MSH Segment
//        MSH mshSegment = adt.getMSH();
//        mshSegment.getSendingApplication().getNamespaceID().setValue("TestSendingSystem");
//        mshSegment.getSequenceNumber().setValue("123");
//
//        // Populate the PID Segment
//        PID pid = adt.getPID();
//        pid.getPatientName(0).getFamilyName().getSurname().setValue("Doe");
//        pid.getPatientName(0).getGivenName().setValue("John");
//        pid.getPatientIdentifierList(0).getID().setValue("123456");
//
//        /*
//         * In a real situation, of course, many more segments and fields would be populated
//         */
//
//        // Now, let's encode the message and look at the output
//        HapiContext context = new DefaultHapiContext();
//        Parser parser = context.getPipeParser();
//        String encodedMessage = parser.encode(adt);
//        System.out.println("Printing ER7 Encoded Message:");
//        System.out.println(encodedMessage);
//
//        /*
//         * Prints:
//         *
//         * MSH|^~\&|TestSendingSystem||||200701011539||ADT^A01^ADT A01||||123
//         * PID|||123456||Doe^John
//         */
//
//        // Next, let's use the XML parser to encode as XML
//        parser = context.getXMLParser();
//        encodedMessage = parser.encode(adt);
//        System.out.println("Printing XML Encoded Message:");
//        System.out.println(encodedMessage);
//    }
//}