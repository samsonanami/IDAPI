package com.fintech.orion.hermesagentservices.transmission.request.basetype;

import com.fintech.orion.common.exceptions.RequestSubmitterException;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.mashape.unirest.request.BaseRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by TharinduMP on 10/17/2016.
 * RequestCreatorInterface
 * Common interface to provide configurations, resources and any extras (optional) required by the request creator.
 */
@FunctionalInterface
public interface RequestCreatorInterface {

    BaseRequest createRequest(Map<String,String> configurations, List<ResourceDTO> resourceList, Map extras) throws RequestSubmitterException;
}
