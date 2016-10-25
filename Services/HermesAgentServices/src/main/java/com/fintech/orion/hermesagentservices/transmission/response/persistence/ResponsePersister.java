package com.fintech.orion.hermesagentservices.transmission.response.persistence;

import com.fintech.orion.common.exceptions.persistence.PersistenceException;
import com.fintech.orion.coreservices.ResponseServiceInterface;
import com.fintech.orion.dto.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TharinduMP on 10/24/2016.
 * Class responsible for persisting the Response Object
 */
public class ResponsePersister implements ResponsePersisterInterface {

    @Autowired
    private ResponseServiceInterface responseService;

    @Override
    public ResponseDTO save(String rawJson, String extractedJson, int processId) throws PersistenceException {
        if(rawJson != null && extractedJson != null) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRawJson(rawJson);
            responseDTO.setExtractedJson(extractedJson);
            responseDTO.setProcessId(processId);

            //save response
            responseService.saveOrUpdate(responseDTO);

            //return response in case the ID is needed
            return responseDTO;

        } else {
            throw new PersistenceException("One of the arguments provided is null.");
        }

    }
}
