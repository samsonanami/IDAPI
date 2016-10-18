package com.fintech.orion.hermes.processor;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.request.RequestProcessDTO;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorFactoryInterface;
import com.fintech.orion.mapping.request.GenericRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TharinduMP on 10/11/2016.
 * Main Request Processing Class.
 */
public class RequestProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    @Autowired
    private ValidatorFactoryInterface validatorFactory;

    @Autowired
    private GenericRequestMapper genericRequestMapper;

    public GenericRequest createGenericRequest(GenericMapMessage genericMapMessage, RequestProcessDTO process) throws ValidatorException {
        try {
            LOGGER.trace("starting createGenericRequest");
            //validate genericMapMessage
            validatorFactory.getValidator(genericMapMessage).validate(genericMapMessage);

            //validate RequestProcessDTO
            validatorFactory.getValidator(process).validate(process);

            return genericRequestMapper.mapMessageAndRequestProcessToGenericRequest(process, genericMapMessage);
        } finally {
            LOGGER.trace("createGenericRequest completed");
        }
    }

}
