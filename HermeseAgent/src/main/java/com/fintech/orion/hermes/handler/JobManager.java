package com.fintech.orion.hermes.handler;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.hermes.processor.MessageProcessorInterface;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;

/**
 * Created by TharinduMP on 10/11/2016.
 * Class for Managing the Job. this includes processing the message,
 * delegating the message to validations and other job related tasks.
 */
public class JobManager {

    @Autowired
    private MessageProcessorInterface messageProcessor;

    public void delegateJob(Message message) throws Exception {

        //get processed DTO
        GenericMapMessage genericMapMessage = messageProcessor.processMessage(message);


    }
}
