package com.fintech.orion.messaging.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fintech.orion.common.exceptions.ConnectionHandlerException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by TharinduMP on 10/6/2016.
 */
public class ConnectionHandler implements ConnectionHandlerInterface {

    static final Logger LOGGER = LoggerFactory.getLogger(ConnectionHandler.class);
    private static final Boolean NON_TRANSACTED = false;

    private ConnectionFactory connectionFactory;
    private Connection connection;

    public ConnectionHandler(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Session establishSession() throws ConnectionHandlerException {
        try {
            LOGGER.debug("Starting Messaging Queue Session Creation");
            connection = connectionFactory.createConnection();
            connection.start();
            return connection.createSession(NON_TRANSACTED, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            throw new ConnectionHandlerException(e);
        }
    }
}
