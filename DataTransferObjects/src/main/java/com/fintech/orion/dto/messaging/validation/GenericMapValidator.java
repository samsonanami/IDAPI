package com.fintech.orion.dto.messaging.validation;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by TharinduMP on 10/11/2016.
 * Main Validation Class of Generic Map Message DTO
 */
public class GenericMapValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return GenericMapMessage.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"processingRequestId","processingRequestId.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"clientId","clientId.required");
    }
}
