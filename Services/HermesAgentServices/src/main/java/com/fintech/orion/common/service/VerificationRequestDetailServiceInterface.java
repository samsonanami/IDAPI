package com.fintech.orion.common.service;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;
import com.fintech.orion.dto.response.api.ImageDetail;
import com.fintech.orion.dto.response.api.VerificationProcessDetail;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by sasitha on 12/19/16.
 *
 */
@Repository
public interface VerificationRequestDetailServiceInterface {

    ProcessingRequest getProcessingRequest(String processingRequestId) throws ItemNotFoundException;

    void saveResponse(String rawResponse, String processedResponse, Process process);

    void saveFinalVerificationResponse(String verificationResponse, String verificationRequestCode, String clientName);

    boolean isVerificationProcessFoundInProcessingRequest(String processingRequestCode, String processType);

    List<Process> getProcessListBelongsToProcessingRequest(String processingRequestCode);

    List<Process> getProcessListBelongsToProcessingRequest(String processingRequestCode, List<String> processTypes);

    ProcessType getProcessTypeFromProcessCode(String processIdentificationCode);

    void updateProcessDetails(List<Process> processList);

    List<ImageDetail> getResourceOfProcessingRequest(String processingRequestCode);

    List<VerificationProcessDetail> getVerificationProcessDetails(String processingRequestCode);

}
