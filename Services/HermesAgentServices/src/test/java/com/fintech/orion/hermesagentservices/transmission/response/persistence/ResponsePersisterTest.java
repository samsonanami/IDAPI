package com.fintech.orion.hermesagentservices.transmission.response.persistence;

import com.fintech.orion.common.exceptions.persistence.PersistenceException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by TharinduMP on 10/24/2016.
 * ResponsePersisterTest
 */
public class ResponsePersisterTest {

    private ResponsePersister responsePersister;

    @Before
    public void setUp() throws Exception {
        responsePersister = new ResponsePersister();
    }

    @Test(expected = PersistenceException.class)
    public void shouldReturnExceptionGivenNullArguments() throws PersistenceException {
        responsePersister.save(null,null,0);
    }
}