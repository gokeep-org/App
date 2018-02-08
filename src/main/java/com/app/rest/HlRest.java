package com.app.rest;

import com.app.hl7.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    private void sendV231Message() {
        SendAndReceiveAMessage.build().sendByCode(Hl7Config.V231());
    }

    public void sendADTA01Message(){
        SendAndReceiveAMessage.build().sendByCode(Hl7Config.ADT_A01());
    }
}
