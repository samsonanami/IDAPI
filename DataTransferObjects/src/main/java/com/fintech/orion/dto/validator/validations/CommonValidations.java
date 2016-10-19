package com.fintech.orion.dto.validator.validations;

import com.fintech.orion.dto.validator.ValidatorException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by TharinduMP on 10/12/2016.
 * Common Validations
 */
public class CommonValidations {



    private CommonValidations() {
    }

    public static void notNull(final Object object, final String objectName) throws ValidatorException {
        if (object == null) {
            throw new ValidatorException(objectName + ValidationMessages.DEFAULT_IS_NULL_EX_MESSAGE);
        }
    }

    public static void notBlank(final String chars, final String objectName) throws ValidatorException {
        notNull(chars,objectName);
        if(StringUtils.isBlank(chars)) {
            throw new ValidatorException(objectName + ValidationMessages.DEFAULT_IS_EMPTY_EX_MESSAGE);
        }
    }
}
