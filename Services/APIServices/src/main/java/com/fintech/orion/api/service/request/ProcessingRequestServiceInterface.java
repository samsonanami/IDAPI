package com.fintech.orion.api.service.request;

import com.fintech.orion.api.service.exceptions.ResourceAccessPolicyViolationException;
import com.fintech.orion.api.service.exceptions.ResourceNotFoundException;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.request.api.VerificationProcess;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;

import java.io.IOException;
import java.util.List;

public interface ProcessingRequestServiceInterface {

    String saveVerificationProcessData(String clientName, List<VerificationProcess> verificationProcessList);

    VerificationProcessDetailedResponse getDetailedResponse(String clientName, String verificationRequestId) throws IOException, ResourceAccessPolicyViolationException, ResourceNotFoundException;
}
