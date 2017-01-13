package com.fintech.orion.common.service;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sasitha on 12/19/16.
 *
 */
@Repository
public interface VerificationRequestDetailServiceInterface {

    ProcessingRequest getProcessingRequest(String processingRequestId) throws ItemNotFoundException;

    void saveResponse(String rawResponse, String processedResponse, Process process);

    boolean isVerificationProcessFoundInProcessingRequest(String processingRequestCode, String processType);

    List<Process> getProcessListBelongsToProcessingRequest(String processingRequestCode);

    ProcessType getProcessTypeFromProcessCode(String processIdentificationCode);

}
