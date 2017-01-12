package com.fintech.orion.hermes.listener;


import com.fintech.orion.hermes.orchestrator.VerificationOrchestrator;
import com.fintech.orion.jobchanel.consumer.MessageDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by TharinduMP on 10/10/2016.
 * Generic Hermes Listener for Jobs in the queue.
 */
public class HermesListener implements MessageDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(HermesListener.class);

    @Autowired
    private VerificationOrchestrator orchestrator;


    @Override
    public void delegateMessage(Serializable message) {
        LOGGER.debug("Received message from job chanel {}", message);
        orchestrator.orchestrate(message);
    }
}
