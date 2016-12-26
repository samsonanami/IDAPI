package com.fintech.orion.hermesagentservices.transmission.request.body.builder;

import com.fintech.orion.dto.hermese.model.Oracle.VerificationResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 12/23/16.
 *
 */
public class OracleRequestBodyBuilderTest {

    OracleRequestBodyBuilder requestBodyBuilder;
    Map<String, Object> bodyContent;
    @Before
    public void setup() throws Exception{
        requestBodyBuilder = new OracleRequestBodyBuilder();
        bodyContent =  new HashMap<>();
    }

    @Test
    public void should_return_the_body_string_with_correct_content()throws Exception{
        VerificationResource resource1 = new VerificationResource();
        resource1.setResourceName("passport");
        resource1.setResourceId("12345");

        List<VerificationResource> resources = new ArrayList<>();
        resources.add(resource1);

        bodyContent.put("idVerification", resources);
        String bodyString = requestBodyBuilder.getRequestBody(bodyContent);

        Assert.notNull(bodyString);
    }

}