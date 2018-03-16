package com.app.rest;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import com.app.hl7.Hl7Util;
import com.app.hl7.SendAndReceiveAMessage;
import com.app.hl7.bean.HttpHl7MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 增删改只返回消息的发送与接收的消息日志
 */
@Controller
@RequestMapping("/pix")
public class PixRest {
    private static final Logger logger = LoggerFactory.getLogger(PixRest.class);

    @RequestMapping(path = "/feed", method = RequestMethod.GET)
    public String index() {
        return "pixfeed";
    }
    @RequestMapping(path = "/feed-v2", method = RequestMethod.GET)
    public String indexV2(){
        return "pixfeed-v2";
    }


    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> savePatient(@RequestBody Map<String, String> info) {
//        MSH|^~\&|CHAOSI|CHAOSI_CLIENT|hinacom-supplier|hinacom|20180315154202.883+0800||ADT^A01^ADT_A01|2475|P|2.5|121312121
//        PID|33333|4f60f69d-6f4c-440b-a19e-ae8f3688b4bc|||^aaaa|||M|||&天通苑街道^^n1^^^中国||||||^222^111|||444444|||2010-01-11 12:34:11|||||^^sssss||||||||^^111111|4444
        String PIX_SOURCE_ITI8_A01 = "MSH|^~\\&|CHAOSI|CHAOSI_CLIENT|" + info.get("receiver_application") + "|" + info.get("facility_name") + "|" + dateFormat.format(new Date()) + "||ADT^A01^ADT_A01|NIST-101004144709009|P|2.3.1\r" +
                "EVN||" + dateFormat.format(new Date()) + "\r" +
                "PID|||" + info.get("id") + "^^^" + info.get("domain") + "||" + info.get("first_name") + "^" + info.get("last_name") + "^^^^^" + info.get("sex") + "|BARNES^^^^^^L|" + info.get("birth") + "|M|||820 JORIE BLVD^^" + info.get("address") + "^NY^10503||||||||" + info.get("sequence_number") + "\r" +
                "PV1||I";
        Hl7Util.writeLog(PIX_SOURCE_ITI8_A01);
        Message response = SendAndReceiveAMessage.build().buildAddess(info.get("host"), Integer.parseInt(info.get("port"))).sendMessage(PIX_SOURCE_ITI8_A01);
        try {
            Hl7Util.writeLog(response.encode());
        } catch (HL7Exception e) {
            e.printStackTrace();
        }
        Map<String, String> res = new HashMap<>();
        res.put("in", PIX_SOURCE_ITI8_A01);
        try {
            res.put("out", response.encode());
        } catch (HL7Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updatePatient(@RequestBody Map<String, String> info) {


        String PIX_SOURCES_ITI_8_A08 = "MSH|^~\\&|CHAOSI|CHAOSI_CLIENT|" + info.get("receiver_application") + "|" + info.get("facility_name") + "|" + dateFormat.format(new Date()) + "||ADT^A08^ADT_A01|NIST-090527135016929|P|2.3.1\r" +
                "EVN||" + dateFormat.format(new Date()) + "\r" +
                "PID|||" + info.get("id") + "^^^" + info.get("domain") + "||" + info.get("first_name") + "^" + info.get("last_name") + "^^^^^" + info.get("sex") + "||" + dateFormat.format(new Date()) + "||||202 KEN HABOR^^" + info.get("address") + "^NY^61000\r" +
                "PV1||R";
        Hl7Util.writeLog("Request: \n" + PIX_SOURCES_ITI_8_A08);
        Message response = SendAndReceiveAMessage.build().buildAddess(info.get("host"), Integer.parseInt(info.get("port"))).sendMessage(PIX_SOURCES_ITI_8_A08);
        try {
            Hl7Util.writeLog("Response: \n" + response.encode());
        } catch (HL7Exception e) {
            e.printStackTrace();
        }
        Map<String, String> res = new HashMap<>();
        res.put("in", PIX_SOURCES_ITI_8_A08);
        try {
            res.put("out", response.encode());
        } catch (HL7Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        String PIX_SOURCES_ITI_8_A08 = "MSH|^~\\&|CHAOSI|CHAOSI_CLIENT|" + "receiver_application"+ "|" + "facility_name" + "|" + dateFormat.format(new Date()) + "||ADT^A08^ADT_A01|NIST-090527135016929|P|2.3.1\r" +
                "EVN||" + dateFormat.format(new Date()) + "\r" +
                "PID|||" + "patient_idtinfy" + "^^^" + "domain" + "||" + "first_name" + "^" + "last_name" + "^^^^^" + "sex" + "||" + dateFormat.format(new Date()) + "||||202 KEN HABOR^^" + "address" + "^NY^61000\r" +
                "PV1||R";
        logger.info(PIX_SOURCES_ITI_8_A08);
    }


    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> mergePatient(@RequestBody Map<String, String> info) {
        String PIX_SOURCES_ITI_8_A40 = "MSH|^~\\&|CHAOSI|CHAOSI_CLIENT|" + info.get("receiver_application") + "|" + info.get("facility_name") + "|" + dateFormat.format(new Date()) + "||ADT^A40^ADT_A39|NIST-090528110022806|P|2.3.1\r" +
                "EVN||" + dateFormat.format(new Date()) + "\r" +
                "PID|||" + info.get("id") + "^^^" + info.get("domain") + "||" + info.get("first_name") + "^" + info.get("last_name") + "^^^^^L\r" +
                "MRG|" + info.get("rep_id") + "^^^" + info.get("domain") + "||||||" + info.get("first_name") + "^" + info.get("last_name") + "^^^^^L\r";
        Hl7Util.writeLog(PIX_SOURCES_ITI_8_A40);
        Message response = SendAndReceiveAMessage.build().buildAddess(info.get("host"), Integer.parseInt(info.get("port"))).sendMessage(PIX_SOURCES_ITI_8_A40);
        try {
            Hl7Util.writeLog(response.encode());
        } catch (HL7Exception e) {
            e.printStackTrace();
        }
        Map<String, String> res = new HashMap<>();
        res.put("in", PIX_SOURCES_ITI_8_A40);
        try {
            res.put("out", response.encode());
        } catch (HL7Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 添加病人
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/add_patient", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addePatient(@RequestBody HttpHl7MessageBody info) {
        Message inMessage = info.getAddYCPatientMessage();
        Hl7Util.writeHl7MessageLog((inMessage));
        Message response = SendAndReceiveAMessage.build().buildAddess(info.getHost(), info.getPort()).sendMessage(inMessage);

        Map<String, String> res = new HashMap<>();
        try {
            res.put("in", inMessage.encode());
            res.put("out", response.encode());
        } catch (HL7Exception e) {
            e.printStackTrace();
        }finally {
            logger.info("Request: \n"+inMessage);
            Hl7Util.writeHl7MessageLog(inMessage);
            Hl7Util.writeHl7MessageLog(response);
        }
        return res;
    }

    @RequestMapping(value = "/update_patient", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updatePatient(@RequestBody HttpHl7MessageBody info) {
        Message inMessage = info.getUpdatePatientMessage();
        Hl7Util.writeHl7MessageLog((inMessage));
        Message response = SendAndReceiveAMessage.build().buildAddess(info.getHost(), info.getPort()).sendMessage(inMessage);
        Map<String, String> res = new HashMap<>();
        try {
            res.put("in", inMessage.encode());
            res.put("out", response.encode());
        } catch (HL7Exception e) {
            e.printStackTrace();
        } finally {
            Hl7Util.writeHl7MessageLog(inMessage);
            Hl7Util.writeHl7MessageLog(response);
        }
        return res;
    }



    @RequestMapping(value = "/merge_patient", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> mergePatient(@RequestBody HttpHl7MessageBody info) throws IOException, HL7Exception {
        Message inMessage = info.getMergePatientMessage();
        Hl7Util.writeHl7MessageLog((inMessage));
        Message response = SendAndReceiveAMessage.build().buildAddess(info.getHost(), info.getPort()).sendMessage(inMessage);
        Map<String, String> res = new HashMap<>();
        try {
            res.put("in", inMessage.encode());
            res.put("out", response.encode());
        } catch (HL7Exception e) {
            e.printStackTrace();
        } finally {
            Hl7Util.writeHl7MessageLog(inMessage);
            Hl7Util.writeHl7MessageLog(response);
        }
        return res;
    }

    // 支持PDQ查询
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> pixClient(@RequestBody HttpHl7MessageBody body){
        return null;
    }
}
