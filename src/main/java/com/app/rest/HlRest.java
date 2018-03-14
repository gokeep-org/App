package com.app.rest;

import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.PipeParser;
import com.app.hl7.*;

import com.app.hl7.task.SyncTimeFromNtp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/hl7")
public class HlRest {
    private static final Logger logger = LoggerFactory.getLogger(HlRest.class);

    @Autowired
    SyncTimeFromNtp syncTimeFromNtp;
    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String test(HttpServletRequest servletContextEvent){
        return "main";
    }

    @RequestMapping(path = "/rid", method = RequestMethod.GET)
    public String rid(HttpServletRequest servletContextEvent){
        return "rid";
    }


    @RequestMapping(path = "/ct", method = RequestMethod.GET)
    public String ct(HttpServletRequest servletContextEvent){
        return "ct";
    }

    @ResponseBody
    @RequestMapping(path = "/flush", method = RequestMethod.POST)
    public Map<Long, String> flushConsol(){
        Map<Long, String> events = new HashMap();
        String outputLog = null;
        try{
            outputLog = HL7EventContainer.queue.poll();
        }catch (Throwable e){
            logger.error("No poll value fromm queue, {}", e.getMessage());
        }
        outputLog = StringUtils.isEmpty(outputLog) ? "no_value" : outputLog;
        events.put(new Date().getTime(), outputLog);
        return events;
    }

    @ResponseBody
    @RequestMapping(path = "/sync_date", method = RequestMethod.POST)
    public Map<String, String> ct(@RequestParam(value = "ntp",  defaultValue = "cn.ntp.org.cn") String ntpServerName){
//        String serverTime = syncTimeFromNtp.execute(ntpServerName);
        String ntpServerTime = NtpUtil.getDateTimeFromNtpServer(ntpServerName);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = dateFormat.format(new Date());
        Map<String, String> res = new HashMap<>();
//        int offsetIndex = serverTime.indexOf("offset");
//        Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
//        String serverTimeValue = p.matcher(serverTime).group();
        res.put("server", ntpServerTime);
        res.put("local", nowDate);
//        res.put("log", ntpServerTime);
//        new SyncTimeFromNtp().start();
//        HL7EventContainer.queue.offer("From ntp server "+ntpServerName+" receiver time string is " + ntpServerTime+", now date is: "+NtpUtil.smartFormtTime(new Date().getTime()));
        return res;
    }

//
//    public Map<String, String> startSync(){
//
//    }



//    @ResponseBody
//    @RequestMapping(path = "/clean", method = RequestMethod.DELETE)
//    public void cleanResult(@RequestParam(value = "name", defaultValue = "all") String name){
//        if (name.equalsIgnoreCase("all")){
//           HL7EventContainer.result.clear();
//            return;
//        }
//        HL7EventContainer.delResultByMshName(name);
//    }

//    @ResponseBody
//    @RequestMapping(path = "/send/{name}", method = RequestMethod.POST)
//    public void sendMessageToHl7Server(@PathVariable("name") String messageType){
//        if (StringUtils.isEmpty(messageType)){
//            return;
//        }
//        switch (messageType.toUpperCase()){
//            case "ADT_A01": sendADTA01Message();
//                break;
//            case "V231": sendV231Message();
//                break;
//        }
//    }

    @ResponseBody
    @RequestMapping(value = "/proxy", method = RequestMethod.POST)
    public Map<String, String> proxySendMessage(
            @RequestBody Map<String, Object> body
    ){
        Map<String, String> result = new HashMap<>();
        result.put("res", null);
        try{
            String host = body.get("host").toString();
            int port = Integer.parseInt(body.get("port").toString());
            String message = body.get("message").toString();
            SendAndReceiveAMessage sendClient = SendAndReceiveAMessage.build().buildAddess(host, port);
            Message response = sendClient.sendMessage(message);
            result.put("res", new PipeParser().encode(response));
        }catch (Throwable e){
            result.put("res", "proxy send error");
            logger.error("proxy send hl7 message find fail");
            return result;
        }
        return result;
    }

//    private void sendV231Message() {
//        SendAndReceiveAMessage.build().sendByCode(Hl7Config.V231());
//    }
//
//    public void sendADTA01Message(){
//        SendAndReceiveAMessage.build().sendByCode(Hl7Config.ADT_A01());
//    }

//    /**
//     *
//     * @return
//     */
//    @RequestMapping(value = "/user", method = RequestMethod.POST)
//    public Map<String, Object> addUser(){
//        User user = new User();
//        user.setUsername("徐宁");
//        userService.addUser(user);
//        Map<String, Object> result = new HashMap<>();
//        result.put("success", true);
//        return result;
//    }
    @RequestMapping(path = "/client", method = RequestMethod.GET)
    public String client(){
        return "client";
    }

}
