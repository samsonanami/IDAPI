package com.fintech.orion;

import com.fintech.orion.helper.FileExtensionValidator;
import com.fintech.orion.helper.FileExtensionValidatorInterface;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileExtensionValidatorTest {

    @Test
    public void shouldReturnTrueForJPEGFileExtension() {
        FileExtensionValidatorInterface fileExtensionValidatorInterface = new FileExtensionValidator();
        assertTrue(fileExtensionValidatorInterface.validate("JPEG"));
    }

    @Test
    public void shouldReturnTrueForJpegFileExtension() {
        FileExtensionValidatorInterface fileExtensionValidatorInterface = new FileExtensionValidator();
        assertTrue(fileExtensionValidatorInterface.validate("jpeg"));
    }

    @Test
    public void shouldReturnTrueForMp4UpperFileExtension() {
        FileExtensionValidatorInterface fileExtensionValidatorInterface = new FileExtensionValidator();
        assertTrue(fileExtensionValidatorInterface.validate("MP4"));
    }

    @Test
    public void shouldReturnTrueForMp4FileExtension() {
        FileExtensionValidatorInterface fileExtensionValidatorInterface = new FileExtensionValidator();
        assertTrue(fileExtensionValidatorInterface.validate("mp4"));
    }

    @Test
    public void shouldReturnFalseForTxtFileExtension() {
        FileExtensionValidatorInterface fileExtensionValidatorInterface = new FileExtensionValidator();
        assertFalse(fileExtensionValidatorInterface.validate("txt"));
    }

}
