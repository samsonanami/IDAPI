package com.fintech.orion.api.service.io.file;


import com.fintech.orion.api.service.exceptions.PersistenceException;
import com.fintech.orion.dto.persistence.Destination;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorFactoryInterface;
import com.fintech.orion.dto.validator.ValidatorInterface;
import com.fintech.orion.dto.validator.ValidatorResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.when;

/**
 * Created by TharinduMP on 10/28/2016.
 * LocalFilePersistenceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class LocalFilePersistenceTest {

    @Mock
    private MultipartFile sourceFile;

    @Mock
    private Destination destination;

    @Mock
    private ValidatorFactoryInterface validatorFactory;

    @Mock
    private ValidatorInterface validatorInterface;

    @InjectMocks
    private LocalFilePersistence localFilePersistence;

    @Test(expected = PersistenceException.class)
    public void shouldReturnExceptionGivenSourceFileIsNull() throws Exception {
        localFilePersistence.persistTo(null, destination);
    }

    @Test(expected = PersistenceException.class)
    public void shouldReturnExceptionGivenDestinationFileNameIsNull() throws Exception {
        destination.setDestinationPath("C:\\");
        when(validatorFactory.getValidator("destinationValidator")).thenReturn(validatorInterface);
        when(validatorInterface.validate(destination)).thenThrow(ValidatorException.class);
        localFilePersistence.persistTo(sourceFile, destination);
    }

    @Test
    public void shouldReturnTrueWhenAllOk() throws Exception {
        destination.setDestinationPath("C:\\");
        when(validatorFactory.getValidator("destinationValidator")).thenReturn(validatorInterface);
        when(validatorInterface.validate(destination)).thenReturn(new ValidatorResult());
        Assert.assertTrue(localFilePersistence.persistTo(sourceFile, destination));
    }
}