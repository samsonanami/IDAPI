package com.fintech.orion.messaging.job;

import com.fintech.orion.common.exceptions.job.JobHandlerException;
import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorFactory;
import com.fintech.orion.dto.validator.ValidatorFactoryInterface;
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
    private ValidatorFactoryInterface validatorFactory;

    @Override
    public Message createGenericMapMessageToMessage(Session session, GenericMapMessage genericMapMessage) throws JobHandlerException {
        try {
            validatorFactory.getValidator("genericMapMessageValidator").validate(genericMapMessage);
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setInt("licenseId", genericMapMessage.getLicenseId());
            mapMessage.setString("identificationCode", genericMapMessage.getIdentificationCode());
            return mapMessage;
        } catch (ValidatorException | JMSException e) {
            throw new JobHandlerException(e);
        }
    }

    @Override
    public GenericMapMessage createMapMessageToGenericMapMessage(MapMessage mapMessage) throws JobHandlerException {
        try {
            GenericMapMessage genericMapMessage = new GenericMapMessage();
            genericMapMessage.setIdentificationCode(mapMessage.getString("identificationCode"));
            genericMapMessage.setLicenseId(mapMessage.getInt("licenseId"));
            validatorFactory.getValidator("genericMapMessageValidator").validate(genericMapMessage);
            return genericMapMessage;
        } catch (ValidatorException | JMSException e) {
            throw new JobHandlerException(e);
        }

    }
}
