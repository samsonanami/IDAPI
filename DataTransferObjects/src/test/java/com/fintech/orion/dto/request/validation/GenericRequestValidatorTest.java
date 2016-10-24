package com.fintech.orion.dto.request.validation;

import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.validator.ValidatorException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by TharinduMP on 10/17/2016.
 * GenericRequestValidatorTest
 */
public class GenericRequestValidatorTest {

    private GenericRequestValidator genericRequestValidator;
    private GenericRequest genericRequest;

    @Before
    public void setUp() throws ValidatorException {
        genericRequestValidator = new GenericRequestValidator();
        genericRequest = new GenericRequest();
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionGivenNullObject() throws ValidatorException {
        genericRequestValidator.validate(null);
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionWhenClientIdIsNull() throws ValidatorException {
        genericRequest.setIdentificationCode("12567863");
        genericRequest.setProcessType(1);
        genericRequest.setProcessId(2);
        genericRequestValidator.validate(genericRequest);
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionWhenIdentificationCodeIsNull() throws ValidatorException {
        genericRequest.setLicenseId(123);
        genericRequest.setProcessType(1);
        genericRequest.setProcessId(2);
        genericRequestValidator.validate(genericRequest);
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionWhenProcessTypeIsNull() throws ValidatorException {
        genericRequest.setLicenseId(123);
        genericRequest.setProcessId(23424);
        genericRequest.setIdentificationCode("156245623");
        genericRequestValidator.validate(genericRequest);
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionWhenProcessIdIsNull() throws ValidatorException {
        genericRequest.setLicenseId(123);
        genericRequest.setProcessType(1);
        genericRequest.setIdentificationCode("12567567");
        genericRequestValidator.validate(genericRequest);
    }
}