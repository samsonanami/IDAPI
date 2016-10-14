package com.fintech.orion.hermesagentservices.transmission.request;

import com.fintech.orion.hermesagentservices.transmission.request.type.RequestInterface;

/**
 * Created by TharinduMP on 10/13/2016.
 * Request Provider Factory
 * This is handled by Spring completely.
 */
@FunctionalInterface
public interface RequestFactoryServiceInterface {
    RequestInterface getRequest(String processType);
}
