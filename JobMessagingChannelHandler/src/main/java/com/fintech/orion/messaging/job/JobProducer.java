package com.fintech.orion.messaging.job;

import com.fintech.orion.common.exceptions.job.JobProducerException;
import com.fintech.orion.messaging.connection.DestinationHandlerInterface;
import com.fintech.orion.messaging.connection.SessionHandlerInterface;

import javax.jms.*;

/**
 * Created by TharinduMP on 10/7/2016.
 * Implementation of Job Producer
 */
public class JobProducer extends JobCommon implements JobProducerInterface, JobCommonInterface {

    private MessageProducer producer;
    private static final long MESSAGE_TIME_TO_LIVE_MILLISECONDS = 0;

    public JobProducer(String queueName, DestinationHandlerInterface destinationHandler, SessionHandlerInterface sessionHandler) {
        super(queueName, destinationHandler, sessionHandler);
        establishProducer();
    }

    /**
     * Produce a job to a provided destination
     * @param message job information
     * @throws JobProducerException
     */
    @Override
    public void sendJob(Message message) throws JobProducerException {
        try {
            LOGGER.info("Started Producing a Job");
            producer.send(message);
            LOGGER.info("Job Produced Successfully and sent the following message : {}", message.toString());
        } catch (JMSException e) {
            LOGGER.error("Producing the Job failed", e);
            throw new JobProducerException(e);
        }
    }

    private void establishProducer(){
        try {
            LOGGER.info("Messaging queue producer creation started");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.setTimeToLive(MESSAGE_TIME_TO_LIVE_MILLISECONDS);
            LOGGER.info("Messaging queue producer created successfully");
        }  catch (JMSException e) {
            LOGGER.error("Messaging queue producer creation failed", e);
        }
    }

}
