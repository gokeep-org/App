package com.app.hl7;

import ca.uhn.hl7v2.model.Message;
import org.omg.CORBA.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HL7EventContainer {
    public static final Map<String, Map<Long, String>> result = new ConcurrentHashMap<>();
    public static final Logger logger = LoggerFactory.getLogger(HL7EventContainer.class);

    public static final void addResult(String mshName, Long time, String response){
        Map<Long, String> event = new HashMap<>();
        event.put(time, response);
        result.put(mshName, event);
    }

    public static final void delResultByMshName(String msh){
        try{
            result.remove(msh);
        }catch (Throwable e){
            logger.error("delete reponse result fail, ", e.getMessage());
        }
    }

    public static final Map<Long, String> getEventByMshName(String mshhName){
        return result.get(mshhName);
    }

    public static final void process(Message requestMessage, String response){
        String name = requestMessage.getName();
        Map<Long, String> middleMap = getEventByMshName(name);
        if (Objects.isNull(middleMap)){
            addResult(name, new Date().getTime(), response);
            return;
        }
        middleMap.put(new Date().getTime(), response);
    }
}
