package com.fintech.orion.hermesagentservices.resources;

import com.fintech.orion.common.exceptions.resource.Base64ServiceException;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by TharinduMP on 10/24/2016.
 * Base64ServiceTest
 */
public class Base64ServiceTest {

    private Base64Service base64Service;

    @Before
    public void setUp() throws Exception {
        base64Service = new Base64Service();
    }

    @Test(expected = Base64ServiceException.class)
    public void shouldReturnExceptionGivenStreamIsNull() throws Base64ServiceException {
        InputStream a = null;
        base64Service.fileToBase64(a);
    }

}