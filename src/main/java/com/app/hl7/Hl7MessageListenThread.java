//package com.app.hl7;
//
//import org.openhealthexchange.openpixpdq.ihe.PixPdqServer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//public class Hl7MessageListenThread extends Thread{
//    private static final Logger logger = LoggerFactory.getLogger(Hl7MessageListenThread.class);
//    @Override
//    public void run() {
//        if (Hl7ComonConfig.ENABLE_PIX_PDQ_SERVER){
//            PixPdqServer.startPixPdqSocketServer(null);
//        }
//    }
//}