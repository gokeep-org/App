package com.app;

import com.app.service.TestService;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openempi.ics.utility.JdbcHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppStart.class)
@ActiveProfiles(value = "dev")
public class AppTest {
    @Autowired
    private TestService testService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);
    
    @Before
    public void testBeforeEvent(){
        LOGGER.info("Junit test is starting");
    }
    
    @Test
    public void testInfo(){
        String res = testService.start();
        LOGGER.info(res);
        Assert.assertEquals(res, "This is a rest test interface");
    }
    
    @After
    public void testAfterEvent(){
        LOGGER.info("Junit test is end");
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = JdbcHelper.getConnection();
        connection.close();
    }

//    @Test
//    public void testStartHl7Server(){
//        startPixPdqSocketServer(null);
//    }
//
//    public static void startPixPdqSocketServer(String iheConfigPath){
//        String configPath = StringUtils.isEmpty(iheConfigPath) ? "IheActors.xml" : iheConfigPath;
//        ConfigurationLoader loader = ConfigurationLoader.getInstance();
//        try {
//            loader.loadConfiguration(configPath, true);
//        } catch (IheConfigurationException e) {
//            System.out.println("ERROR: IHE Test server is disable");
//        }
//        System.out.println("SUCCESS: IHE Test server is enable");
//    }
}
