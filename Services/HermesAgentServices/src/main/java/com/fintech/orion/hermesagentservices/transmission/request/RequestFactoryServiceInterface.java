package com.fintech.orion.hermesagentservices.transmission.request;

import com.fintech.orion.hermesagentservices.transmission.request.type.RequestInterface;

/**
 * Created by TharinduMP on 10/13/2016.
 * Request Provider Factory Service
 * This is handled by Spring completely.
 * The class provides a set of classes necessary to build the request, make request and handle the request.
 */
@FunctionalInterface
public interface RequestFactoryServiceInterface {
    RequestInterface getRequest(String processType);
}