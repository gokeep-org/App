package com.app.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.app.action.factory.TestActionFactory;
import com.app.config.MyLog;
import com.app.domain.output.test.TestInfoOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestRest {
    private static final Logger logger = LoggerFactory.getLogger(TestRest.class);
//    @MyLog
    @GetMapping("/info")
    public TestInfoOutput test(@QueryParam("flag") boolean flag) throws Exception {
        return TestActionFactory.getTestInfoAction(flag).execute();
    }

    @PostMapping("/hook")
    public String hookTest(Map<String, Object> body){
        if (body != null)
            logger.info("receiver result is{}", body.toString());
        return "receiver hook result";
    }
}
