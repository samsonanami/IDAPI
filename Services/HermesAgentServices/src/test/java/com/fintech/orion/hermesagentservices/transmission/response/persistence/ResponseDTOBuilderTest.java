package com.fintech.orion.hermesagentservices.transmission.response.persistence;

import com.fintech.orion.common.exceptions.persistence.PersistenceException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by TharinduMP on 10/24/2016.
 * ResponsePersisterTest
 */
public class ResponseDTOBuilderTest {

    private ResponseDTOBuilder responseDTOBuilder;

    @Before
    public void setUp() throws Exception {
        responseDTOBuilder = new ResponseDTOBuilder();
    }

    @Test(expected = PersistenceException.class)
    public void shouldReturnExceptionGivenNullArguments() throws PersistenceException {
        responseDTOBuilder.build(null,null,0);
    }
}