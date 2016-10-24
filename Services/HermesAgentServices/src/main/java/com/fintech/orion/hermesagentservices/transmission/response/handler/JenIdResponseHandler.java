package com.fintech.orion.hermesagentservices.transmission.response.handler;


import com.fintech.orion.common.exceptions.response.ResponseHandlerException;
import com.fintech.orion.coreservices.ProcessingStatusServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.response.ResponseDTO;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.JenID;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenidresultstring.JenIDResultString;
import com.fintech.orion.hermesagentservices.transmission.response.mapper.GenericMapperInterface;
import com.fintech.orion.hermesagentservices.transmission.response.persistence.ResponsePersisterInterface;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


/**
 * Created by TharinduMP on 10/19/2016.
 * JenIdResponseHandler
 * this needs to handle the response and the final outcome for the response.
 */
public class JenIdResponseHandler implements ResponseHandlerInterface {

    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JenIdResponseHandler.class);

    @Autowired
    private GenericMapperInterface genericMapper;

    @Autowired
    private ProcessingStatusServiceInterface processingStatusService;

    @Autowired
    private ResponsePersisterInterface responsePersister;

    @Override
    public ProcessDTO handleResponse(HttpResponse<JsonNode> response, ProcessDTO processDTO) throws ResponseHandlerException {

        try {
            if(response.getStatus() == 200) {

                //get response mapped to jen id model
                String rawJson = response.getBody().toString();
                JenID jenID = genericMapper.createMappedJsonObject(rawJson, JenID.class);
                JenIDResultString jenIDResultString = genericMapper.createMappedJsonObject(jenID.getOutputData().getResultString(), JenIDResultString.class);

                //convert to saving format
                String extractedJson = genericMapper.createJSONStringFromObject(jenIDResultString);

                //save response
                ResponseDTO responseDTO = responsePersister.save(rawJson, extractedJson, processDTO.getId());
                processDTO.setResponseDTO(responseDTO);

                //set status to complete
                processDTO.setProcessingStatusDTO(processingStatusService.findByStatus(Status.PROCESSING_COMPLETE));

            } else {
                // save the error into the process response
                ResponseDTO responseDTO = responsePersister.save(response.getBody().toString(), "", processDTO.getId());
                processDTO.setResponseDTO(responseDTO);

                // log it on logger
                LOGGER.error("Response Code was not 200.");
                LOGGER.error("Response Code : {} . Response : {}", response.getStatus(), response.getBody());
            }
        } catch (IOException e) {
            LOGGER.error("Jen ID Response parsing failed.");
            throw new ResponseHandlerException(e);

        } catch (ItemNotFoundException e) {
            LOGGER.error("Could not find ProcessingComplete Status in processing status table.");
            throw new ResponseHandlerException(e);
        }
        return processDTO;
    }
}
