package com.fintech.orion.hermesagentservices.transmission.response.persistence;

import com.fintech.orion.common.exceptions.persistence.PersistenceException;
import com.fintech.orion.dto.response.ResponseDTO;

/**
 * Created by TharinduMP on 10/24/2016.
 * ResponsePersisterInterface
 */
@FunctionalInterface
public interface ResponseDTOBuilderInterface {
    ResponseDTO build(String rawJson, String extractedJson, int processId) throws PersistenceException;
}
