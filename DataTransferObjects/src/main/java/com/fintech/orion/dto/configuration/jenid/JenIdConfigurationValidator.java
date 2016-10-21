package com.fintech.orion.dto.configuration.jenid;

import com.fintech.orion.dto.configuration.CommonConfigurationMapValidator;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorInterface;
import com.fintech.orion.dto.validator.ValidatorResult;
import com.fintech.orion.dto.validator.validations.MapValidations;

import java.util.Map;

/**
 * Created by TharinduMP on 10/19/2016.
 * JenIdConfigurationValidator
 */
public class JenIdConfigurationValidator extends CommonConfigurationMapValidator implements ValidatorInterface{

    @Override
    public ValidatorResult validate(Object o) throws ValidatorException {
        super.validate(o);

        // no rechecking since superclass has already done that.
        Map<String, String> map = (Map<String, String>) o;

        //further validations
        MapValidations.doesContainKeyWithNonEmptyValue(map,"header.authorization");
        MapValidations.doesContainKeyWithNonEmptyValue(map,"queryString.synchronous");

        //check if synchronous is true
        if("false".equals(map.get("queryString.synchronous"))) {
            throw new ValidatorException("queryString.synchronous was false. Asynchronous is not supported");
        }

        MapValidations.doesContainKeyWithNonEmptyValue(map,"body.clientID");

        return new ValidatorResult();
    }
}
