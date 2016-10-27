package com.fintech.orion.hermesagentservices.transmission.payload.processor;

import com.fintech.orion.common.exceptions.payload.PayloadProcessingException;

import java.io.IOException;

/**
 * Created by TharinduMP on 10/26/2016.
 */
public interface ProcessorInterface {
    String marshall(Object payloadModel) throws PayloadProcessingException;

    String unmarshall(String stringPayload) throws IOException;
}
