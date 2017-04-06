package com.fintech.orion.hermesagentservices.transformer.custom;

import com.fintech.orion.common.exceptions.transformer.RequestTransformerException;
import com.fintech.orion.dto.response.api.*;
import com.fintech.orion.dto.response.external.*;
import com.fintech.orion.hermesagentservices.status.DefaultResponseTransformerStatusCalculator;
import com.fintech.orion.hermesagentservices.transformer.ResponseTransformer;
import com.fintech.orion.hermesagentservices.transformer.mapper.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
 */
public class GenericResponseTransformer implements ResponseTransformer<VerificationResponse> {
    private DefaultResponseTransformerStatusCalculator statusCalculator;

    private String idVerificationName;
    private String addressVerificationName;
    private String facialVerificationName;
    private String criticalErrorSetOcrExtractionFieldName;
    private String processingFailureOcrExtractionFieldName;
    private String verificationStatusPass;
    private String verificationStatusFail;

    public void setIdVerificationName(String idVerificationName) {
        this.idVerificationName = idVerificationName;
    }

    public void setAddressVerificationName(String addressVerificationName) {
        this.addressVerificationName = addressVerificationName;
    }

    public void setCriticalErrorSetOcrExtractionFieldName(String criticalErrorSetOcrExtractionFieldName) {
        this.criticalErrorSetOcrExtractionFieldName = criticalErrorSetOcrExtractionFieldName;
    }

    public void setProcessingFailureOcrExtractionFieldName(String processingFailureOcrExtractionFieldName) {
        this.processingFailureOcrExtractionFieldName = processingFailureOcrExtractionFieldName;
    }

    public void setVerificationStatusPass(String verificationStatusPass) {
        this.verificationStatusPass = verificationStatusPass;
    }

    public void setVerificationStatusFail(String verificationStatusFail) {
        this.verificationStatusFail = verificationStatusFail;
    }

    public void setFacialVerificationName(String facialVerificationName) {
        this.facialVerificationName = facialVerificationName;
    }

