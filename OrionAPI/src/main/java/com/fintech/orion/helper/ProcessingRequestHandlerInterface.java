package com.fintech.orion.helper;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ProcessingRequestHandlerInterface {

    String saveData(String accessToken, List<VerificationProcess> verificationProcessList) throws ItemNotFoundException;

    VerificationRequest getData(String accessToken, String verificationRequestId) throws ItemNotFoundException;

    BufferedImage getImageData(String accessToken, String verificationProcessId, int id);
}
