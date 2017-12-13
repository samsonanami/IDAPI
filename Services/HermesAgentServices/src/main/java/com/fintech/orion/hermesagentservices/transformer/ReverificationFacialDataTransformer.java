package com.fintech.orion.hermesagentservices.transformer;

import com.fintech.orion.dto.hermese.model.compressionlabs.response.FacialVerificationResponse;
import com.fintech.orion.dto.response.external.VerificationResponse;

public interface ReverificationFacialDataTransformer  {
    FacialVerificationResponse transform(VerificationResponse verificationResponse);
}
