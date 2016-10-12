package com.fintech.orion.dto.validator;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.messaging.validation.GenericMapMessageValidator;

/**
 * Created by TharinduMP on 10/12/2016.
 * Main Validator provider factory class.
 */
public class ValidatorFactory implements ValidatorFactoryInterface {

    @Override
    public ValidatorInterface getValidator(Object dtoObject) throws ValidatorException {
        if(dtoObject instanceof GenericMapMessage) {
            return new GenericMapMessageValidator();
        } else {
            throw new ValidatorException("Could not find a valid Validator for the provided object");
        }
    }
}
