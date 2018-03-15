package com.app.hl7.bean;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.parser.Parser;
import com.app.hl7.Hl7ComonConfig;
import com.app.hl7.SendAndReceiveAMessage;

import java.io.IOException;
import java.util.Date;

public class MessageGenerate extends BasicMessage{
    public MessageGenerate (){}


}