package com.fintech.orion.hermesagentservices.transmission.request.type;

import com.fintech.orion.common.exceptions.RequestException;
import com.fintech.orion.dto.request.GenericRequest;

/**
 * Created by TharinduMP on 10/13/2016.
 * RequestInterface
 */
public interface RequestInterface {
    void process(GenericRequest genericRequest) throws RequestException;
}
