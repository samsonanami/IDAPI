package com.fintech.orion.hermesagentservices.transmission.response.persistence;

import com.fintech.orion.common.exceptions.persistence.PersistenceException;
import com.fintech.orion.dto.response.ResponseDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by TharinduMP on 10/24/2016.
 * Class responsible for building Response DTO
 */
public class ResponseDTOBuilder implements ResponseDTOBuilderInterface {

    @Override
    @Transactional
    public ResponseDTO build(String rawJson, String extractedJson, int processId) throws PersistenceException {
        if(rawJson != null && extractedJson != null) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRawJson(rawJson);
            responseDTO.setExtractedJson(extractedJson);
            responseDTO.setProcessId(processId);

            //return response in case the ID is needed
            return responseDTO;

        } else {
            throw new PersistenceException("One of the arguments provided is null.");
        }

    }
}
