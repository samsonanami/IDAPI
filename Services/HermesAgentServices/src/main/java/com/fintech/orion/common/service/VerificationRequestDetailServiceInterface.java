package com.fintech.orion.common.service;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.hermesagentservices.transmission.payload.model.Oracle.response.OcrResponse;
import org.springframework.stereotype.Repository;

/**
 * Created by sasitha on 12/19/16.
 *
 */
@Repository
public interface VerificationRequestDetailServiceInterface {

    ProcessingRequest getProcessingRequest(String processingRequestId) throws ItemNotFoundException;

    void saveRawResponse(String rawResponse, Process process);
}
