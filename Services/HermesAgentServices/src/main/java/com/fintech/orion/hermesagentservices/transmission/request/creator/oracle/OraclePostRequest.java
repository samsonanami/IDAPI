package com.fintech.orion.hermesagentservices.transmission.request.creator.oracle;

import com.fintech.orion.common.exceptions.request.RequestSubmitterException;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.hermesagentservices.transmission.request.basetype.RequestCreatorInterface;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.BaseRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 12/19/16.
 *
 */
public class OraclePostRequest implements RequestCreatorInterface {

    @Override
    public BaseRequest createRequest(Map<String, String> configurations, List<ResourceDTO> resourceList, Map extras) throws RequestSubmitterException {
        String body = (String) extras.get("body");

        return Unirest.post(configurations.get("url"))
                .header("Content-Type", configurations.get("header.contentType"))
                .body(body);
    }
}
