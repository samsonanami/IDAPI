package com.fintech.orion.hermesagentservices.status;

import com.fintech.orion.dto.response.api.VerificationProcessDetail;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.external.CustomValidation;
import com.fintech.orion.dto.response.external.DocumentMrzVizValidation;
import com.fintech.orion.dto.response.external.MrzVizValidation;
import com.fintech.orion.dto.response.external.VerificationResponse;

import java.util.List;

public class DefaultResponseTransformerStatusCalculator implements VerificationProcessStatusCalculator<VerificationResponse, Boolean>{

    @Override
    public Boolean calculateSingleVerificationProcessStatus(List<CustomValidation> customValidations, List<DocumentMrzVizValidation> documentMrzVizValidations) {
        boolean verificationStatus = true;

        for (CustomValidation customValidation : customValidations){
            if (!customValidation.getStatus()){
                verificationStatus = false;
            }
        }

        for (DocumentMrzVizValidation documentMrzVizValidation : documentMrzVizValidations){
            for (MrzVizValidation mrzVizValidation : documentMrzVizValidation.getValidations()){
                if (!mrzVizValidation.getStatus()){
                    verificationStatus = false;
                }
            }
        }
        return verificationStatus;
    }

    @Override
    public Boolean calculateFinalVerificationStatus(VerificationProcessDetailedResponse detailedResponse,
                                                    VerificationResponse finalVerificationResponse) {
        boolean finalVerificationStatus = true;
        if (isVerificationIsRequested("facialVerification",
                detailedResponse.getVerificationProcessDetails()) &&
                !finalVerificationResponse.getFacialVerification().getStatus().equalsIgnoreCase("passed")){
            finalVerificationStatus = false;
        }

        if (isVerificationIsRequested("facialVerification",
                detailedResponse.getVerificationProcessDetails()) &&
                !finalVerificationResponse.getFacialVerification().getStatus().equalsIgnoreCase("passed")){
            finalVerificationStatus = false;
        }

        if (isVerificationIsRequested("idVerification",
                detailedResponse.getVerificationProcessDetails()) &&
                !finalVerificationResponse.getIdVerification().getStatus()){
            finalVerificationStatus = false;
        }

        if (isVerificationIsRequested("addressVerification",
                detailedResponse.getVerificationProcessDetails()) &&
                !finalVerificationResponse.getAddressVerification().getStatus()){
            finalVerificationStatus = false;
        }
        return finalVerificationStatus;
    }

    private boolean isVerificationIsRequested(String verificationRequestType,
                                              List<VerificationProcessDetail> verificationProcessDetails){
        boolean isVerificationFound = false;
        for (VerificationProcessDetail verificationProcessDetail : verificationProcessDetails){
            if (verificationProcessDetail.getVerificationProcessName().equalsIgnoreCase(verificationRequestType)){
                isVerificationFound = true;
                break;
            }
        }

        return isVerificationFound;
    }
}
