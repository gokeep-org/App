package com.app;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by xuning on 2018/1/23.
 */
public class ClientTest {
    MongoTemplate mongoTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ClientTest.class);
    @Before
    public void init() {
        Mongo mongo = new Mongo("localhost", 27017);
        mongoTemplate = new MongoTemplate(mongo, "scrapy");
    }

    @Test
    public void testClientOperation() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("employId").is("2").and("employ.no").is("2").and("employ.list.emsId").is("5"));
        query.addCriteria(criteria);
        List map = mongoTemplate.find(query, Map.class, "test");
        logger.info(map.toString());
    }

    @After
    public void destory() {
    }
}
