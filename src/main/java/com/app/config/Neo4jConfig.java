package com.app.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Neo4jConfig {

     @Bean
     public org.neo4j.ogm.config.Configuration getConfiguration() {

         org.neo4j.ogm.config.Configuration.Builder  builder = new org.neo4j.ogm.config.Configuration.Builder();
         builder.credentials("neo4j", "zhongfu@2021");
         builder.uri("bolt://localhost:7687");
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
