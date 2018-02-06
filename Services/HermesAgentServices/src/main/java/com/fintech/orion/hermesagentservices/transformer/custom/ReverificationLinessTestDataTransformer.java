package com.fintech.orion.hermesagentservices.transformer.custom;

import com.fintech.orion.dto.hermese.model.compressionlabs.response.FacialReVerificationResponse;
import com.fintech.orion.dto.hermese.model.compressionlabs.response.FacialVerificationResponse;
import com.fintech.orion.dto.response.external.VerificationResponse;
import com.fintech.orion.hermesagentservices.transformer.ReVerificationFacialDataTransformer;

public class ReverificationLinessTestDataTransformer implements ReVerificationFacialDataTransformer {

    @Override
    public FacialReVerificationResponse transform(VerificationResponse verificationResponse) {
        FacialReVerificationResponse reVerificationResponse  = new FacialReVerificationResponse();
        reVerificationResponse.setFaceMatchStatus(verificationResponse.getFacialVerification().getStatus());
        reVerificationResponse.setLivenessStatus(verificationResponse.getLivenessTest().getStatus());

        return reVerificationResponse;

    }

    private double getConfidence(String faceMatchStatus, String livenessStatus){
        double confidence = 0.0;
        if(livenessStatus.equalsIgnoreCase("ManuallyVerifiedFailed")){
            confidence = 0.02;
        }else if (faceMatchStatus.equalsIgnoreCase("ManuallyVerifiedPassed")){
            confidence = 1.0;
        }else if(faceMatchStatus.equalsIgnoreCase("ManuallyVerifiedFailed")){
            confidence = 0.1;
        }else if(faceMatchStatus.equalsIgnoreCase("manuallyVerifiedCouldNotVerify")){
            confidence = 0.4;
        }
        return confidence;
    }
}
