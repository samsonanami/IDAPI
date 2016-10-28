package com.fintech.orion.dto.persistence.validation;

import com.fintech.orion.dto.persistence.Destination;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorInterface;
import com.fintech.orion.dto.validator.ValidatorResult;
import com.fintech.orion.dto.validator.validations.CommonValidations;
import org.springframework.stereotype.Component;

/**
 * Created by TharinduMP on 10/28/2016.
 */
@Component
public class DestinationValidator implements ValidatorInterface {
    @Override
    public ValidatorResult validate(Object o) throws ValidatorException {
        if (o != null && o instanceof Destination) {
            Destination destination = (Destination) o;
            CommonValidations.notBlank(destination.getDestinationPath(), "destination.destinationPath");
            CommonValidations.notBlank(destination.getFileName(), "destination.fileName");

        } else {
            throw new ValidatorException("Object is null or Object is not an instanceof ProcessDTO");
        }
        return new ValidatorResult();
    }
}

