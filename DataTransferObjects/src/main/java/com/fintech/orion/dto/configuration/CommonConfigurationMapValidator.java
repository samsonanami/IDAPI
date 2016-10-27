package com.fintech.orion.dto.configuration;

import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorInterface;
import com.fintech.orion.dto.validator.ValidatorResult;
import com.fintech.orion.dto.validator.validations.MapValidations;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by TharinduMP on 10/18/2016.
 * CommonConfigurationMapValidator
 */
@Component
public class CommonConfigurationMapValidator implements ValidatorInterface {

    @Override
    public ValidatorResult validate(Object o) throws ValidatorException {
        if(o != null && o instanceof Map) {
            Map<String, String> map = (Map<String, String>) o;

            //validations for the keys
            MapValidations.doesContainKeyWithNonEmptyValue(map,"url");
            MapValidations.doesContainKeyWithNonEmptyValue(map,"header.contentType");
        }
        return new ValidatorResult();
    }
}
