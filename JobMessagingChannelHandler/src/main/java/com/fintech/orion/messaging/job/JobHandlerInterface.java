package com.fintech.orion.messaging.job;

import com.fintech.orion.dto.messaging.GenericMapMessage;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by TharinduMP on 10/11/2016.
 */
public interface JobHandlerInterface {

    Message createGenericMapMessageToMessage(Session session, GenericMapMessage genericMapMessage) throws JMSException;

    GenericMapMessage createMapMessageToGenericMapMessage(MapMessage mapMessage) throws JMSException;
}
