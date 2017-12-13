package com.fintech.orion.hermesagentservices.transformer.custom;

import com.fintech.orion.dto.hermese.model.compressionlabs.response.FacialVerificationResponse;
import com.fintech.orion.dto.response.external.VerificationResponse;
import com.fintech.orion.hermesagentservices.transformer.ReverificationFacialDataTransformer;

public class ReverificationLinessTestDataTransformer implements ReverificationFacialDataTransformer {

    @Override
    public FacialVerificationResponse transform(VerificationResponse verificationResponse) {
        FacialVerificationResponse facialVerificationResponse = new FacialVerificationResponse();
        facialVerificationResponse.setConfidence(
                getConfidence(verificationResponse.getFacialVerification().getStatus(),
                        verificationResponse.getLivenessTest().getStatus()));


        return facialVerificationResponse;

    }

    private double getConfidence(String faceMatchStatus, String livenessStatus){
        double confidence = 0.0;
        if(livenessStatus.equalsIgnoreCase("ManuallyVerifiedFailed")){
            confidence = 0.02;
        }else if (faceMatchStatus.equalsIgnoreCase("manuallyVerified")){
            confidence = 1.0;
        }else if(faceMatchStatus.equalsIgnoreCase("ManuallyVerifiedFailed")){
            confidence = 0.1;
        }else if(faceMatchStatus.equalsIgnoreCase("manuallyVerifiedCouldNotVerify")){
            confidence = 0.4;
        }
        return confidence;
    }
}
