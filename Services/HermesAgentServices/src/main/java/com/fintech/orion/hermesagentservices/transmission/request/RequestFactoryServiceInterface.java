package com.fintech.orion.hermesagentservices.transmission.request;

import com.fintech.orion.hermesagentservices.transmission.request.type.RequestInterface;

/**
 * Created by TharinduMP on 10/13/2016.
 * Request Provider Factory
 */
@FunctionalInterface
public interface RequestFactoryServiceInterface {
    RequestInterface getRequest(String processType);
}
