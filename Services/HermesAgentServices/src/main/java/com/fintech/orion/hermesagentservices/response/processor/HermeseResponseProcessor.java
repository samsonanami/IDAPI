package com.fintech.orion.hermesagentservices.response.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.orion.common.exceptions.HermeseResponseprocessorException;
import com.fintech.orion.common.service.VerificationRequestDetailService;
import com.fintech.orion.common.service.VerificationRequestDetailServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.response.api.FieldData;
import com.fintech.orion.dto.response.api.FieldDataComparision;
import com.fintech.orion.dto.response.api.FieldDataValue;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.hermesagentservices.configuration.VerificationConfiguration;
import com.fintech.orion.hermesagentservices.transmission.payload.model.Oracle.response.OcrFieldData;
import com.fintech.orion.hermesagentservices.transmission.payload.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.hermesagentservices.transmission.payload.model.Oracle.response.OcrResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public String processAndUpdateRawResponse(String rawResponse, ProcessingRequest processingRequest) throws HermeseResponseprocessorException {
        objectMapper = new ObjectMapper();
        try {

            String processedResponse = getProcessedJson(null, rawResponse);
            return processedResponse;
        } catch (IOException e) {
            throw new HermeseResponseprocessorException("Error mapping object to json ", e);
        }


    }


    private String getProcessedJson(String existingProcessedResponse, String newRawResponse) throws IOException {
        VerificationProcessDetailedResponse detailedResponse = new VerificationProcessDetailedResponse();
        if (existingProcessedResponse != null && !existingProcessedResponse.isEmpty()){
            detailedResponse = objectMapper.readValue(existingProcessedResponse, VerificationProcessDetailedResponse.class);
        }

        OcrResponse ocrResponse = objectMapper.readValue(newRawResponse, OcrResponse.class);
        detailedResponse.setStatus(ocrResponse.getStatus());
        detailedResponse.setVerificationRequestId(ocrResponse.getVerificationRequestId());
        List<FieldData> fieldDataList = new ArrayList<>();
        for (OcrFieldData fieldData : ocrResponse.getData()){
            FieldData responseFieldData = new FieldData();
            responseFieldData.setId(fieldData.getId());
            List<FieldDataValue> fieldDataValueList = new ArrayList<>();
            for (OcrFieldValue value : fieldData.getValue()){
                FieldDataValue responseFieldDataValue = new FieldDataValue();
                responseFieldDataValue.setId(value.getId());
                responseFieldDataValue.setValue(value.getValue());
                responseFieldDataValue.setConfidence(value.getConfidence());
                fieldDataValueList.add(responseFieldDataValue);
            }
            responseFieldData.setValue(fieldDataValueList);
            responseFieldData.setComparison(getFieldComparisonList(fieldDataValueList));
            fieldDataList.add(responseFieldData);
        }
        detailedResponse.setData(fieldDataList);

        return objectMapper.writeValueAsString(detailedResponse);

    }

    private List<FieldDataComparision> getFieldComparisonList(List<FieldDataValue> fieldDataValueList){
        List<FieldDataComparision> fieldDataComparisions = new ArrayList<>();
        for (FieldDataValue compare1 : fieldDataValueList){
            for (FieldDataValue compare2 : fieldDataValueList){
                if(!compare1.getId().equalsIgnoreCase(compare2.getId()) && verificationStrategy(compare1.getId()).equals("STRING")){
                    FieldDataComparision comparision = new FieldDataComparision();
                    comparision.setId(compare1.getId() + "vs" + compare2.getId());
                    comparision.setValue(compare1.getValue().equalsIgnoreCase(compare2.getValue()));
                    fieldDataComparisions.add(comparision);
                }
            }
        }
        return fieldDataComparisions;
    }

    private String verificationStrategy(String field){
        String sp = field.split("##")[1];
        VerificationConfiguration verificationConfiguration = verificationConfigurationMap.get(sp);
        return verificationConfiguration.getVerificationStrategy();
    }
}
