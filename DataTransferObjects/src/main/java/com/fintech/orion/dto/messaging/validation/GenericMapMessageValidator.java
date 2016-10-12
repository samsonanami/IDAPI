package com.fintech.orion.dto.messaging.validation;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.validator.validations.CommonValidations;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorInterface;
import com.fintech.orion.dto.validator.ValidatorResult;

/**
 * Created by TharinduMP on 10/12/2016.
 * GenericMapMessageValidator
 */
public class GenericMapMessageValidator implements ValidatorInterface {
    @Override
    public ValidatorResult validate(Object o) throws ValidatorException {
        if(o != null && o instanceof GenericMapMessage) {
            GenericMapMessage genericMapMessage = (GenericMapMessage) o;
            CommonValidations.notNull(genericMapMessage.getClientId(),"genericMapMessage.clientId");
            CommonValidations.notNull(genericMapMessage.getProcessingRequestId(), "genericMapMessage.processingRequestId");
        } else {
            throw new ValidatorException("Object is null or Object is not an instanceof GenericMapMessage");
        }
        return new ValidatorResult();
    }
}
