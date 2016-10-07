package com.fintech.orion.messaging.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.SessionHandlerException;
import com.fintech.orion.common.exceptions.DestinationHandlerException;
import com.fintech.orion.common.exceptions.job.JobProducerException;
import com.fintech.orion.messaging.connection.DestinationHandlerInterface;
import com.fintech.orion.messaging.connection.SessionHandlerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;
import java.util.Map;

public class JobProducer implements JobProducerInterface {

    @Autowired
    private SessionHandlerInterface sessionHandler;

    @Autowired
    private DestinationHandlerInterface destinationHandler;

    static final Logger LOGGER = LoggerFactory.getLogger(JobProducer.class);
    private static final long MESSAGE_TIME_TO_LIVE_MILLISECONDS = 0;

    private String queueName;
    private Session session;
    private Destination destination;
    private MessageProducer producer;

    public JobProducer(String queueName) {
        this.queueName = queueName;
        initializeJobProducer();
    }

    /**
     * Produce a job to a provided destination
     * @param jobData job information
     * @throws JobProducerException
     */
    @Override
    public void produceJob(Map<String, Object> jobData) throws JobProducerException {
        try {
            LOGGER.info("Started Producing a Job");
            String message = objectMapping(jobData);
            TextMessage messageToSend = session.createTextMessage(message);
            producer.send(messageToSend);
            LOGGER.info("Job Produced Successfully and sent the following message : {}", messageToSend);
        } catch (JMSException e) {
            LOGGER.error("Producing the Job failed", e);
            throw new JobProducerException(e);
        } catch (JsonProcessingException e) {
            LOGGER.error("Parsing the jobData of the job failed", e);
            throw new JobProducerException(e);
        }
    }

    private void initializeJobProducer() {
        try {
            establishSession();
            establishDestination();
            establishProducer();
        } catch (DestinationHandlerException e) {
            LOGGER.error("Messaging queue destination creation failed", e);
        } catch (SessionHandlerException e) {
            LOGGER.error("Messaging queue session creation failed", e);
        } catch (JMSException e) {
            LOGGER.error("Messaging queue producer creation failed", e);
        }
    }

    private void establishSession() throws SessionHandlerException {
        this.session = sessionHandler.establishSession();
        LOGGER.info("Messaging queue session created successfully");
    }

    private void establishDestination() throws DestinationHandlerException {
        this.destination = destinationHandler.provideDestination(session, queueName);
        LOGGER.info("Messaging queue destination created successfully");
    }

    private void establishProducer() throws JMSException {
        LOGGER.info("Messaging queue producer creation started");
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        producer.setTimeToLive(MESSAGE_TIME_TO_LIVE_MILLISECONDS);
        LOGGER.info("Messaging queue producer created successfully");
    }

    private String objectMapping(Map<String, Object> jobData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jobData);
    }

}
