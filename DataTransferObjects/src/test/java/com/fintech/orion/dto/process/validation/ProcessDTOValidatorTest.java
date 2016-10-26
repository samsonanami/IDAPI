package com.fintech.orion.dto.process.validation;

import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by TharinduMP on 10/26/2016.
 * ProcessDTOValidatorTest
 */
public class ProcessDTOValidatorTest {

    private ProcessDTOValidator processDTOValidator;
    private ProcessDTO processDTO;
    private ProcessTypeDTO processTypeDTO;
    private ProcessingStatusDTO processingStatusDTO;

    @Before
    public void setUp() throws Exception {
        processDTOValidator = new ProcessDTOValidator();
        processDTO = new ProcessDTO();
        processTypeDTO = new ProcessTypeDTO();
        processingStatusDTO = new ProcessingStatusDTO();
    }

    @Test(expected = ValidatorException.class)
    public void shouldReturnExceptionGivenNullObject() throws ValidatorException {
        processDTOValidator.validate(null);
    }

    @Test(expected = ValidatorException.class)
    public void shouldReturnExceptionGivenObjectInstanceIsNotMatching() throws ValidatorException {
        String a = "test";
        processDTOValidator.validate(a);
    }

    @Test(expected = ValidatorException.class)
    public void shouldReturnExceptionGivenProcessDTOIdIsNull() throws Exception {
        processTypeDTO.setId(1);
        processingStatusDTO.setId(1);
        processDTO.setProcessIdentificationCode("123123");
        processDTO.setProcessTypeDTO(processTypeDTO);
        processDTO.setProcessingStatusDTO(processingStatusDTO);
        processDTOValidator.validate(processDTO);
    }

    @Test(expected = ValidatorException.class)
    public void shouldReturnExceptionGivenProcessDTOIdentificationIsNull() throws Exception {
        processTypeDTO.setId(1);
        processDTO.setId(1);
        processingStatusDTO.setId(1);
        processDTO.setProcessTypeDTO(processTypeDTO);
        processDTO.setProcessingStatusDTO(processingStatusDTO);
        processDTOValidator.validate(processDTO);
    }

    @Test(expected = ValidatorException.class)
    public void shouldReturnExceptionGivenProcessTypeIsNull() throws Exception {
        processTypeDTO.setId(1);
        processDTO.setId(1);
        processDTO.setProcessIdentificationCode("123123");
        processingStatusDTO.setId(1);
        processDTO.setProcessingStatusDTO(processingStatusDTO);
        processDTOValidator.validate(processDTO);
    }

    @Test(expected = ValidatorException.class)
    public void shouldReturnExceptionGivenProcessingStatusIsNull() throws Exception {
        processTypeDTO.setId(1);
        processDTO.setId(1);
        processDTO.setProcessIdentificationCode("123123");
        processingStatusDTO.setId(1);
        processDTO.setProcessTypeDTO(processTypeDTO);
        processDTOValidator.validate(processDTO);
    }

    @Test
    public void shouldReturnAValidationResultGivenAllAreCorrect() throws Exception {
        processTypeDTO.setId(1);
        processDTO.setId(1);
        processDTO.setProcessIdentificationCode("123123");
        processingStatusDTO.setId(1);
        processDTO.setProcessTypeDTO(processTypeDTO);
        processDTO.setProcessingStatusDTO(processingStatusDTO);
        processDTOValidator.validate(processDTO);
    }
}