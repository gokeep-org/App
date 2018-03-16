package com.app.hl7.bean;


import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.message.ADT_A08;
import ca.uhn.hl7v2.model.v231.message.ADT_A39;
import ca.uhn.hl7v2.model.v231.message.ADT_A40;
import ca.uhn.hl7v2.model.v231.segment.EVN;
import ca.uhn.hl7v2.model.v231.segment.MRG;
import ca.uhn.hl7v2.model.v24.message.QBP_K13;
import ca.uhn.hl7v2.model.v25.message.ADT_A01;
import ca.uhn.hl7v2.model.v25.message.QBP_Q21;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.model.v25.segment.PV1;
import com.app.hl7.Hl7ComonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class HttpHl7MessageBody extends BasicMessage implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(HttpHl7MessageBody.class);

    public HttpHl7MessageBody() {
        super();
    }


    /**
     * 构建MSH通用信息
     *
     * @param mshSegment
     * @param map
     * @throws DataTypeException
     */
    public void buildMsh(MSH mshSegment, Map<String, String> map) throws DataTypeException {
        mshSegment.getSendingApplication().getNamespaceID().setValue(Hl7ComonConfig.sendApplication);
        mshSegment.getSendingFacility().getNamespaceID().setValue(Hl7ComonConfig.sendFacilit);
        mshSegment.getReceivingApplication().getNamespaceID().setValue(getReceiver_application_name());
        mshSegment.getReceivingFacility().getNamespaceID().setValue(getReceiver_facility_name());
        mshSegment.getSequenceNumber().setValue(map.get("sequence_number"));
    }

    public void buildMsh(ca.uhn.hl7v2.model.v231.segment.MSH mshSegment, Map<String, String> map) throws DataTypeException {
        mshSegment.getSendingApplication().getNamespaceID().setValue(Hl7ComonConfig.sendApplication);
        mshSegment.getSendingFacility().getNamespaceID().setValue(Hl7ComonConfig.sendFacilit);
        mshSegment.getReceivingApplication().getNamespaceID().setValue(getReceiver_application_name());
        mshSegment.getReceivingFacility().getNamespaceID().setValue(getReceiver_facility_name());
        mshSegment.getSequenceNumber().setValue(map.get("sequence_number"));
    }




    /**
     * 构建2.5 版本pid
     *
     * @param pid
     * @param map
     * @throws DataTypeException
     */
    public void buildPid(PID pid, Map<String, String> map) throws DataTypeException {
        // 病人标ID
        pid.getPatientID().getIDNumber().setValue(map.get("patient_id"));
        // 病人标识符
        pid.getPatientIdentifierList(0).getIDNumber().setValue(map.get("patient_identify"));
        // 病人账号
        pid.getPatientAccountNumber().getIDNumber().setValue(map.get("patient_account"));
        // 病人的家庭名字
        pid.getPatientName(0).getFamilyName().getSurname().setValue(map.get("family_name"));
        // 病人的曾用名
        pid.getPatientName(0).getGivenName().setValue(map.get("given_name"));
        // 国家
        pid.getPatientAddress(0).getCountry().setValue(map.get("country"));
        // 省份
        pid.getPatientAddress(0).getStateOrProvince().setValue(map.get("province"));
        // 城市
        pid.getPatientAddress(0).getCity().setValue(map.get("city"));
        // 街道
        pid.getPatientAddress(0).getStreetAddress().getStreetName().setValue(map.get("street"));
        // 邮编
        pid.getPatientAddress(0).getZipOrPostalCode().setValue(map.get("post_code"));
        // 社保号
        pid.getSSNNumberPatient().setValue(map.get("ssn_code"));
        // 监护人的标识符
        pid.getMotherSIdentifier(0).getIDNumber().setValue(map.get("mother_id"));
        // 性别
        pid.getAdministrativeSex().setValue(map.get("sex"));
        // 出生年月
        pid.getBirthPlace().setValue(map.get("birth"));
        //-------------下面三个通过&构成domain----------------------------------
        String domain = map.get("domain");
        if (!StringUtils.isEmpty(domain)) {
            String[] dos = domain.split("&");
            // 机构ID号
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd1_NamespaceID().setValue(dos[0]);
            // universalID
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd2_UniversalID().setValue(dos[1]);
            // universaIdType
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd3_UniversalIDType().setValue(dos[2]);
        } else {
            // 机构ID号
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd1_NamespaceID().setValue(map.get("namespace_id"));
            // universalID
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd2_UniversalID().setValue(map.get("universal_id"));
            // universaIdType
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd3_UniversalIDType().setValue(map.get("universal_type"));
        }
    }


    /**
     * 构建2.3.1 版本pid
     *
     * @param pid
     * @param map
     * @throws DataTypeException
     */
    public void buildPid(ca.uhn.hl7v2.model.v231.segment.PID pid, Map<String, String> map) throws DataTypeException {
        // 病人标ID
        pid.getPatientID().getID().setValue(map.get("patient_id"));
        // 病人标识符
        pid.getPatientIdentifierList(0).getID().setValue(map.get("patient_identify"));
        // 社保号
        pid.getSSNNumberPatient().setValue(map.get(map.get("ssn_code")));
        // 病人账号
        pid.getPatientAccountNumber().getID().setValue(map.get("patient_account"));

        // 性别
        pid.getSex().setValue(map.get("sex"));
        // 病人的家庭名字
        pid.getPatientName(0).getFamilyLastName().getFamilyName().setValue(map.get("family_name"));
        // 病人的曾用名
        pid.getPatientName(0).getGivenName().setValue(map.get("given_name"));
        // 国家
        pid.getPatientAddress(0).getCountry().setValue(map.get("country"));
        // 省份
        pid.getPatientAddress(0).getStateOrProvince().setValue(map.get("province"));
        // 城市
        pid.getPatientAddress(0).getCity().setValue(map.get("city"));
        // 街道
        pid.getPatientAddress(0).getStreetAddress().setValue(map.get("street"));
        // 邮编
        pid.getPatientAddress(0).getZipOrPostalCode().setValue(map.get("post_code"));
        // 社保号
        pid.getSSNNumberPatient().setValue(map.get("ssn_code"));
        // 监护人的标识符
        pid.getMotherSIdentifier(0).getID().setValue(map.get("mother_id"));
        // 病人标识符
//        pid.getPatientIdentifierList(0).getID().setValue("chaosi_01");

        String domain = map.get("domain");
        //-------------下面三个通过&构成domain
        if (!StringUtils.isEmpty(domain)) {
            String[] dos = domain.split("&");
            // 机构ID号
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd1_NamespaceID().setValue(dos[0]);
            // universalID
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd2_UniversalID().setValue(dos[1]);
            // universaIdType
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd3_UniversalIDType().setValue(dos[2]);
        } else {
            // 机构ID号
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd1_NamespaceID().setValue(map.get("namespace_id"));
            // universalID
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd2_UniversalID().setValue(map.get("universal_id"));
            // universaIdType
            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd3_UniversalIDType().setValue(map.get("universal_type"));
        }
    }


    public void   buildPV1(PV1 pv1, Map<String, String> map) throws DataTypeException {
        // 入院类型
//        pv1.getAdmissionType().setValue("A");
        pv1.getAdmittingDoctor(0).getFamilyName().getSurname().setValue(map.get("doctor_family_name"));
        pv1.getAdmittingDoctor(0).getGivenName().setValue(map.get("doctor_given_name"));
    }


    /**
     * 获取添加病人的消息
     *
     * @return
     * @throws IOException
     * @throws HL7Exception
     */
    public Message getAddPatientMessage() {
        Map<String, String> map = getContent();
        if (CollectionUtils.isEmpty(getContent())) {
            return null;
        }
        ADT_A01 adt = new ADT_A01();
        try {
            adt.initQuickstart("ADT", "A01", "P");
            buildMsh(adt.getMSH(), map);
            buildPid(adt.getPID(), map);
            buildPV1(adt.getPV1(), map);
        } catch (Throwable e) {
            logger.error("get add patient message error, {}", e.getMessage());
        }

        return adt;
    }

    public Message getAddYCPatientMessage() {
        Map<String, String> map = getContent();
        if (CollectionUtils.isEmpty(getContent())) {
            return null;
        }
        ca.uhn.hl7v2.model.v231.message.ADT_A01 adt = new ca.uhn.hl7v2.model.v231.message.ADT_A01();
        try {
            adt.initQuickstart("ADT", "A01", "P");
            buildMsh(adt.getMSH(), map);
            buildPid(adt.getPID(), map);
        } catch (Throwable e) {
            logger.error("get add patient message error, {}", e.getMessage());
        }

        return adt;
    }

    /**
     * 获取更新病人的消息
     *
     * @return
     * @throws IOException
     * @throws HL7Exception
     */
    public Message getUpdatePatientMessage() {
        Map<String, String> map = getContent();
        if (CollectionUtils.isEmpty(getContent())) {
            return null;
        }
        ADT_A08 adt_a08 = new ADT_A08();
        try {
            adt_a08.initQuickstart("ADT", "A08", "P");
            buildMsh(adt_a08.getMSH(), map);
            buildPid(adt_a08.getPID(), map);
//            buildPV1(adt_a08.getPV1(), map);
        } catch (Throwable e) {
            logger.error("get update patient message find error");
        }
        return adt_a08;
    }


    public void buildEvn(EVN evn, Map<String, String> map) {
        try {
            evn.getEvn2_RecordedDateTime().getTimeOfAnEvent().setValue(new Date());
        } catch (DataTypeException e) {
            logger.error("set evn date is error");
        }

    }

    /**
     * Pix 客户端通过domain和标识符查询
     *
     * @param info
     * @return
     * @throws DataTypeException
     */
    public Message getSearchPatientByIdentifyAndDomain(Map<String, Object> info) throws DataTypeException {
        QBP_K13 qbp_k13;
        QBP_Q21 qbp_q21 = new QBP_Q21();
        try {
            qbp_q21.initQuickstart("QPB", "Q21", "P");

        } catch (HL7Exception e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        buildMsh(qbp_q21.getMSH(), info);

        qbp_q21.getQPD().getMessageQueryName().getIdentifier().setValue("CHAOSI SEARCH DOAMIN PATIENT");
//        qbp_q21.getQPD().getQpd2_QueryTag().setValue("").getIdentifier().setValue("CHAOSI SEARCH DOAMIN PATIENT");

//        qbp_q21.getQPD().getQueryTag().setValue("");
//        List<String> domains = (List<String>) info.get("domains");
//        qbp_q21.getQPD().getUserParametersInsuccessivefields();
//        qbp_q21.getQPD().get
//        domains.stream().forEach(domain->{
//            qbp_q21.getQPD().getAssigningAuthority().getHd1_NamespaceID().setValue(dos[0]);
//            // universalID
//            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd2_UniversalID().setValue(dos[1]);
//            // universaIdType
//            pid.getPatientIdentifierList(0).getAssigningAuthority().getHd3_UniversalIDType().setValue(dos[2]);
//        });
        return null;
    }

    /**
     * 获取合并消息
     *
     * @return
     * @throws IOException
     * @throws HL7Exception
     */
    public Message getMergePatientMessage() throws IOException, HL7Exception {
        Map<String, String> map = getContent();
        if (CollectionUtils.isEmpty(getContent())) {
            return null;
        }
//        ADT_A40 adt_a40 = new ADT_A40();
        ADT_A39 adt_a40 = new ADT_A39();

        adt_a40.initQuickstart("ADT", "A39", "P");

        ca.uhn.hl7v2.model.v231.segment.MSH mshSegment = adt_a40.getMSH();

        mshSegment.getSendingApplication().getNamespaceID().setValue(getSend_application_name());
        mshSegment.getSendingFacility().getNamespaceID().setValue(getSend_facility_name());
        mshSegment.getReceivingApplication().getNamespaceID().setValue(getReceiver_application_name());
        mshSegment.getReceivingFacility().getNamespaceID().setValue(getReceiver_facility_name());
        mshSegment.getSequenceNumber().setValue(map.get("sequence_number"));
        buildEvn(adt_a40.getEVN(), map);

        ca.uhn.hl7v2.model.v231.segment.PID pid = adt_a40.getPIDPD1MRGPV1().getPID();

        buildPid(pid, map);

        MRG mrg = adt_a40.getPIDPD1MRGPV1().getMRG();
        // 合并病人标识符
        mrg.getMrg1_PriorPatientIdentifierList(0).getID().setValue(map.get("merge_patient_id"));
        String domain = map.get("domain");

        // 病人
        pid.getPatientName(0).getGivenName().setValue(map.get("given_name"));
        //-------------下面三个通过&构成domain
        if (!StringUtils.isEmpty(domain)) {
            String[] dos = domain.split("&");
            // 机构ID号
            mrg.getMrg1_PriorPatientIdentifierList(0).getAssigningAuthority().getHd1_NamespaceID().setValue(dos[0]);
            // universalID
            mrg.getMrg1_PriorPatientIdentifierList(0).getAssigningAuthority().getHd2_UniversalID().setValue(dos[1]);
            // universaIdType
            mrg.getMrg1_PriorPatientIdentifierList(0).getAssigningAuthority().getHd3_UniversalIDType().setValue(dos[2]);
        } else {
            // 机构ID号
            mrg.getMrg1_PriorPatientIdentifierList(0).getAssigningAuthority().getHd1_NamespaceID().setValue(map.get("namespace_id"));
            // universalID
            mrg.getMrg1_PriorPatientIdentifierList(0).getAssigningAuthority().getHd2_UniversalID().setValue(map.get("universal_id"));
            // universaIdType
            mrg.getMrg1_PriorPatientIdentifierList(0).getAssigningAuthority().getHd3_UniversalIDType().setValue(map.get("universal_type"));
        }
        return adt_a40;
    }


//    public Message getAddPatientMessage() throws IOException, HL7Exception {
//        Map<String, String> map = getContent();
//        if (CollectionUtils.isEmpty(getContent())){
//            return null;
//        }
//        ADT_A01 adt = new ADT_A01();
//        adt.initQuickstart("ADT", "A01", "P");
//        MSH mshSegment = adt.getMSH();
//        mshSegment.getSendingApplication().getNamespaceID().setValue(Hl7ComonConfig.sendApplication);
//        mshSegment.getSendingFacility().getNamespaceID().setValue(Hl7ComonConfig.sendFacilit);
//        mshSegment.getReceivingApplication().getNamespaceID().setValue(map.get("receiver_application_name"));
//        mshSegment.getReceivingFacility().getNamespaceID().setValue(map.get("receiver_facility_name"));
//        if (!StringUtils.isEmpty(map.get("sequence_number"))){
//            mshSegment.getSequenceNumber().setValue((String) map.get("sequence_number"));
//        }
//        // Populate the PID Segment
//        PID pid = adt.getPID();
//        // 社保号
//        pid.getSSNNumberPatient().setValue("");
//        // 社保号
//        pid.getPatientID().getIDNumber();
//
//        pid.getPatientAccountNumber().getIDNumber().setValue("账号");
//        pid.getPatientName(0).getFamilyName().getSurname().setValue("");
//        pid.getPatientName(0).getGivenName().setValue(map.get("given_name"));
//
////        pid.getBreedCode().getCe3_NameOfCodingSystem().setValue("111111");
//
//        pid.getPatientAddress(0).getCountry().setValue("中国");
//        pid.getPatientAddress(0).getStateOrProvince().setValue("省份");
//        pid.getPatientAddress(0).getCity().setValue("n1");
//        pid.getPatientAddress(0).getStreetAddress().getStreetName().setValue("天通苑街道");
//        pid.getPatientAddress(0).getZipOrPostalCode().setValue("邮编");
//
////        pid.getStrain().setValue("vdvffdvfd");
//
//        // 这是给孩子看病没信息的情况
//        pid.getMotherSIdentifier(0).getIDNumber().setValue("妈妈的标识符");
//
//
//        PV1 pv1 = adt.getPV1();
//        // 入院类型
//        pv1.getAdmissionType().setValue("A");
////        pv1.getPatientClass().setValue(Admiss);
//
//        pid.getAdministrativeSex().setValue(map.get("sex"));
////        pid.getPatientID().getIDNumber().setValue(map.get("id"));
//        pid.getBirthPlace().setValue(map.get("birth"));
//        pid.getNationality().getNameOfCodingSystem().setValue("sssss");
//        pid.getReligion().getNameOfCodingSystem().setValue("111");
//        pid.getReligion().getIdentifier().setValue("ttt");
//        pid.getStrain().setValue("4444");
//        pid.getDriverSLicenseNumberPatient().getLicenseNumber().setValue("444444");
//        pid.getDriverSLicenseNumberPatient().getLicenseNumber().setValue("444444");
////        pid.getPatientID().getAssigningAgencyOrDepartment().getOriginalText().setValue("ddd");
//        pid.getIdentityReliabilityCode(0).setValue("cdcdcdcddcd");
//        pid.getPatientIdentifierList(0).getAssigningAgencyOrDepartment().getOriginalText().setValue("aaaaaaaaaaaa");
//        EVN evn = adt.getEVN();
//        evn.getEventReasonCode().setValue("1");
////        pid.getPatientIdentifierList(0).getIDNumber().setValue("id nuber");
//        pid.getPatientIdentifierList(0).getIDNumber().setValue("chaosi_01");
//        pid.getPatientIdentifierList(0).getAssigningAgencyOrDepartment().getOriginalText().setValue("NIST2010&2.16.840.1.113883.3.72.5.9.1&ISO");
//        pid.getPatientIdentifierList(0).getAssigningAuthority().getNamespaceID().setValue("namespace");
//        // domain 串拼接
//        pid.getPatientIdentifierList(0).getAssigningAuthority().getHd1_NamespaceID().setValue("NIST2010");
//        pid.getPatientIdentifierList(0).getAssigningAuthority().getHd2_UniversalID().setValue("2.16.840.1.113883.3.72.5.9.1");
//        pid.getPatientIdentifierList(0).getAssigningAuthority().getHd3_UniversalIDType().setValue("ISO");
//        return adt;
//    }
//
//    public static void main(String[] args) throws IOException, HL7Exception {
//        HttpHl7MessageBody body = new HttpHl7MessageBody();
//        body.setHost("172.16.1.163");
//        body.setPort(9004);
//        Map<String, String> me = new HashMap<>();
//        me.put("sequence_number", "121312121");
//        body.setContent(me);
//        Message message = body.getMergePatientMessage();
//        Message response = SendAndReceiveAMessage.build().buildAddess("172.16.1.163", 9004).sendMessage(body.getAddPatientMessage());
//        System.out.println("");
//    }
}
