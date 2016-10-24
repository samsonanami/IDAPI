package com.fintech.orion.hermes.delegate;

import com.fintech.orion.common.exceptions.RequestWorkerException;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.hermesagentservices.transmission.request.type.RequestInterface;

/**
 * Created by TharinduMP on 10/17/2016.
 * RequestDelegationInterface
 */
@FunctionalInterface
public interface RequestDelegationInterface {
    void delegateRequest(GenericRequest genericRequest, RequestInterface request) throws RequestWorkerException;
}
