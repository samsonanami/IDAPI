package com.fintech.orion.hermesagentservices.processor.response.chain.oracle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.Processor;
import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.documentverification.factory.DocumentVerification;
import com.fintech.orion.documentverification.factory.DocumentVerificationFactory;
import com.fintech.orion.documentverification.factory.DocumentVerificationType;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.DataValidation;
import com.fintech.orion.dto.response.api.FieldData;
import com.fintech.orion.dto.response.api.ValidationData;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.processor.VerificationResult;
import com.fintech.orion.hermesagentservices.processor.response.chain.RequestProcessorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sasitha on 2/17/17.
 */
public class OracleResponseProcessor extends RequestProcessorChain {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleResponseProcessor.class);
    @Autowired
    private VerificationRequestDetailServiceInterface verificationRequestDetailService;

    @Resource
    @Qualifier("verificationConfigurationMap")
    private Map<String, VerificationConfiguration> verificationConfigurationMap;

    @Autowired
    private DocumentVerificationFactory documentVerificationFactory;

    @Override
    protected void execute(VerificationProcessDetailedResponse response,
                           List<VerificationResult> verificationResults, String processingRequestId) {
        String rawString = "";
        for (VerificationResult verificationResult : verificationResults){
            if (verificationResult.getProcessor().equals(Processor.ORACLE)){
                rawString = verificationResult.getResultString();
            }
        }

        if (rawString != null && !"null".equalsIgnoreCase(rawString) && !rawString.isEmpty()) {
            try {
                processRawString(response, rawString, processingRequestId);
            } catch (IOException e) {
                LOGGER.error("Error processing raw response {} for processing request id {} ",
                        rawString, processingRequestId, e);
            }
        }
    }

    private void processRawString(VerificationProcessDetailedResponse response, String rawString,
                                  String processingRequestId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        VerificationProcessDetailedResponse detailedResponse = response;
        OcrResponse ocrResponse = objectMapper.readValue(rawString, OcrResponse.class);
        detailedResponse.setStatus(ocrResponse.getStatus());
        detailedResponse.setVerificationRequestId(processingRequestId);
        ocrResponse.setVerificationRequestId(processingRequestId);

        updateDataComparison(detailedResponse, ocrResponse);
        updateDataValidations(detailedResponse, ocrResponse);
        if (isProcessTypeFoundInProcessingRequest(processingRequestId,
                "idVerification")){
            updateIdDocumentFullValidation(detailedResponse, ocrResponse);
        }
        if (isProcessTypeFoundInProcessingRequest(processingRequestId,
                "addressVerification")){
            updateAddressDocumentFullValidation(detailedResponse, ocrResponse);
        }
        ResponseProcessorResult result = new ResponseProcessorResult();
        result.setFinalProcessingStatus(true);
        result.setProcessedString(objectMapper.writeValueAsString(detailedResponse));
    }

    private void updateDataComparison(VerificationProcessDetailedResponse response, OcrResponse ocrResponse){
        List<Object> resultList = new ArrayList<>();
        DocumentVerification dataComparison = documentVerificationFactory.getDocumentVerification(DocumentVerificationType.DATA_COMPARISON);
        resultList = dataComparison.verifyExtractedDocumentResult(ocrResponse, verificationConfigurationMap);
        List<FieldData> fieldDataList = resultList.stream()
                .map(element->(FieldData) element)
                .collect(Collectors.toList());
        response.setData(fieldDataList);
    }

    private void updateIdDocumentFullValidation(VerificationProcessDetailedResponse response, OcrResponse ocrResponse){
        List<Object> resultList = new ArrayList<>();
        DocumentVerification idDocFullComparision =
                documentVerificationFactory.getDocumentVerification(DocumentVerificationType.ID_DOC_FULL_VERIFICATIONS);
        resultList = idDocFullComparision.verifyExtractedDocumentResult(ocrResponse, verificationConfigurationMap);
        List<ValidationData> validationDataList= resultList.stream()
                .map(element->(ValidationData) element)
                .collect(Collectors.toList());
        response.setIdDocFullValidations(validationDataList);

    }

    private void updateAddressDocumentFullValidation(VerificationProcessDetailedResponse response, OcrResponse ocrResponse){
        List<Object> resultList = new ArrayList<>();
        DocumentVerification addressDocFullComparision =
                documentVerificationFactory.getDocumentVerification(DocumentVerificationType.ADDRESS_DOC_FULL_VERIFICATIONS);
        resultList = addressDocFullComparision.verifyExtractedDocumentResult(ocrResponse, verificationConfigurationMap);
        List<ValidationData> validationDataList = getValidationDataList(resultList);
        response.setAddressDocFullValidations(validationDataList);
    }

    private void updateDataValidations(VerificationProcessDetailedResponse response, OcrResponse ocrResponse){
        List<Object> resultList = new ArrayList<>();
        DocumentVerification dataValidations =
                documentVerificationFactory.getDocumentVerification(DocumentVerificationType.DATA_VALIDATIONS);
        resultList = dataValidations.verifyExtractedDocumentResult(ocrResponse, verificationConfigurationMap);
        List<DataValidation> dataValidationList = getDataValidationValueList(resultList);
        response.setDataValidation(dataValidationList);
    }

    private List<ValidationData> getValidationDataList(List<Object> objectList){
        return  objectList.stream()
                .map(element->(ValidationData) element)
                .collect(Collectors.toList());
    }

    private List<DataValidation> getDataValidationValueList(List<Object> objectList){
        return objectList.stream()
                .map(element->(DataValidation) element)
                .collect(Collectors.toList());
    }



    private boolean isProcessTypeFoundInProcessingRequest(String processingRequestId, String processType){
        return verificationRequestDetailService.isVerificationProcessFoundInProcessingRequest(processingRequestId,
                processType);
    }
}
