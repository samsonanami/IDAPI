package com.fintech.orion.messaging.job;

import com.fintech.orion.common.exceptions.DestinationHandlerException;
import com.fintech.orion.common.exceptions.SessionHandlerException;
import com.fintech.orion.messaging.connection.DestinationHandlerInterface;
import com.fintech.orion.messaging.connection.SessionHandlerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Destination;
import javax.jms.Session;

/**
 * Created by TharinduMP on 10/7/2016.
 * The class is responsible for providing the common functionality for Consumer and Producer
 */

public class JobCommon implements JobCommonInterface {

    static final Logger LOGGER = LoggerFactory.getLogger(JobCommon.class);

    private SessionHandlerInterface sessionHandler;
    private DestinationHandlerInterface destinationHandler;
    protected String queueName;
    protected Session session;
    protected Destination destination;

    public JobCommon(String queueName, DestinationHandlerInterface destinationHandler, SessionHandlerInterface sessionHandler) {
        this.queueName = queueName;
        this.destinationHandler = destinationHandler;
        this.sessionHandler = sessionHandler;
        initializeJobCommon();
    }

    @Override
    public Session getSession() {
        return session;
    }

    private void initializeJobCommon() {
        try {
            establishSession();
            establishDestination();
        } catch (DestinationHandlerException e) {
            LOGGER.error("Messaging queue destination creation failed", e);
        } catch (SessionHandlerException e) {
            LOGGER.error("Messaging queue session creation failed", e);
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

}
