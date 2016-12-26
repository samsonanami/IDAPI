package com.fintech.orion.documentverification.factory;

import com.fintech.orion.documentverification.strategy.*;
import com.fintech.orion.dto.configuration.DataValidationStrategyType;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.FieldData;
import com.fintech.orion.dto.response.api.FieldDataComparision;
import com.fintech.orion.dto.response.api.FieldDataValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 12/25/16.
 *
 */
public class DataComparator implements DocumentVerification {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataComparator.class);

    private  Map<String, VerificationConfiguration> verificationConfigurationMap;

    private DataValidationStrategy validationStrategy;

    private DocumentDataValidator validator;

    @Override
    public List<Object> verifyExtractedDocumentResult(OcrResponse ocrResponse,  Map<String, VerificationConfiguration> configurations) {
        verificationConfigurationMap = configurations;

        List<Object> fieldDataList = new ArrayList<>();
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
            responseFieldData.setComparison(getFieldComparisonList(fieldDataValueList, fieldData.getId()));
            fieldDataList.add(responseFieldData);
        }
        return fieldDataList;
    }

    private boolean isComparisonsAlreadyHappens(String baseId, String compareId, List<FieldDataComparision> comparision){
        boolean isComparisionPresent = false;
        for (FieldDataComparision c : comparision){
            if (c.getId().equalsIgnoreCase(getComparisionId(baseId, compareId)) || c.getId().equalsIgnoreCase(getComparisionId(compareId, baseId))){
                isComparisionPresent = true;
            }
        }
        return isComparisionPresent;
    }

    private String getComparisionId(String baseId, String compareId){
        return baseId + "##VS##" + compareId;
    }

    private List<FieldDataComparision> getFieldComparisonList(List<FieldDataValue> fieldDataValueList, String fieldName){
        List<FieldDataComparision> fieldDataComparisions = new ArrayList<>();
        DataValidationStrategy strategy = verificationStrategy(fieldName);
        if(strategy != null){
            validator = new DocumentDataValidator(strategy);
            for (FieldDataValue fieldDataValue : fieldDataValueList){
                fieldDataComparisions.addAll(compareValueWithOtherValues(fieldDataValue, fieldDataValueList));
            }
        }

        return fieldDataComparisions;
    }

    private List<FieldDataComparision> compareValueWithOtherValues(FieldDataValue base, List<FieldDataValue> values){
        List<FieldDataComparision> fieldDataComparision = new ArrayList<>();
        for (FieldDataValue value : values){
            if (!base.getId().equalsIgnoreCase(value.getId()) && !isComparisonsAlreadyHappens(base.getId(), value.getId(), fieldDataComparision)){
                ValidationResult result = validator.executeStrategy(base.getValue(), value.getValue());
                FieldDataComparision comparision = new FieldDataComparision();
                comparision.setId(getComparisionId(base.getId(), value.getId()));
                comparision.setValue(result.isStatus());
                fieldDataComparision.add(comparision);
            }
        }
        return fieldDataComparision;
    }

    private DataValidationStrategy verificationStrategy(String field){
        DataValidationStrategyProvider provider = new DataValidationStrategyProvider();
        VerificationConfiguration verificationConfiguration = verificationConfigurationMap.get(field);
        DataValidationStrategyType type;
        DataValidationStrategy strategy = null;
        try{
            type = verificationConfiguration.getSameValueComparisonStrategyAcrossMultipleResources();
            strategy = provider.getValidationStrategy(type);
        }catch (Exception e){
            LOGGER.warn("Could not find a same value comparison strategy across multiple resources for " +
                    "extraction field {} the full configuration for this extraction field is {} ",
                    field, verificationConfiguration);
        }

        return strategy;
    }
}
