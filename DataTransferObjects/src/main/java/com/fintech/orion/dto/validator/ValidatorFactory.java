package com.fintech.orion.dto.validator;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.messaging.validation.GenericMapMessageValidator;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.request.validation.GenericRequestValidator;

/**
 * Created by TharinduMP on 10/12/2016.
 * Main Validator provider factory class.
 */
public class ValidatorFactory implements ValidatorFactoryInterface {

    @Override
    public ValidatorInterface getValidator(Object dtoObject) throws ValidatorException {
        if (dtoObject.getClass().equals(GenericMapMessage.class)) {
            return new GenericMapMessageValidator();
        } else if (dtoObject.getClass().equals(GenericRequest.class)) {
            return new GenericRequestValidator();
        } else {
            throw new ValidatorException("Could not find a valid Validator for the provided object");
        }
    }
}
