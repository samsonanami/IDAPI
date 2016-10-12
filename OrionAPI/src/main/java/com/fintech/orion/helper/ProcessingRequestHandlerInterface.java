package com.fintech.orion.helper;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;

import java.io.IOException;
import java.util.List;

public interface ProcessingRequestHandlerInterface {

    Client isValidClient(String accessToken) throws ItemNotFoundException;

    String saveData(String accessToken, List<VerificationProcess> verificationProcessList) throws ItemNotFoundException;

    VerificationRequest getData(String accessToken, String verificationRequestId) throws ItemNotFoundException;

    Object getImageData(String accessToken, String verificationProcessId, int id) throws IOException;
}
