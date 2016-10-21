package com.fintech.orion.hermesagentservices.transmission.request.creator.jenid;

import com.fintech.orion.common.exceptions.RequestSubmitterException;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.hermesagentservices.transmission.request.basetype.RequestCreatorInterface;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.BaseRequest;
import org.apache.http.annotation.ThreadSafe;

import java.util.List;
import java.util.Map;

/**
 * Created by TharinduMP on 10/17/2016.
 * JenId Post Synchronous Request Creator
 */
@ThreadSafe
public class JenIdPostSyncRequest implements RequestCreatorInterface {

    @Override
    public BaseRequest createRequest(Map<String,String> configurations, List<ResourceDTO> resourceList, Map extras) throws RequestSubmitterException {

            return Unirest.post(configurations.get("url"))
                    .header("Content-Type", configurations.get("header.contentType"))
                    .header("Authorization", configurations.get("header.authorization"))
                    .queryString("synchronous",configurations.get("queryString.synchronous"))
                    .body(extras.get("body"));
    }
}
