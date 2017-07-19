package com.fintech.orion.hermesagentservices.transmission.request.body.builder;


import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import static org.junit.Assert.*;

/**
 * Created by sasitha on 12/23/16.
 */
public class RequestBodyBuilderFactoryTest {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void should_return_correct_request_body_builder_object() throws Exception{
        RequestBodyBuilderFactory requestBodyBuilderFactory = new RequestBodyBuilderFactory();
        OracleRequestBodyBuilder oracleRequestBodyBuilder = (OracleRequestBodyBuilder) requestBodyBuilderFactory.getRequestBodyBuilder(RequestBodyType.ORACLE);
        Assert.notNull(oracleRequestBodyBuilder);
    }

}