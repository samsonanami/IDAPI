package com.fintech.orion.documentverification.factory;

import com.fintech.orion.documentverification.custom.common.OcrResponseReader;
import com.fintech.orion.documentverification.strategy.DataValidationStrategy;
import com.fintech.orion.documentverification.strategy.DataValidationStrategyProvider;
import com.fintech.orion.documentverification.strategy.DocumentDataValidator;
import com.fintech.orion.documentverification.strategy.ValidationResult;
import com.fintech.orion.documentverification.translator.OcrValueTranslator;
import com.fintech.orion.documentverification.translator.OcrValueTranslatorFactory;
import com.fintech.orion.documentverification.translator.exception.TranslatorException;
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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private OcrValueTranslatorFactory ocrValueTranslatorFactory;

    @Resource(name = "preProcessNonePreProcessDelimiterList")
    private List<String> preProcessNonePreProcessDelimiterList;

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
            if(isComparisonEnabledForField(fieldData.getId())){
                responseFieldData.setComparison(getFieldComparisonList(fieldDataValueList,
                        fieldData.getId(), ocrResponse));
            }
            fieldDataList.add(responseFieldData);
        }
        return fieldDataList;
    }

    private boolean isComparisonEnabledForField(String ocrExtractionField){
        return verificationConfigurationMap.keySet().contains(ocrExtractionField);
    }

    private boolean isComparisonsAlreadyHappens(String baseId, String compareId, List<FieldDataComparision> comparision) {
        boolean isComparisionPresent = false;
        for (FieldDataComparision c : comparision) {
            if (c.getId().contains(getDocumentName(baseId)) && c.getId().contains(getDocumentName(compareId))) {
                isComparisionPresent = true;
            }
        }
        return isComparisionPresent;
    }

    private String getDocumentName(String id){
        String documentName = "";
        if (id.split("##").length > 0){
            documentName = id.split("##")[0];
        }
        return documentName;
    }

    private String getComparisionId(String baseId, String compareId) {
        return baseId + "##VS##" + compareId;
    }

    private List<FieldDataComparision> getFieldComparisonList(List<FieldDataValue> fieldDataValueList, String fieldName, OcrResponse ocrResponse) {
        List<FieldDataComparision> fieldDataComparision = new ArrayList<>();
        DataValidationStrategy strategy = verificationStrategy(fieldName);
        if (strategy != null) {
            validator = new DocumentDataValidator(strategy);

            if (!fieldDataValueList.isEmpty()) {
                for (FieldDataValue fieldDataValue : fieldDataValueList) {

                    fieldDataComparision.addAll(compareValueWithOtherValues(fieldDataValue, fieldDataValueList, ocrResponse, fieldName));
                }
            }

        }

        return fieldDataComparision;
    }

    private List<FieldDataValue> filterFieldDataValueList(FieldDataValue base, List<FieldDataValue> values) {

        String idOfTheFirstObject = base.getId();
        String prefixOfFirstObjectId = idOfTheFirstObject.substring(0, idOfTheFirstObject.lastIndexOf('#') + 1);
        String suffixOfFirstObjectId = idOfTheFirstObject.split("##")[2].toString();

        if (suffixOfFirstObjectId.equals("PP")) {
            String removeObjectId = prefixOfFirstObjectId.concat("NPP");
            return values.stream().filter(p -> !p.getId().equals(removeObjectId)).collect(Collectors.toList());
        } else if (suffixOfFirstObjectId.equals("NPP")) {
            String removeObjectId = prefixOfFirstObjectId.concat("PP");
            return values.stream().filter(p -> !p.getId().equals(removeObjectId)).collect(Collectors.toList());
        }
        return values;
    }

    private List<FieldDataComparision> compareValueWithOtherValues(FieldDataValue base, List<FieldDataValue> values
            , OcrResponse ocrResponse, String fieldName) {
        OcrValueTranslator ocrValueTranslator = this.ocrValueTranslatorFactory.getOcrValueTranslator(fieldName);

        List<FieldDataComparision> fieldDataComparision = new ArrayList<>();

        List<FieldDataValue> filteredFieldDataValueList = filterFieldDataValueList(base, values);

        for (FieldDataValue value : filteredFieldDataValueList) {

            if (!base.getId().equalsIgnoreCase(value.getId()) && !isComparisonsAlreadyHappens(base.getId()
                    , value.getId(), fieldDataComparision)) {
                String baseTemplateCategory = responseReader.getTemplateCategory(base.getId(), ocrResponse);
                String compareTemplateCategory = responseReader.getTemplateCategory(value.getId(), ocrResponse);
                ValidationResult result = new ValidationResult();
                result.setStatus(false);
                try {
                    Object translatedBaseValue = ocrValueTranslator.translate(base.getValue(), baseTemplateCategory);
                    Object translatedCompareValue = ocrValueTranslator.translate(value.getValue(), compareTemplateCategory);
                    result = validator.executeStrategy(translatedBaseValue, translatedCompareValue, compareTemplateCategory);
                } catch (TranslatorException e) {
                    LOGGER.error("unable to translate values ", e);
                }

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
