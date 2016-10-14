package com.fintech.orion.dto.messaging.validation;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by TharinduMP on 10/13/2016.
 * Test Suite for GenericMapMessageValidator
 */
public class GenericMapMessageValidatorTest {

    private GenericMapMessageValidator genericMapMessageValidator;
    private GenericMapMessage genericMapMessage;

    @Before
    public void setUp() {
        genericMapMessageValidator = new GenericMapMessageValidator();
        genericMapMessage = new GenericMapMessage();
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionGivenANullObject() throws ValidatorException {
        Object o = null;
        genericMapMessageValidator.validate(o);
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionWhenObjectIsNotInstanceOfSameClass() throws ValidatorException {
        Object o = "test";
        genericMapMessageValidator.validate(o);
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionWhenClientIdIsNull() throws ValidatorException {
        genericMapMessage.setIdentificationCode("123123");
        genericMapMessageValidator.validate(genericMapMessage);
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionWhenProcessingRequestIsNull() throws ValidatorException {
        genericMapMessage.setIdentificationCode("45636");
        genericMapMessage.setClientId(425624);
        genericMapMessageValidator.validate(genericMapMessage);
    }

    @Test
    public void shouldReturnResultWhenAllDetailsAreCorrect() throws ValidatorException {
        genericMapMessage.setClientId(4243562);
        genericMapMessage.setIdentificationCode("567484568");
        ValidatorResult result = genericMapMessageValidator.validate(genericMapMessage);
        assertFalse("Has No Errors",result.hasErrors());
    }
}