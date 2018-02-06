package com.fintech.orion.hermesagentservices.transformer;

import com.fintech.orion.dto.hermese.model.compressionlabs.response.FacialReVerificationResponse;
import com.fintech.orion.dto.response.external.VerificationResponse;

public interface ReVerificationFacialDataTransformer {
    FacialReVerificationResponse transform(VerificationResponse verificationResponse);
}
