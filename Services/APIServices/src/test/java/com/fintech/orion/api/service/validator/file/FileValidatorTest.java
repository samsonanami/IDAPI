package com.fintech.orion.api.service.validator.file;

import com.fintech.orion.api.service.validator.file.FileValidator;
import com.fintech.orion.api.service.exceptions.FileValidatorException;
import com.fintech.orion.dto.validation.file.ValidatorStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by TharinduMP on 10/27/2016.
 * FileValidatorTest
 */
@RunWith(MockitoJUnitRunner.class)
public class FileValidatorTest {

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private Map<String, Integer> supportedFilesAndSizes;


    @InjectMocks
    private FileValidator fileValidator;

    @Test(expected = FileValidatorException.class)
    public void shouldReturnExceptionWhenFileIsNull() throws Exception {
        fileValidator.validateFile(null);
    }


    @Test
    public void shouldReturnFailWhenExtIsNotSupported() throws Exception {
        when(multipartFile.getOriginalFilename()).thenReturn("test.txt");
        when(supportedFilesAndSizes.containsKey("txt")).thenReturn(false);
        ValidatorStatus validatorStatus = fileValidator.validateFile(multipartFile);
        assertTrue(!validatorStatus.isPassed());
        assertTrue(validatorStatus.getMessage().equals("extension 'txt' is not supported."));
    }

    @Test
    public void shouldReturnFailWhenExtIsMissing() throws Exception {
        when(multipartFile.getOriginalFilename()).thenReturn("test");
        ValidatorStatus validatorStatus = fileValidator.validateFile(multipartFile);
        assertTrue(!validatorStatus.isPassed());
        assertTrue(validatorStatus.getMessage().equals("extension is missing in the provided file."));
    }

    @Test
    public void shouldReturnFailWhenFileIsBeyondSupportedSize() throws Exception {
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpeg");
        when(supportedFilesAndSizes.containsKey("jpeg")).thenReturn(true);
        when(supportedFilesAndSizes.get("jpeg")).thenReturn(500);
        when(multipartFile.getSize()).thenReturn(1000L);
        ValidatorStatus validatorStatus = fileValidator.validateFile(multipartFile);
        assertTrue(!validatorStatus.isPassed());
        assertTrue(validatorStatus.getMessage().equals("file size is beyond the supported. the limit for 'jpeg' is 500 bytes"));
    }

    @Test
    public void shouldReturnPassedWhenAllAreOk() throws Exception {
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpeg");
        when(supportedFilesAndSizes.containsKey("jpeg")).thenReturn(true);
        when(supportedFilesAndSizes.get("jpeg")).thenReturn(500);
        when(multipartFile.getSize()).thenReturn(100L);
        ValidatorStatus validatorStatus = fileValidator.validateFile(multipartFile);
        assertTrue(validatorStatus.isPassed());
    }

    @Test
    public void shouldReturnPassedWhenExtensionIsUpperCase() throws Exception {
        when(multipartFile.getOriginalFilename()).thenReturn("test.jPEg");
        when(supportedFilesAndSizes.containsKey("jpeg")).thenReturn(true);
        when(supportedFilesAndSizes.get("jpeg")).thenReturn(500);
        when(multipartFile.getSize()).thenReturn(100L);
        ValidatorStatus validatorStatus = fileValidator.validateFile(multipartFile);
        assertTrue(validatorStatus.isPassed());
    }
}