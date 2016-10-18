package com.fintech.orion.dto.request.validation;

import com.fintech.orion.dto.request.RequestProcessDTO;
import com.fintech.orion.dto.validator.ValidatorException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by TharinduMP on 10/17/2016.
 */
public class RequestProcessDTOValidatorTest {

    private RequestProcessDTO requestProcessDTO;
    private RequestProcessDTOValidator requestProcessDTOValidator;

    @Before
    public void setUp() throws Exception {
        requestProcessDTOValidator = new RequestProcessDTOValidator();
        requestProcessDTO = new RequestProcessDTO();
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionGivenObjectIsNull() throws Exception {
        requestProcessDTOValidator.validate(null);
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionGivenProcessIdIsNull() throws Exception {
        requestProcessDTO.setProcessType(0);
        requestProcessDTOValidator.validate(requestProcessDTO);
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionGivenProcessTypeIsNull() throws Exception {
        requestProcessDTO.setProcessId(2456);
        requestProcessDTOValidator.validate(requestProcessDTO);
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowExceptionGivenObjectIsNotAProperInstance() throws Exception {
        String a = "asd";
        requestProcessDTOValidator.validate(a);
    }
}