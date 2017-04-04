package com.fintech.orion.hermesagentservices.transformer.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.transformer.RequestTransformerException;
import com.fintech.orion.dto.response.api.*;
import com.fintech.orion.dto.response.external.*;
import com.fintech.orion.hermesagentservices.transformer.ResponseTransformer;
import com.fintech.orion.hermesagentservices.transformer.mapper.CustomValidationMapper;
import com.fintech.orion.hermesagentservices.transformer.mapper.DataMapper;
import com.fintech.orion.hermesagentservices.transformer.mapper.ImageMapper;
import com.fintech.orion.hermesagentservices.transformer.mapper.VerificationMapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * {@code GenericResponseTransformer} will transform
 * {@link com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse VerificationProcessDetailedResponse}
 * in to a {@link com.fintech.orion.dto.response.external.VerificationResponse VerificationResponse } object.
 * </p>
 * <p>
 * {@link com.fintech.orion.dto.response.external.VerificationResponse VerificationResponse } is the object returned
 * from the Orion API
 * <p>
 * {@link com.fintech.orion.dto.response.external.VerificationResponse VerificationResponse } is the
 */
public class GenericResponseTransformer implements ResponseTransformer {

    @Override
    public String transform(VerificationProcessDetailedResponse detailedResponse) throws RequestTransformerException {
        ObjectMapper objectMapper = new ObjectMapper();

        VerificationResponse verificationResponse = new VerificationResponse();

        verificationResponse.setVerificationRequestId(detailedResponse.getVerificationRequestId());
        verificationResponse.setImageDetails(getImageDetails(detailedResponse.getImageDetails()));
        verificationResponse.setVerificationDetails(getVerifications(detailedResponse.getVerificationProcessDetails()));

        verificationResponse.setData(transformToDataObjects(detailedResponse.getData()));
        verificationResponse.setFacialVerification(getFacialVerification(detailedResponse.getFacialMatch()));
        verificationResponse.setLivenessTest(getLivnessTest(detailedResponse.getLivenessTest()));
        verificationResponse.setIdVerification(getIdVerificationDetails(detailedResponse));
        verificationResponse.setAddressVerification(getAddressVerificationDetails(detailedResponse));

        calculateFinalVerificationStatus(verificationResponse, detailedResponse);


        try {
            return objectMapper.writeValueAsString(verificationResponse);
        } catch (JsonProcessingException e) {
            throw new RequestTransformerException(e);
        }
    }

    private List<Data> transformToDataObjects(List<FieldData> input) {
        DataMapper dataMapper = Mappers.getMapper(DataMapper.class);
        return input.stream()
                .map(element -> dataMapper.fieldDataToData(element))
                .collect(Collectors.toList());
    }

    private List<Image> getImageDetails(List<ImageDetail> imageDetails) {
        ImageMapper imageMapper = Mappers.getMapper(ImageMapper.class);
        return imageDetails.stream()
                .map(element -> imageMapper.imageDetailsToImage(element))
                .collect(Collectors.toList());
    }

    private List<Verification> getVerifications(List<VerificationProcessDetail> verificationProcessDetails) {
        VerificationMapper verificationMapper = Mappers.getMapper(VerificationMapper.class);
        return verificationProcessDetails.stream()
                .map(element -> verificationMapper.verificationDetailsToVerification(element))
                .collect(Collectors.toList());
    }

    private List<CustomValidation> getCustomValidations(List<ValidationData> validationDataList) {
        CustomValidationMapper customValidationMapper = Mappers.getMapper(CustomValidationMapper.class);
        return validationDataList.stream()
                .map(element -> customValidationMapper.validationDataToCustomValidation(element))
                .collect(Collectors.toList());

    }

    private FacialVerification getFacialVerification(String status) {
        FacialVerification facialVerification = new FacialVerification();
        facialVerification.setStatus(status);

        return facialVerification;
    }

    private LivenessTest getLivnessTest(String status) {
        LivenessTest livenessTest = new LivenessTest();
        livenessTest.setStatus(status);
        return livenessTest;
    }

    private IdVerification getIdVerificationDetails(VerificationProcessDetailedResponse detailedResponse) {
        IdVerification idVerification = new IdVerification();
        if(isVerificationIsRequested("idVerification", detailedResponse.getVerificationProcessDetails())){
            idVerification.setDataValidations(getDocumentMrzVizValidations(detailedResponse,
                    "idVerification"));
            idVerification.setCustomValidations(getCustomValidations(detailedResponse.getIdDocFullValidations()));
            idVerification.setStatus(calculateIntermediateStatus(idVerification.getCustomValidations(), idVerification.getDataValidations()));
        }

        return idVerification;

    }

    private AddressVerification getAddressVerificationDetails(VerificationProcessDetailedResponse detailedResponse) {
        AddressVerification addressVerification = new AddressVerification();
        if(isVerificationIsRequested("addressVerification", detailedResponse.getVerificationProcessDetails())){
            addressVerification.setDataValidations(getDocumentMrzVizValidations(detailedResponse,
                    "addressVerification"));
            addressVerification.setCustomValidations(getCustomValidations(detailedResponse.getAddressDocFullValidations()));
            addressVerification.setStatus(calculateIntermediateStatus(addressVerification.getCustomValidations(), addressVerification.getDataValidations()));
        }
        return addressVerification;
    }

