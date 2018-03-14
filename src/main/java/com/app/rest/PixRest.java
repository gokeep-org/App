package com.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


/**
 * 增删改只返回消息的发送与接收的消息日志
 */
@Controller
@RequestMapping("/pix")
public class PixRest {
    private static final Logger logger = LoggerFactory.getLogger(PixRest.class);

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(){
        return "pix_main";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePatient(Map<String, Object> info){

        return null;
    }


}
