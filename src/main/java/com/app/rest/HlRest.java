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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/hl7")
public class HlRest {
    private static final Logger logger = LoggerFactory.getLogger(HlRest.class);
//    @Autowired
//    private UserService userService;
    @Autowired
    SyncTimeFromNtp syncTimeFromNtp;
    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String test(HttpServletRequest servletContextEvent){
//        ListenHL7Socket socket = (ListenHL7Socket) servletContextEvent.getAttribute(HlServerListener.KEYWORD_SOCKET);
        return "main";
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
    @RequestMapping(path = "/ct", method = RequestMethod.POST)
    public Map<String, String> ct(@RequestParam(value = "ntp_server",  defaultValue = "cn.ntp.org.cn") String ntpServerName){
        syncTimeFromNtp.execute();
        String ntpServerTime = NtpUtil.getDateTimeFromNtpServer(ntpServerName);
        Map<String, String> res = new HashMap<>();
        res.put("res", ntpServerTime);
        HL7EventContainer.queue.offer("From ntp server "+ntpServerName+" receiver time string is " + ntpServerTime+", now date is: "+NtpUtil.smartFormtTime(new Date().getTime()));
        return res;
    }

//    @ResponseBody
//    @RequestMapping(path = "/clean", method = RequestMethod.DELETE)
//    public void cleanResult(@RequestParam(value = "name", defaultValue = "all") String name){
//        if (name.equalsIgnoreCase("all")){
//            HL7EventContainer.result.clear();
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
            result.put("res", message);
            SendAndReceiveAMessage sendClient = SendAndReceiveAMessage.build().buildAddess(host, port);
            Message message1 = sendClient.sendMessage(message);
            result.put("res", new PipeParser().encode(message1));
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