    private List<DocumentMrzVizValidation> getDocumentMrzVizValidations(
            VerificationProcessDetailedResponse detailedResponse,
            String verificationProcessType) {
        List<DocumentMrzVizValidation> documentMrzVizValidations = new ArrayList<>();
        VerificationProcessDetail verificationProcessDetail =
                verificationProcessDetailByProcessType(verificationProcessType, detailedResponse);
        for (ImageDetail imageDetail : verificationProcessDetail.getImageDetails()) {
            DocumentMrzVizValidation documentDataValidation = new DocumentMrzVizValidation();
            documentDataValidation.setDocument(imageDetail.getResourceName());
            documentDataValidation.setValidations(getMrzVizValidationForDocument(imageDetail.getResourceName(), detailedResponse));

            documentMrzVizValidations.add(documentDataValidation);
        }
        return documentMrzVizValidations;
    }

    private List<MrzVizValidation> getMrzVizValidationForDocument(String resourceName, VerificationProcessDetailedResponse detailedResponse) {
        List<MrzVizValidation> documentMrzVizValidation = new ArrayList<>();
        for (DataValidation dataValidation : detailedResponse.getDataValidation()) {
            for (DataValidationValue dataValidationValue : dataValidation.getValue()) {
                if (dataValidationValue.getDocumentName().equalsIgnoreCase(resourceName)) {
                    MrzVizValidation mrzVizValidation = new MrzVizValidation();
                    mrzVizValidation.setId(dataValidation.getId());
                    mrzVizValidation.setStatus(dataValidationValue.getStatus());
                    mrzVizValidation.setValues(getMriVizValidationVales(dataValidationValue));
                    mrzVizValidation.setACriticalValidation(dataValidationValue.isCriticalValidation());
                    documentMrzVizValidation.add(mrzVizValidation);
                }
            }
        }
        return documentMrzVizValidation;
    }

    private List<DocumentMrzVizValidationValue> getMriVizValidationVales(DataValidationValue dataValidationValue) {
        List<DocumentMrzVizValidationValue> values = new ArrayList<>();
        DocumentMrzVizValidationValue mrzValue = new DocumentMrzVizValidationValue();
        mrzValue.setId("mrz_value");
        mrzValue.setValue(dataValidationValue.getMrzValue());
        DocumentMrzVizValidationValue vizValue = new DocumentMrzVizValidationValue();
        vizValue.setId("viz_value");
        vizValue.setValue(dataValidationValue.getVizValue());

        values.add(vizValue);
        values.add(mrzValue);

        return values;
    }

    private VerificationProcessDetail verificationProcessDetailByProcessType(String verificationProcessType,
                                                                             VerificationProcessDetailedResponse detailedResponse) {
        VerificationProcessDetail processDetail = new VerificationProcessDetail();
        for (VerificationProcessDetail verificationProcessDetail : detailedResponse.getVerificationProcessDetails()) {
            if (verificationProcessDetail.getVerificationProcessName().equalsIgnoreCase(verificationProcessType)) {
                processDetail = verificationProcessDetail;
            }
        }

        return processDetail;
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

    private boolean calculateIntermediateStatus(List<CustomValidation> customValidations,
                                                List<DocumentMrzVizValidation> documentMrzVizValidations ){
        boolean verificationStatus = true;

        for (CustomValidation customValidation : customValidations){
            if (!customValidation.getStatus()){
                verificationStatus = false;
            }
        }

        for (DocumentMrzVizValidation documentMrzVizValidation : documentMrzVizValidations){
            for (MrzVizValidation mrzVizValidation : documentMrzVizValidation.getValidations()){
                if (mrzVizValidation.getStatus()){
                    verificationStatus = false;
                }
            }
        }
        return verificationStatus;
    }

    private void calculateFinalVerificationStatus(VerificationResponse verificationResponse,
                                             VerificationProcessDetailedResponse detailedResponse){
        boolean finalVerificationStatus = true;
        if (isVerificationIsRequested("facialVerification",
                detailedResponse.getVerificationProcessDetails()) &&
                !verificationResponse.getFacialVerification().getStatus().equalsIgnoreCase("passed")){
            finalVerificationStatus = false;
        }

        if (isVerificationIsRequested("facialVerification",
                detailedResponse.getVerificationProcessDetails()) &&
                !verificationResponse.getFacialVerification().getStatus().equalsIgnoreCase("passed")){
            finalVerificationStatus = false;
        }

        if (isVerificationIsRequested("idVerification",
                detailedResponse.getVerificationProcessDetails()) &&
                !verificationResponse.getIdVerification().getStatus()){
            finalVerificationStatus = false;
        }

        if (isVerificationIsRequested("addressVerification",
                detailedResponse.getVerificationProcessDetails()) &&
                !verificationResponse.getAddressVerification().getStatus()){
            finalVerificationStatus = false;
        }
        if (finalVerificationStatus){
            verificationResponse.setStatus("passed");
        }else {
            verificationResponse.setStatus("failed");
        }

    }

}
