package com.fintech.orion.messaging.job;

import com.fintech.orion.common.exceptions.job.JobHandlerException;
import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by TharinduMP on 10/11/2016.
 * Handler that manages dto object <--> message conversion
 */
public class JobHandler implements JobHandlerInterface {

    @Autowired
    private ValidatorFactory validatorFactory;

    @Override
    public Message createGenericMapMessageToMessage(Session session, GenericMapMessage genericMapMessage) throws JobHandlerException {
        try {
            validatorFactory.getValidator(genericMapMessage).validate(genericMapMessage);
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setInt("ClientId", genericMapMessage.getClientId());
            mapMessage.setInt("processingRequestId", genericMapMessage.getProcessingRequestId());
            return mapMessage;
        } catch (ValidatorException | JMSException e) {
            throw new JobHandlerException(e);
        }
    }

    @Override
    public GenericMapMessage createMapMessageToGenericMapMessage(MapMessage mapMessage) throws JobHandlerException {
        try {
            GenericMapMessage genericMapMessage = new GenericMapMessage();
            genericMapMessage.setProcessingRequestId(mapMessage.getInt("processingRequestId"));
            genericMapMessage.setClientId(mapMessage.getInt("ClientId"));
            validatorFactory.getValidator(genericMapMessage).validate(genericMapMessage);
            return genericMapMessage;
        } catch (ValidatorException | JMSException e) {
            throw new JobHandlerException(e);
        }

    }
}
