package com.fintech.orion.hermesagentservices.response.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.HermeseResponseprocessorException;
import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.documentverification.factory.DocumentVerification;
import com.fintech.orion.documentverification.factory.DocumentVerificationFactory;
import com.fintech.orion.documentverification.factory.DocumentVerificationType;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.ResponseProcessorResult;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.FieldData;
import com.fintech.orion.dto.response.api.ValidationData;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sasitha on 12/20/16.
 *
 */
@Service
public class HermeseResponseProcessor implements HermeseResponseProcessorInterface{

    private static final String STATUS_FAILD = "Failed";

    @Autowired
    private VerificationRequestDetailServiceInterface verificationRequestDetailService;

    @Resource
    @Qualifier("verificationConfigurationMap")
    private Map<String, VerificationConfiguration> verificationConfigurationMap;

    @Autowired
    private DocumentVerificationFactory documentVerificationFactory;


    @Override
    public ResponseProcessorResult processAndUpdateRawResponse(String rawResponse, ProcessingRequest processingRequest) throws HermeseResponseprocessorException {
        try {

            return getProcessedJson(null, rawResponse, processingRequest);
        } catch (IOException e) {
            throw new HermeseResponseprocessorException("Error mapping object to json ", e);
        }


    }


    private ResponseProcessorResult getProcessedJson(String existingProcessedResponse, String newRawResponse, ProcessingRequest processingRequest) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        VerificationProcessDetailedResponse detailedResponse = new VerificationProcessDetailedResponse();
        if (existingProcessedResponse != null && !existingProcessedResponse.isEmpty()){
            detailedResponse = objectMapper.readValue(existingProcessedResponse, VerificationProcessDetailedResponse.class);
        }

        OcrResponse ocrResponse = objectMapper.readValue(newRawResponse, OcrResponse.class);
        detailedResponse.setStatus(ocrResponse.getStatus());
        detailedResponse.setVerificationRequestId(processingRequest.getProcessingRequestIdentificationCode());
        ocrResponse.setVerificationRequestId(processingRequest.getProcessingRequestIdentificationCode());




        updateDataComparison(detailedResponse, ocrResponse);
        if (isProcessTypeFoundInProcessingRequest(processingRequest.getProcessingRequestIdentificationCode(),
                "idVerification")){
            updateIdDocumentFullValidation(detailedResponse, ocrResponse);
        }
        if (isProcessTypeFoundInProcessingRequest(processingRequest.getProcessingRequestIdentificationCode(),
                "addressVerification")){
            updateAddressDocumentFullValidation(detailedResponse, ocrResponse);
        }
        setFinalProcessingStatus(detailedResponse);
        ResponseProcessorResult result = new ResponseProcessorResult();
        result.setFinalProcessingStatus(true);
        result.setProcessedString(objectMapper.writeValueAsString(detailedResponse));
        if (STATUS_FAILD.equalsIgnoreCase(detailedResponse.getStatus())){
            result.setFinalProcessingStatus(false);
        }

        return result;

    }

    private void setFinalProcessingStatus(VerificationProcessDetailedResponse detailedResponse){
        for (ValidationData validation : detailedResponse.getIdDocFullValidations()){
            if("critical_error_set".equalsIgnoreCase(validation.getId()) && !validation.getRemarks().isEmpty()){
                detailedResponse.setStatus(STATUS_FAILD);
            }
        }
        for (ValidationData validation : detailedResponse.getAddressDocFullValidations()){
            if("critical_error_set".equalsIgnoreCase(validation.getId())&& !validation.getRemarks().isEmpty()){
                detailedResponse.setStatus(STATUS_FAILD);
            }
        }
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

    private List<ValidationData> getValidationDataList(List<Object> objectList){
        return  objectList.stream()
                .map(element->(ValidationData) element)
                .collect(Collectors.toList());
    }



    private boolean isProcessTypeFoundInProcessingRequest(String processingRequestId, String processType){
        return verificationRequestDetailService.isVerificationProcessFoundInProcessingRequest(processingRequestId,
                processType);
    }

}
