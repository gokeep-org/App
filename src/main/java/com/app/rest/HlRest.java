package com.app.rest;

import ca.uhn.hl7v2.model.Message;
import com.app.hl7.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/hl7")
public class HlRest {
    private static final Logger logger = LoggerFactory.getLogger(HlRest.class);

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String test(HttpServletRequest servletContextEvent){
        ListenHL7Socket socket = (ListenHL7Socket) servletContextEvent.getAttribute(HlServerListener.KEYWORD_SOCKET);
        return "main";
    }

    @ResponseBody
    @RequestMapping(path = "/flush", method = RequestMethod.POST)
    public Map<Long, String> flushConsol(@RequestParam(value = "name", defaultValue = "ADT_01") String name){
        Map<Long, String> result = new HashMap<>();
        Map<Long, String> events = HL7EventContainer.getEventByMshName(name);
        if (Objects.isNull(events)){
            return result;
        }
        events.entrySet().stream().forEach(event->{
            result.put(event.getKey(), event.getValue());
        });
        return result;
    }

    @ResponseBody
    @RequestMapping(path = "/clean", method = RequestMethod.DELETE)
    public void cleanResult(@RequestParam(value = "name", defaultValue = "all") String name){
        if (name.equalsIgnoreCase("all")){
            HL7EventContainer.result.clear();
            return;
        }
        HL7EventContainer.delResultByMshName(name);
    }

    @ResponseBody
    @RequestMapping(path = "/send/{name}", method = RequestMethod.POST)
    public void sendMessageToHl7Server(@PathVariable("name") String messageType){
        if (StringUtils.isEmpty(messageType)){
            return;
        }
        switch (messageType.toUpperCase()){
            case "ADT_A01": sendADTA01Message();
                break;
            case "V231": sendV231Message();
                break;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/proxy", method = RequestMethod.POST)
    public Map<String, String> proxySendMessage(
//            @QueryParam("host") String host,
//            @QueryParam("port") Integer port,
//            @QueryParam("message") String message,
            @RequestBody Map<String, Object> body
    ){
        Map<String, String> result = new HashMap<>();
        result.put("res", null);
        try{
            String host = body.get("host").toString();
            int port = Integer.parseInt(body.get("port").toString());
            String message = body.get("message").toString();
            SendAndReceiveAMessage sendClient = SendAndReceiveAMessage.build().buildAddess(host, port);
            Message responseMessage = sendClient.sendByCode(message);
            String code  =  sendClient.parseMessage(responseMessage);
            result.put("res", code);
        }catch (Throwable e){
            result.put("res", "proxy send error");
            logger.error("proxy send hl7 message find fail");
            return result;
        }
        return result;
    }

    private void sendV231Message() {
        SendAndReceiveAMessage.build().sendByCode(Hl7Config.V231());
    }

    public void sendADTA01Message(){
        SendAndReceiveAMessage.build().sendByCode(Hl7Config.ADT_A01());
    }
}