    @Override
    public VerificationResponse transform(VerificationProcessDetailedResponse detailedResponse) throws RequestTransformerException {
        statusCalculator = new DefaultResponseTransformerStatusCalculator(idVerificationName, addressVerificationName, facialVerificationName, verificationStatusPass);
        VerificationResponse verificationResponse = new VerificationResponse();

        verificationResponse.setVerificationRequestId(detailedResponse.getVerificationRequestId());
        verificationResponse.setImageDetails(getImageDetails(detailedResponse.getImageDetails()));
        verificationResponse.setVerificationDetails(getVerifications(detailedResponse.getVerificationProcessDetails()));

        verificationResponse.setData(transformToDataObjects(detailedResponse.getData()));
        verificationResponse.setFacialVerification(getFacialVerification(detailedResponse.getFacialMatch()));
        verificationResponse.setLivenessTest(getLivnessTest(detailedResponse.getLivenessTest()));
        verificationResponse.setProcessingFailures(getProcessingFailures(detailedResponse.getData()));

        if (isVerificationIsRequested(idVerificationName, detailedResponse.getVerificationProcessDetails())) {
            verificationResponse.setIdVerification(getIdVerificationDetails(detailedResponse));
        }
        if (isVerificationIsRequested(addressVerificationName, detailedResponse.getVerificationProcessDetails())) {
            verificationResponse.setAddressVerification(getAddressVerificationDetails(detailedResponse));
        }
        calculateFinalVerificationStatus(verificationResponse, detailedResponse);


        return verificationResponse;
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

    private List<Data> transformToDataObjects(List<FieldData> input) {
        DataMapper dataMapper = Mappers.getMapper(DataMapper.class);
        return input.stream()
                .map(element -> dataMapper.fieldDataToData(element))
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


    private List<ProcessingFailure> getProcessingFailures(List<FieldData> fieldDataList) {
        ProcessingFailureMapper processingFailureMapper = Mappers.getMapper(ProcessingFailureMapper.class);
        List<ProcessingFailure> processingFailureList = new ArrayList<>();
        Optional<FieldData> filteredFieldData =
                fieldDataList.stream()
                        .filter(fieldData -> fieldData.getId().equalsIgnoreCase(processingFailureOcrExtractionFieldName))
                        .findFirst();
        if (filteredFieldData.isPresent()) {
            FieldData processingFailureFieldData = filteredFieldData.get();
            processingFailureList.addAll(processingFailureFieldData.getValue().stream()
                    .map(fieldDataValue -> processingFailureMapper.fieldDataToProcessingFailure(fieldDataValue))
                    .collect(Collectors.toList()));
        }

        return processingFailureList;

    }


    private IdVerification getIdVerificationDetails(VerificationProcessDetailedResponse detailedResponse) {
        IdVerification idVerification = new IdVerification();
        idVerification.setDataValidations(getDocumentMrzVizValidations(detailedResponse,
                idVerificationName));
        idVerification.setCustomValidations(getCustomValidations(detailedResponse.getIdDocFullValidations()));
        idVerification.setStatus(calculateIntermediateStatus(idVerification.getCustomValidations(), idVerification.getDataValidations()));


        return idVerification;

    }

    private AddressVerification getAddressVerificationDetails(VerificationProcessDetailedResponse detailedResponse) {
        AddressVerification addressVerification = new AddressVerification();
        addressVerification.setDataValidations(getDocumentMrzVizValidations(detailedResponse, addressVerificationName));
        addressVerification.setCustomValidations(getCustomValidations(detailedResponse.getAddressDocFullValidations()));
        addressVerification.setStatus(calculateIntermediateStatus(addressVerification.getCustomValidations(), addressVerification.getDataValidations()));

        return addressVerification;
    }


    private List<DocumentMrzVizValidation> getDocumentMrzVizValidations(
            VerificationProcessDetailedResponse detailedResponse,
            String verificationProcessType) {
        List<DocumentMrzVizValidation> documentMrzVizValidations = new ArrayList<>();
        VerificationProcessDetail verificationProcessDetail =
                verificationProcessDetailByProcessType(verificationProcessType, detailedResponse);
        for (ImageDetail imageDetail : verificationProcessDetail.getImageDetails()) {
            List<MrzVizValidation> validations =
                    getMrzVizValidationForDocument(imageDetail.getResourceName(), detailedResponse);
            if (!validations.isEmpty()) {
                DocumentMrzVizValidation documentDataValidation = new DocumentMrzVizValidation();
                documentDataValidation.setDocument(imageDetail.getResourceName());
                documentDataValidation.setValidations(validations);

                documentMrzVizValidations.add(documentDataValidation);
            }
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

    private List<CustomValidation> getCustomValidations(List<ValidationData> validationDataList) {
        CustomValidationMapper customValidationMapper = Mappers.getMapper(CustomValidationMapper.class);
        return validationDataList.stream()
                .filter(element -> !element.getId().equalsIgnoreCase(criticalErrorSetOcrExtractionFieldName))
                .map(element -> customValidationMapper.validationDataToCustomValidation(element))
                .collect(Collectors.toList());

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
                                              List<VerificationProcessDetail> verificationProcessDetails) {
        boolean isVerificationFound = false;
        for (VerificationProcessDetail verificationProcessDetail : verificationProcessDetails) {
            if (verificationProcessDetail.getVerificationProcessName().equalsIgnoreCase(verificationRequestType)) {
                isVerificationFound = true;
                break;
            }
        }

        return isVerificationFound;
    }

    private boolean calculateIntermediateStatus(List<CustomValidation> customValidations,
                                                List<DocumentMrzVizValidation> documentMrzVizValidations) {
        return statusCalculator.calculateSingleVerificationProcessStatus(customValidations, documentMrzVizValidations);
    }

    private void calculateFinalVerificationStatus(VerificationResponse verificationResponse,
                                                  VerificationProcessDetailedResponse detailedResponse) {
        boolean finalVerificationStatus = statusCalculator
                .calculateFinalVerificationStatus(detailedResponse, verificationResponse);
        if (finalVerificationStatus) {
            verificationResponse.setStatus(verificationStatusPass);
        } else {
            verificationResponse.setStatus(verificationStatusFail);
        }
    }

}
