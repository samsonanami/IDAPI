package com.fintech.orion.hermes.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by TharinduMP on 10/10/2016.
 * Generic Hermes Listener for Jobs in the queue.
 */
public class HermesListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(HermesListener.class);

    @Override
    public void onMessage(Message message) {
        LOGGER.info("{} received a message", getClass().getName());


    }
}
