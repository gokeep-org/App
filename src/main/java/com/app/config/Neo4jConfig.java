package com.app.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Neo4jConfig {

    @Value("${neo4j.uri}")
    private String uri;
    @Value("${neo4j.username}")
    private String username;
    @Value("${neo4j.password}")
    private String password;

     @Bean
     public org.neo4j.ogm.config.Configuration getConfiguration() {

         org.neo4j.ogm.config.Configuration.Builder  builder = new org.neo4j.ogm.config.Configuration.Builder();
         builder.credentials(username, password);
         builder.uri(uri);
         return builder.build();
    }

    @Bean
    public SessionFactory sessionFactory() {
               // Return the session factory which also includes the persistent entities
               return new SessionFactory(getConfiguration(), "com/app/entities");
    }


    @Bean
    public Neo4jTransactionManager transactionManager(){
        return new Neo4jTransactionManager(sessionFactory());
    }
}
