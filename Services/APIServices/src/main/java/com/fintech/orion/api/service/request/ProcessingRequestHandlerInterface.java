package com.fintech.orion.api.service.request;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;
import org.json.simple.parser.ParseException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface ProcessingRequestHandlerInterface {

    String saveVerificationProcessData(String clientName, List<VerificationProcess> verificationProcessList) throws ItemNotFoundException;

    VerificationRequest getVerificationRequestData(String accessToken, String verificationRequestId) throws ItemNotFoundException;

    BufferedImage getResourceData(String accessToken, String verificationProcessId, String id) throws IOException, ParseException, ItemNotFoundException;
}
