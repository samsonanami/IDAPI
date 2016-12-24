package com.fintech.orion.hermesagentservices.transmission.request.body.builder;

import java.util.Map;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public interface RequestBodyBuilder {

    String getRequestBody(Map<String, Object> bodyContent);
}
