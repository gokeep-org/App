package com.app.hl7.bean;


import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

import ca.uhn.hl7v2.model.MessageVisitor;
import ca.uhn.hl7v2.model.MessageVisitors;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.PID;
import com.app.hl7.Hl7ComonConfig;
import com.app.hl7.SendAndReceiveAMessage;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpHl7MessageBody extends BasicMessage implements Serializable {

    public HttpHl7MessageBody() {
        super();
    }

    public Message getAddPatientMessage() throws IOException, HL7Exception {
        Map<String, String> map = getContent();
        if (CollectionUtils.isEmpty(getContent())){
            return null;
        }
        ca.uhn.hl7v2.model.v25.message.ADT_A01 adt = new ca.uhn.hl7v2.model.v25.message.ADT_A01();
        adt.initQuickstart("ADT", "A01", "P");
        MSH mshSegment = adt.getMSH();
        mshSegment.getSendingApplication().getNamespaceID().setValue(Hl7ComonConfig.sendApplication);
        mshSegment.getSendingFacility().getNamespaceID().setValue(Hl7ComonConfig.sendFacilit);
        mshSegment.getReceivingApplication().getNamespaceID().setValue(map.get("receiver_application_name"));
        mshSegment.getReceivingFacility().getNamespaceID().setValue(map.get("receiver_facility_name"));
        if (!StringUtils.isEmpty(map.get("sequence_number"))){
            mshSegment.getSequenceNumber().setValue((String) map.get("sequence_number"));
        }
        // Populate the PID Segment
        PID pid = adt.getPID();
        pid.getPatientName(0).getFamilyName().getSurname().setValue((String) map.get("sure_name"));
        pid.getPatientName(0).getGivenName().setValue(map.get("given_name"));
        pid.getBreedCode().getCe3_NameOfCodingSystem().setValue("111111");
        pid.getPatientAddress(0).getCity().setValue(map.get("city"));
        pid.getAdministrativeSex().setValue(map.get("sex"));
        pid.getPatientID().getIDNumber().setValue(map.get("id"));
        pid.getBirthPlace().setValue(map.get("birth"));
        pid.getNationality().getNameOfCodingSystem().setValue("sssss");
        pid.getReligion().getNameOfCodingSystem().setValue("111");
        pid.getReligion().getText().setValue("222");
        pid.getSetIDPID().setValue("33333");
        pid.getStrain().setValue("4444");
        pid.getDriverSLicenseNumberPatient().getLicenseNumber().setValue("444444");
        pid.getDriverSLicenseNumberPatient().getLicenseNumber().setValue("444444");

        return adt;
    }

    public static void main(String[] args) {
        HttpHl7MessageBody body = new HttpHl7MessageBody();
        body.setHost("172.16.1.163");
        body.setPort(9004);
        Map<String, String> me = new HashMap<>();
        me.put("sequence_number", "121312121");
        me.put("receiver_application_name", "hinacom-supplier");
        me.put("receiver_facility_name", "hinacom");
        me.put("given_name", "aaaa");
        me.put("id", UUID.randomUUID().toString());
        me.put("city", "beijing");
        me.put("birth", "2010-01-11 12:34:11");
        me.put("sex", "M");
        body.setContent(me);
        try {
            Message response = SendAndReceiveAMessage.build().buildAddess("172.16.1.163", 9004).sendMessage(body.getAddPatientMessage());
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HL7Exception e) {
            e.printStackTrace();
        }
    }
}
