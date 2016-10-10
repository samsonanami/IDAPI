package com.fintech.orion.helper;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;

import java.util.List;

public interface ProcessingRequestHandlerInterface {

    String saveData(String accessToken, List<VerificationProcess> verificationProcessList) throws ItemNotFoundException;

    VerificationRequest getData(String verificationRequestId) throws ItemNotFoundException;
}
