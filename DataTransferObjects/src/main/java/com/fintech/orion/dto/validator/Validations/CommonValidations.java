package com.fintech.orion.dto.validator.validations;

import com.fintech.orion.dto.validator.ValidatorException;

/**
 * Created by TharinduMP on 10/12/2016.
 * Common Validations
 */
public class CommonValidations {

    private static final String DEFAULT_IS_NULL_EX_MESSAGE = " object is null";

    private CommonValidations() {
    }

    public static <T> T notNull(final T object, final String objectName) throws ValidatorException {
        if (object == null) {
            throw new ValidatorException(objectName + DEFAULT_IS_NULL_EX_MESSAGE);
        }
        return object;
    }
}
