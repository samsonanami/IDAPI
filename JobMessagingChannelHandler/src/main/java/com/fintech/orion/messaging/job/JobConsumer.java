package com.fintech.orion.messaging.job;

import com.fintech.orion.messaging.connection.DestinationHandlerInterface;
import com.fintech.orion.messaging.connection.SessionHandlerInterface;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

/**
 * Created by TharinduMP on 10/7/2016.
 * Implementation of Job Consumer
 */
public class JobConsumer extends JobCommon implements JobConsumerInterface {

    private MessageConsumer consumer;

    public JobConsumer(String queueName, DestinationHandlerInterface destinationHandler, SessionHandlerInterface sessionHandler) {
        super(queueName, destinationHandler, sessionHandler);
        establishConsumer();
    }

    @Override
    public void setJobConsumer(MessageListener messageListener) throws JMSException {
        LOGGER.info("Setting Consumer Message Listener");
        consumer.setMessageListener(messageListener);
        LOGGER.info("Setting Consumer Message Listener Successful", messageListener.getClass());
    }

    private void establishConsumer() {
        try {
            LOGGER.info("Messaging queue consumer creation started");
            this.consumer = session.createConsumer(destination);
            LOGGER.info("Messaging queue consumer created successfully");
        } catch (JMSException e) {
            LOGGER.error("Messaging queue consumer creation failed", e);
        }
    }
}
