package com.fintech.orion.hermesagentservices.transmission.request.body;

import com.fintech.orion.common.exceptions.BodyServiceException;
import com.fintech.orion.dto.resource.ResourceDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by TharinduMP on 10/19/2016.
 * BodyServiceInterface
 */
@FunctionalInterface
public interface BodyServiceInterface {

    Object createJSONBody(Map<String,String> configurations, List<ResourceDTO> resourceList, Map extras) throws BodyServiceException;
}
