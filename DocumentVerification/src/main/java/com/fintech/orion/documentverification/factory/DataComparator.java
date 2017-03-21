package com.fintech.orion.documentverification.factory;

import com.fintech.orion.documentverification.custom.common.OcrResponseReader;
import com.fintech.orion.documentverification.strategy.DataValidationStrategy;
import com.fintech.orion.documentverification.strategy.DataValidationStrategyProvider;
import com.fintech.orion.documentverification.strategy.DocumentDataValidator;
import com.fintech.orion.documentverification.strategy.ValidationResult;
import com.fintech.orion.dto.configuration.DataValidationStrategyType;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.FieldData;
import com.fintech.orion.dto.response.api.FieldDataComparision;
import com.fintech.orion.dto.response.api.FieldDataValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 12/25/16.
 */
public class DataComparator implements DocumentVerification {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataComparator.class);

    private Map<String, VerificationConfiguration> verificationConfigurationMap;

    private DocumentDataValidator validator;

    @Autowired
    @Qualifier("defaultOcrResponseReader")
    private OcrResponseReader responseReader;

    @Autowired
    private DataValidationStrategyProvider dataValidationStrategyProvider;

    @Override
    public List<Object> verifyExtractedDocumentResult(OcrResponse ocrResponse, Map<String, VerificationConfiguration> configurations) {
        verificationConfigurationMap = configurations;

        List<Object> fieldDataList = new ArrayList<>();
        for (OcrFieldData fieldData : ocrResponse.getData()) {
            FieldData responseFieldData = new FieldData();
            responseFieldData.setId(fieldData.getId());
            List<FieldDataValue> fieldDataValueList = new ArrayList<>();
            for (OcrFieldValue value : fieldData.getValue()) {
                FieldDataValue responseFieldDataValue = new FieldDataValue();
                responseFieldDataValue.setId(value.getId());
                responseFieldDataValue.setValue(value.getValue());
                responseFieldDataValue.setConfidence(value.getConfidence());
                fieldDataValueList.add(responseFieldDataValue);
            }
            responseFieldData.setValue(fieldDataValueList);
            responseFieldData.setComparison(getFieldComparisonList(fieldDataValueList, fieldData.getId(), ocrResponse));
            fieldDataList.add(responseFieldData);
        }
        return fieldDataList;
    }

    private boolean isComparisonsAlreadyHappens(String baseId, String compareId, List<FieldDataComparision> comparision) {
        boolean isComparisionPresent = false;
        for (FieldDataComparision c : comparision) {
            if (c.getId().equalsIgnoreCase(getComparisionId(baseId, compareId)) || c.getId().equalsIgnoreCase(getComparisionId(compareId, baseId))) {
                isComparisionPresent = true;
            }
        }
        return isComparisionPresent;
    }

    private String getComparisionId(String baseId, String compareId) {
        return baseId + "##VS##" + compareId;
    }

    private List<FieldDataComparision> getFieldComparisonList(List<FieldDataValue> fieldDataValueList, String fieldName, OcrResponse ocrResponse) {
        List<FieldDataComparision> fieldDataComparisions = new ArrayList<>();
        DataValidationStrategy strategy = verificationStrategy(fieldName);
        if (strategy != null) {
            validator = new DocumentDataValidator(strategy);
            for (FieldDataValue fieldDataValue : fieldDataValueList) {
                fieldDataComparisions.addAll(compareValueWithOtherValues(fieldDataValue, fieldDataValueList, ocrResponse));
            }
        }

        return fieldDataComparisions;
    }

    private List<FieldDataComparision> compareValueWithOtherValues(FieldDataValue base, List<FieldDataValue> values
            , OcrResponse ocrResponse) {
        List<FieldDataComparision> fieldDataComparision = new ArrayList<>();
        for (FieldDataValue value : values) {
            if (!base.getId().equalsIgnoreCase(value.getId()) && !isComparisonsAlreadyHappens(base.getId()
                    , value.getId(), fieldDataComparision)) {
                String templateCategory = responseReader.getTemplateCategory(value.getId(), ocrResponse);
                ValidationResult result = validator.executeStrategy(base.getValue(), value.getValue(), templateCategory);
                FieldDataComparision comparision = new FieldDataComparision();
                comparision.setId(getComparisionId(base.getId(), value.getId()));
                comparision.setValue(result.isStatus());
                fieldDataComparision.add(comparision);
            }
        }
        return fieldDataComparision;
    }

    private DataValidationStrategy verificationStrategy(String field) {
        VerificationConfiguration verificationConfiguration = verificationConfigurationMap.get(field);
        DataValidationStrategyType type;
        DataValidationStrategy strategy = null;
        try {
            type = verificationConfiguration.getSameValueComparisonStrategyAcrossMultipleResources();
            strategy = dataValidationStrategyProvider.getValidationStrategy(type);
        } catch (Exception e) {
            LOGGER.warn("Could not find a same value comparison strategy across multiple resources for " +
                            "extraction field {} the full configuration for this extraction field is {} ",
                    field, verificationConfiguration, e);
        }

        return strategy;
    }
}
