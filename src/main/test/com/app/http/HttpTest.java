package com.app.http;

import com.app.dtu.bean.model.ParseToEntityAdapter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpTest {
    private static final Logger logger = LoggerFactory.getLogger(HttpTest.class);

    @Test
    public void testWarnList(){
        String d = ParseToEntityAdapter.httpClient.get("http://www.baidu.com").text();
        System.out.println(d);
    }
}
