package com.fintech.orion.helper;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;

import java.io.IOException;
import java.util.List;

public interface ProcessingRequestHandlerInterface {

    String saveVerificationProcessData(String accessToken, List<VerificationProcess> verificationProcessList) throws ItemNotFoundException;

    VerificationRequest getVerificationRequestData(String accessToken, String verificationRequestId) throws ItemNotFoundException;

    Object getResourceData(String accessToken, String verificationProcessId, int id) throws IOException;
}
