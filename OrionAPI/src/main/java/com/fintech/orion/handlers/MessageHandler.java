package com.fintech.orion.handlers;

import com.fintech.orion.common.exceptions.job.JobHandlerException;
import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.messaging.job.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;

/**
 * MessageHandler
 */
public class MessageHandler implements MessageHandlerInterface {

    @Autowired
    private JobHandlerInterface jobHandlerInterface;

    @Autowired
    private JobCommonInterface jobProducerInterface;

    @Override
    public Message getMessage(int id, String processingRequestId) throws JobHandlerException {
        GenericMapMessage mapMessage = new GenericMapMessage();
        mapMessage.setLicenseId(id);
        mapMessage.setIdentificationCode(processingRequestId);

        return jobHandlerInterface.createGenericMapMessageToMessage(jobProducerInterface.getSession(), mapMessage);
    }
}
