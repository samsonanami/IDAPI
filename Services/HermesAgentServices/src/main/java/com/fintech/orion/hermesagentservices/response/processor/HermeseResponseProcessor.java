package com.fintech.orion.hermesagentservices.response.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.HermeseResponseprocessorException;
import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.documentverification.factory.DocumentVerification;
import com.fintech.orion.documentverification.factory.DocumentVerificationFactory;
import com.fintech.orion.documentverification.factory.DocumentVerificationType;
import com.fintech.orion.dto.response.api.FieldData;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Autowired
    private VerificationRequestDetailServiceInterface verificationRequestDetailService;

    @Resource
    @Qualifier("verificationConfigurationMap")
    private Map<String, VerificationConfiguration> verificationConfigurationMap;

    private DocumentVerificationFactory documentVerificationFactory;


    @Override
    @Transactional
    public String processAndUpdateRawResponse(String rawResponse, ProcessingRequest processingRequest) throws HermeseResponseprocessorException {
        documentVerificationFactory = new DocumentVerificationFactory();
        try {

            String processedResponse = getProcessedJson(null, rawResponse);
            return processedResponse;
        } catch (IOException e) {
            throw new HermeseResponseprocessorException("Error mapping object to json ", e);
        }


    }


    private String getProcessedJson(String existingProcessedResponse, String newRawResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        VerificationProcessDetailedResponse detailedResponse = new VerificationProcessDetailedResponse();
        if (existingProcessedResponse != null && !existingProcessedResponse.isEmpty()){
            detailedResponse = objectMapper.readValue(existingProcessedResponse, VerificationProcessDetailedResponse.class);
        }

        OcrResponse ocrResponse = objectMapper.readValue(newRawResponse, OcrResponse.class);
        detailedResponse.setStatus(ocrResponse.getStatus());
        detailedResponse.setVerificationRequestId(ocrResponse.getVerificationRequestId());



        List<Object> resultList = new ArrayList<>();

        DocumentVerification dataComparison = documentVerificationFactory.getDocumentVerification(DocumentVerificationType.DATA_COMPARISON);
        resultList = dataComparison.verifyExtractedDocumentResult(ocrResponse, verificationConfigurationMap);
        List<FieldData> fieldDataList = resultList.stream()
                .map(element->(FieldData) element)
                .collect(Collectors.toList());
        detailedResponse.setData(fieldDataList);

        return objectMapper.writeValueAsString(detailedResponse);

    }


}
