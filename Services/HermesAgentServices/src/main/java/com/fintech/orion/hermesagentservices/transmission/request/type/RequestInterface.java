package com.fintech.orion.hermesagentservices.transmission.request.type;

import com.fintech.orion.dto.request.GenericRequest;

/**
 * Created by TharinduMP on 10/13/2016.
 */
public interface RequestInterface {
    void process(GenericRequest genericRequest);
}
