package com.fintech.orion.api.service.request;

import com.fintech.orion.api.service.exceptions.DataNotFoundException;
import com.fintech.orion.api.service.exceptions.ResourceAccessPolicyViolationException;
import com.fintech.orion.api.service.exceptions.ResourceNotFoundException;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.request.api.VerificationProcess;
import com.fintech.orion.dto.response.api.VerificationRequestSummery;
import com.fintech.orion.dto.response.external.VerificationResponse;
import org.springframework.hateoas.PagedResources;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ProcessingRequestServiceInterface {

    String saveVerificationProcessData(String clientName, List<VerificationProcess> verificationProcessList) throws DataNotFoundException;

    VerificationResponse getDetailedResponse(String clientName, String verificationRequestId) throws IOException, ResourceAccessPolicyViolationException, ResourceNotFoundException;

    PagedResources<VerificationRequestSummery> verificationRequestSummery(String clientName, Date from, Date to,
                                                                          String page, String size) throws DataNotFoundException;

	String updateVerificationRequestData(String clientName, String verificationId, VerificationResponse body)
			throws ItemNotFoundException, JsonProcessingException;
}
