package com.fintech.orion.hermesagentservices.transmission.request.basetype;

import com.fintech.orion.common.exceptions.RequestSubmitterException;

/**
 * Created by TharinduMP on 10/17/2016.
 */
public interface RequestSubmitterInterface {

    void submitRequest() throws RequestSubmitterException;
}
