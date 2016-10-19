package com.fintech.orion.dto.request.validation;

import com.fintech.orion.dto.messaging.validation.GenericMapMessageValidator;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorInterface;
import com.fintech.orion.dto.validator.ValidatorResult;
import com.fintech.orion.dto.validator.validations.CommonValidations;

/**
 * Created by TharinduMP on 10/14/2016.
 * GenericRequestValidator
 */
public class GenericRequestValidator extends GenericMapMessageValidator implements ValidatorInterface {

    @Override
    public ValidatorResult validate(Object o) throws ValidatorException {
        super.validate(o);
        if(o != null && o instanceof GenericRequest) {
            GenericRequest genericRequest = (GenericRequest) o;
            CommonValidations.notNull(genericRequest.getProcessId(),"processId");
            CommonValidations.notNull(genericRequest.getProcessType(),"processType");
        } else {
            throw new ValidatorException("Object is null or Object is not an instanceof GenericRequest");
        }
        return new ValidatorResult();
    }
}
