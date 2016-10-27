package com.fintech.orion.hermes.processor;

import com.fintech.orion.common.exceptions.MessageProcessorException;
import com.fintech.orion.dto.messaging.GenericMapMessage;

import javax.jms.Message;

/**
 * Created by TharinduMP on 10/11/2016.
 * MessageProcessorInterface
 */
@FunctionalInterface
public interface MessageProcessorInterface {
    GenericMapMessage processMessage(Message message) throws MessageProcessorException;
}
