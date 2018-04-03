package com.fintech.orion.hermesagentservices.status;

import com.fintech.orion.dto.response.api.ImageDetail;
import com.fintech.orion.dto.response.api.VerificationProcessDetail;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.external.*;

import java.util.List;

public class DefaultResponseTransformerStatusCalculator implements VerificationProcessStatusCalculator<VerificationResponse, String>{
    private String idVerificationLiteralName;
    private String addressVerificationLiteralName;
    private String facialVerificationLiteralName;
    private String verificationStatusPass;
    private String verificationStatusFail;
    private String verificationStatusPending;
    private String livenessPassLiteral;
    private String faceMatchPassLiteral;
    private boolean faceMatchImageAvailable;

    public DefaultResponseTransformerStatusCalculator(String idVerificationLiteralName,
                                                      String addressVerificationLiteralName,
                                                      String facialVerificationLiteralName,
                                                      String passedStatusLiteral,
                                                      String verificationStatusFail,
                                                      String verificationStatusPending,
                                                      String livenessPass,
                                                      String faceMatchPass) {
        this.idVerificationLiteralName = idVerificationLiteralName;
        this.addressVerificationLiteralName = addressVerificationLiteralName;
        this.facialVerificationLiteralName = facialVerificationLiteralName;
        this.verificationStatusPass = passedStatusLiteral;
        this.verificationStatusFail = verificationStatusFail;
        this.verificationStatusPending = verificationStatusPending;
        this.livenessPassLiteral = livenessPass;
        this.faceMatchPassLiteral = faceMatchPass;
    }

    @Override
    public String calculateSingleVerificationProcessStatus(List<CustomValidation> customValidations,
                                                           List<DocumentMrzVizValidation> documentMrzVizValidations) {
        String verificationStatus = "true";

        for (CustomValidation customValidation : customValidations){
            if (!customValidation.getStatus()){
                verificationStatus = "false";
            }
        }

        for (DocumentMrzVizValidation documentMrzVizValidation : documentMrzVizValidations){
            for (MrzVizValidation mrzVizValidation : documentMrzVizValidation.getValidations()){
                if (mrzVizValidation.getStatus().equals("false") || mrzVizValidation.getStatus().equals("failed")){
                    verificationStatus = "false";
                }
            }
        }
        return verificationStatus;
    }

    @Override
    public String calculateFinalVerificationStatus(VerificationProcessDetailedResponse detailedResponse,
                                                    VerificationResponse finalVerificationResponse,
                                                   boolean isReVerification, String reVerificationStatus) {
        String finalVerificationStatus = (!isReVerification) ? verificationStatusPending : reVerificationStatus;
        boolean dataComparisonStatus = checkDataComparisonStatus(finalVerificationResponse);

        for (ImageDetail imageDetails : detailedResponse.getImageDetails()) {
            if (imageDetails.getResourceName().equalsIgnoreCase("faceMatchImage")) {
                this.faceMatchImageAvailable = true;
            }
        }

        if (this.faceMatchImageAvailable) {
            if (isVerificationIsRequested(facialVerificationLiteralName, detailedResponse.getVerificationProcessDetails()) &&
                    faceMatchPassLiteral.equalsIgnoreCase(finalVerificationResponse.getFacialVerification().getStatus())) {
                finalVerificationStatus = (!isReVerification) ? verificationStatusPass : reVerificationStatus;
            } else if (isVerificationIsRequested(facialVerificationLiteralName, detailedResponse.getVerificationProcessDetails())) {
                return (!isReVerification) ? verificationStatusPending : reVerificationStatus;
            }
        } else {
            if (isVerificationIsRequested(facialVerificationLiteralName, detailedResponse.getVerificationProcessDetails()) &&
                    faceMatchPassLiteral.equalsIgnoreCase(finalVerificationResponse.getFacialVerification().getStatus()) &&
                    livenessPassLiteral.equalsIgnoreCase(finalVerificationResponse.getLivenessTest().getStatus())) {
                finalVerificationStatus = (!isReVerification) ? verificationStatusPass : reVerificationStatus;
            } else if (isVerificationIsRequested(facialVerificationLiteralName, detailedResponse.getVerificationProcessDetails())) {
                return (!isReVerification) ? verificationStatusPending : reVerificationStatus;
            }
        }

        if (isVerificationIsRequested(idVerificationLiteralName,
                detailedResponse.getVerificationProcessDetails()) &&
                (finalVerificationResponse.getIdVerification().getStatus().equals("true") ||
                        finalVerificationResponse.getIdVerification().getStatus().equals("passed")) &&
                dataComparisonStatus){
            finalVerificationStatus =  verificationStatusPass;
        }else if (isVerificationIsRequested(idVerificationLiteralName,
                detailedResponse.getVerificationProcessDetails()) &&
                (finalVerificationResponse.getIdVerification().getStatus().equals("true") ||
                        finalVerificationResponse.getIdVerification().getStatus().equals("passed"))&&
                !dataComparisonStatus){
            return verificationStatusFail;
        }else if(isVerificationIsRequested(idVerificationLiteralName,
                detailedResponse.getVerificationProcessDetails()) &&
                (finalVerificationResponse.getIdVerification().getStatus().equals("false") ||
                        finalVerificationResponse.getIdVerification().getStatus().equals("failed"))){
            return (!isReVerification) ? verificationStatusPending : reVerificationStatus;
        }

        if (isVerificationIsRequested(addressVerificationLiteralName,
                detailedResponse.getVerificationProcessDetails()) &&
                (finalVerificationResponse.getAddressVerification().getStatus().equals("true") ||
                        finalVerificationResponse.getAddressVerification().getStatus().equals("passed")) &&
                dataComparisonStatus){
            finalVerificationStatus = verificationStatusPass;
        }else if(isVerificationIsRequested(addressVerificationLiteralName,
                detailedResponse.getVerificationProcessDetails()) &&
                (finalVerificationResponse.getAddressVerification().getStatus().equals("true") ||
                        finalVerificationResponse.getAddressVerification().getStatus().equals("passed"))&&
                !dataComparisonStatus){
            return verificationStatusFail;
        }else if(isVerificationIsRequested(addressVerificationLiteralName,
                detailedResponse.getVerificationProcessDetails()) &&
                (finalVerificationResponse.getAddressVerification().getStatus().equals("false") ||
                        finalVerificationResponse.getAddressVerification().getStatus().equals("faile"))){
            return (!isReVerification) ? verificationStatusPending : reVerificationStatus;
        }

        return finalVerificationStatus;
    }

    private boolean checkDataComparisonStatus(VerificationResponse finalVerificationResponse){
        boolean dataComparisonStatus = true;

        for (Data data : finalVerificationResponse.getData()){
            for (DataComparision dataComparision : data.getComparison()){
                if(dataComparision.getStatus().equalsIgnoreCase("false")){
                    dataComparisonStatus = false;
                    break;
                }
            }
        }
        return dataComparisonStatus;
    }

    private boolean isVerificationIsRequested(String verificationRequestType,
                                              List<VerificationProcessDetail> verificationProcessDetails){
        boolean isVerificationFound = false;        for (VerificationProcessDetail verificationProcessDetail : verificationProcessDetails){
            if (verificationProcessDetail.getVerificationProcessName().equalsIgnoreCase(verificationRequestType)){
                isVerificationFound = true;
                break;
            }
        }

        return isVerificationFound;
    }
}
