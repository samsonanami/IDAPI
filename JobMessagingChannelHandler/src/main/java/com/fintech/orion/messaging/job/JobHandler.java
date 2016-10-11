package com.fintech.orion.messaging.job;

import com.fintech.orion.dto.messaging.GenericMapMessage;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by TharinduMP on 10/11/2016.
 * Handler that manages dto object <--> message conversion
 */
public class JobHandler implements JobHandlerInterface {

    @Override
    public Message createGenericMapMessageToMessage(Session session, GenericMapMessage genericMapMessage) throws JMSException {
        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setInt("ClientId",genericMapMessage.getClientId());
        mapMessage.setInt("processingRequestId",genericMapMessage.getProcessingRequestId());
        return mapMessage;
    }

    @Override
    public GenericMapMessage createMapMessageToGenericMapMessage(MapMessage mapMessage) throws JMSException {
        GenericMapMessage genericMapMessage = new GenericMapMessage();
        genericMapMessage.setProcessingRequestId(mapMessage.getInt("processingRequestId"));
        genericMapMessage.setClientId(mapMessage.getInt("ClientId"));
        return genericMapMessage;
    }
}
