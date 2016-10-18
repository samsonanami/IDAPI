package com.fintech.orion.hermes.listener;

import com.fintech.orion.hermes.handler.JobManagerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by TharinduMP on 10/10/2016.
 * Generic Hermes Listener for Jobs in the queue.
 */
public class HermesListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(HermesListener.class);

    @Autowired
    private JobManagerInterface jobManager;

    @Override
    public void onMessage(Message message) {
        LOGGER.info("{} received a message", getClass().getName());

    }
}
