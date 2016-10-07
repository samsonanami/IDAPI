package com.fintech.orion.messaging.connection;

import com.fintech.orion.common.exceptions.DestinationHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by TharinduMP on 10/7/2016.
 */
public class DestinationHandler implements DestinationHandlerInterface {
    static final Logger LOGGER = LoggerFactory.getLogger(DestinationHandler.class);

    @Override
    public Destination provideDestination(Session session, String queueName) throws DestinationHandlerException {
        try {
            LOGGER.info("Messaging queue destination creation started");
            return session.createQueue(queueName);
        } catch (JMSException e) {
            throw new DestinationHandlerException(e);
        }
    }
}
