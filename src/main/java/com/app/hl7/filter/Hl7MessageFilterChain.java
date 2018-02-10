package com.app.hl7.filter;

import ca.uhn.hl7v2.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Hl7MessageFilterChain<T extends Message> {
    private static final Logger logger = LoggerFactory.getLogger(Hl7MessageFilterChain.class);
    private int index = 0;
    private List<Hl7MessageFilter> chains = new ArrayList<>();
    private Hl7MessageFilterChain(){
    }

    public static Hl7MessageFilterChain build(){
        Hl7MessageFilterChain chain = new Hl7MessageFilterChain();
        chain.chains.addAll(
                Arrays.asList(new ADT_A01MessageProcess())
        );
        return chain;
    }

    /**
     * 调用责任链处理消息
     * @param message
     * @return
     */
    public T doFilter(T message){
        this.chains.stream().forEach(chain->{
            chain.process(message);
        });
        return message;
    }






}
