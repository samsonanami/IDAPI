package com.fintech.orion.hermes.handler;

import com.fintech.orion.common.exceptions.RequestHandlerException;
import com.fintech.orion.dto.request.GenericRequest;

import java.util.List;

/**
 * Created by TharinduMP on 10/18/2016.
 * RequestHandlerInterface
 */
@FunctionalInterface
public interface RequestHandlerInterface {
    void handleRequests(List<GenericRequest> genericRequests) throws RequestHandlerException;
}
