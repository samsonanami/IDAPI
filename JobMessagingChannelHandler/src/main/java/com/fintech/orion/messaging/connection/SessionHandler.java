package com.fintech.orion.messaging.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fintech.orion.common.exceptions.SessionHandlerException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class SessionHandler implements SessionHandlerInterface {

    static final Logger LOGGER = LoggerFactory.getLogger(SessionHandler.class);
    private static final Boolean NON_TRANSACTED = false;

    private ConnectionFactory connectionFactory;

    public SessionHandler(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Session establishSession() throws SessionHandlerException {
        try {
            LOGGER.info("Starting Messaging Queue Session Creation");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            return connection.createSession(NON_TRANSACTED, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            throw new SessionHandlerException(e);
        }
    }
}
