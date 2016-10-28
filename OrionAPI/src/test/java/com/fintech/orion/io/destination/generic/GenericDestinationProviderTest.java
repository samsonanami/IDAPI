package com.fintech.orion.io.destination.generic;

import com.fintech.orion.common.exceptions.DestinationProviderException;
import com.fintech.orion.dto.persistence.Destination;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by TharinduMP on 10/28/2016.
 * GenericDestinationProviderTest
 */

@RunWith(MockitoJUnitRunner.class)
public class GenericDestinationProviderTest {

    @InjectMocks
    private GenericDestinationProvider genericDestinationProvider;

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(genericDestinationProvider,"workingDir","C:\\");

    }

    @Test(expected = DestinationProviderException.class)
    public void shouldReturnExceptionWhenFileNameIsNull() throws Exception {
        genericDestinationProvider.provide(null);
    }

    @Test
    public void shouldReturnAValidDestinationGivenAFileName() throws Exception {
        Destination destination = genericDestinationProvider.provide("abc.txt");
        Assert.assertTrue(destination.getFileName().equals("abc.txt"));
        Assert.assertTrue(destination.getDestinationPath().equals("C:\\"));
    }
}