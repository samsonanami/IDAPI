package com.fintech.orion.hermes.processor;

import com.fintech.orion.common.exceptions.MessageProcessorException;
import com.fintech.orion.common.exceptions.job.JobHandlerException;
import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.messaging.job.JobHandlerInterface;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.MapMessage;
import javax.jms.Message;

/**
 * Created by TharinduMP on 10/11/2016.
 * Process the message and provide the DTO Object
 */
public class MessageProcessor implements MessageProcessorInterface {

    @Autowired
    private JobHandlerInterface jobHandler;

    @Override
    public GenericMapMessage processMessage(Message message) throws MessageProcessorException {
        try {
            if (message != null && message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                return jobHandler.createMapMessageToGenericMapMessage(mapMessage);
            } else {
                throw new MessageProcessorException("Sent message is not of a MapMessage type or message is null");
            }
        } catch (JobHandlerException e) {
            throw new MessageProcessorException(e);
        }
    }
}
