package com.fintech.orion.messaging.connection;

import com.fintech.orion.common.exceptions.DestinationHandlerException;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by TharinduMP on 10/7/2016.
 */
public interface DestinationHandlerInterface {

    Destination provideDestination(Session session, String queueName) throws DestinationHandlerException;
}
