package com.fintech.orion.documentverification.custom.datavalidation;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.configuration.DocumentMrzDecodingConfigurations;
import com.fintech.orion.documentverification.common.configuration.factory.ConfigurationFactory;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.common.exception.MRZDecodingException;
import com.fintech.orion.documentverification.common.exception.MRZValidatingException;
import com.fintech.orion.documentverification.common.mrz.MRZDecodeResults;
import com.fintech.orion.documentverification.common.mrz.ValidateMRZ;
import com.fintech.orion.documentverification.common.mrz.ValidateMRZResult;
import com.fintech.orion.documentverification.custom.common.MrzLineBuilder;
import com.fintech.orion.documentverification.custom.common.OcrResponseReader;
import com.fintech.orion.documentverification.custom.common.ValidationHelper;
import com.fintech.orion.documentverification.strategy.DataValidationStrategy;
import com.fintech.orion.documentverification.strategy.DataValidationStrategyProvider;
import com.fintech.orion.documentverification.strategy.DocumentDataValidator;
import com.fintech.orion.documentverification.strategy.ValidationResult;
import com.fintech.orion.documentverification.template.category.TemplateCategory;
import com.fintech.orion.documentverification.template.category.factory.TemplateCategoryFactory;
import com.fintech.orion.documentverification.translator.OcrValueTranslator;
import com.fintech.orion.documentverification.translator.OcrValueTranslatorFactory;
import com.fintech.orion.documentverification.translator.exception.TranslatorException;
import com.fintech.orion.dto.configuration.DataValidationStrategyType;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.DataValidation;
import com.fintech.orion.dto.response.api.DataValidationValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AbstractDataValidation extends ValidationHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataValidation.class);
    @Autowired
    @Qualifier("documentMrzDecodingConfigurations")
    private List documentMrzDecodingConfigurations;

    @Autowired
    private DataValidationStrategyProvider dataValidationStrategyProvider;

    @Autowired
    private ConfigurationFactory commonConfigurationFactory;

    @Autowired
    private TemplateCategoryFactory templateCategoryFactory;

    @Autowired
    private OcrResponseReader ocrResponseReader;

    @Autowired
    private OcrValueTranslatorFactory ocrValueTranslatorFactory;

    private DataValidationStrategyType dataValidationStrategyType;

    private List<String> resourceNames;

    public DataValidation ocrExtractionFieldVizMrzDataValidation(ResourceName resourceName, OcrResponse ocrResponse)
            throws CustomValidationException {
        DataValidation dataValidation = new DataValidation();
        dataValidation.setId(getOcrExtractionFieldName());
        List<DataValidationValue> dataValidationValueList = new ArrayList<>();
        List<String> unFilteredResourceList = getResourceListFromOcrResponse(ocrResponse);
        for (String documentName : filterResourceNameList(unFilteredResourceList)){
            DataValidationValue dataValidationValue = getDataValidationValue(getTemplateName(documentName, ocrResponse),
                    documentName, ocrResponse);
            String templateCategory = ocrResponseReader.getTemplateCategory(documentName, ocrResponse);
            OcrValueTranslator<String,String> visualOcrValeTranslator = this.ocrValueTranslatorFactory.getOcrValueTranslator(getOcrExtractionFieldName());
            OcrValueTranslator<String,String> mrzOcrValueTranslator = this.ocrValueTranslatorFactory.getOcrValueTranslator("MRZ_"+getOcrExtractionFieldName());
            ValidationResult result = new ValidationResult();
            result.setStatus(false);
            try {
                Object translatedVisualValue = visualOcrValeTranslator.translate(dataValidationValue.getVizValue(), templateCategory);
                Object translatedMRZValue = mrzOcrValueTranslator.translate(dataValidationValue.getMrzValue(), templateCategory);
                DataValidationStrategy strategy = dataValidationStrategyProvider
                        .getValidationStrategy(dataValidationStrategyType);
                DocumentDataValidator validator =  new DocumentDataValidator(strategy);

                result = validator.executeStrategy(translatedMRZValue,
                        translatedVisualValue,
                        ocrResponseReader.getTemplateCategory(getTemplateName(documentName, ocrResponse)));
            } catch (TranslatorException e) {
                LOGGER.error("Error occurred while translating following values mrz : {} viz {} ",
                        dataValidationValue.getMrzValue(), dataValidationValue.getVizValue(), e);
            }
            dataValidationValue.setCriticalValidation(isCriticalValidation());
            if (result.isStatus()){
                dataValidationValue.setStatus(true);
                dataValidationValue.setRemarks(getSuccessRemarksMessage());
            }
            dataValidationValueList.add(dataValidationValue);
        }
        dataValidation.setValue(dataValidationValueList);
        return dataValidation;
    }

    private DataValidationValue getDataValidationValue(String templateName,
                                                       String documentName, OcrResponse ocrResponse)
            throws CustomValidationException {
        DataValidationValue dataValidationValue = new DataValidationValue();
        dataValidationValue.setDocumentName(documentName);
        dataValidationValue.setStatus(false);
        dataValidationValue.setRemarks(getFailedRemarksMessage());
        dataValidationValue.setMrzValue("");
        dataValidationValue.setVizValue(getVizValue(getOcrExtractionFieldName(), documentName, ocrResponse));
        MrzLineBuilder mrzLineBuilder = new MrzLineBuilder();
        extractMrzValueFromOcrResponse(documentName, ocrResponse, dataValidationValue, mrzLineBuilder,
                commonConfigurationFactory.getConfiguration(getTemplateCategory(templateName)));
        return dataValidationValue;
    }

    private void extractMrzValueFromOcrResponse(String documentName, OcrResponse ocrResponse,
                                                DataValidationValue dataValidationValue, MrzLineBuilder mrzLineBuilder,
                                                DocumentMrzDecodingConfigurations configuration) {
        MRZDecodeResults decodeResults;
        String singleMrzLine = mrzLineBuilder.buildSingleLineMRZ(ocrResponse, documentName,
                configuration.getMrzOcrExtractionFieldBase(),
                configuration.getMrzLineCount());
        try {
            ValidateMRZResult validateMRZResult = validateMrz(singleMrzLine, configuration.getMrzValidationStrategy());
            if ("true".equalsIgnoreCase(validateMRZResult.getValidationResult())) {
                decodeResults = configuration.getMrzDecodingStrategy().decode(singleMrzLine);
                dataValidationValue.setMrzValue(getMrzValueForOcrExtractionField(documentName,
                        getOcrExtractionFieldName(), decodeResults));
            }
        } catch (MRZDecodingException e) {
            dataValidationValue.setRemarks("Unable to decoding MRZ : " + singleMrzLine);
            LOGGER.error("Error decoding the mrz line {}", e);
        } catch (MRZValidatingException e) {
            dataValidationValue.setRemarks("Invalid MRZ detected : " + singleMrzLine);
            LOGGER.error("Error validating mrz line {}", e);
        }
    }

    private List<String> filterResourceNameList(List<String> fullList){
        return fullList.stream()
                .filter(r -> this.resourceNames.contains(r)).collect(Collectors.toList());
    }

    public String getVizValue(String extractionFieldName, String documentName, OcrResponse ocrResponse){
        OcrFieldData fieldData = getFieldDataById(extractionFieldName, ocrResponse);
        OcrFieldValue fieldValue = getFieldValueById(documentName+"##"+extractionFieldName, fieldData);
        return fieldValue.getValue();
    }

    private ValidateMRZResult validateMrz(String mrz, ValidateMRZ mrzValidator) throws MRZValidatingException {
        return mrzValidator.validate(mrz);
    }

    public String getMrzValueForOcrExtractionField(String documentName,
                                                   String ocrExtractionField, MRZDecodeResults decodeResults){
        MrzDecodeDataExtraction extraction = new MrzDecodeDataExtraction();
        return extraction.getMRZValue(documentName, ocrExtractionField, decodeResults);
    }

    public void setDataValidationStrategyType(DataValidationStrategyType dataValidationStrategyType) {
        this.dataValidationStrategyType = dataValidationStrategyType;
    }

    public DataValidationStrategyType getDataValidationStrategyType() {
        return dataValidationStrategyType;
    }

    public String getTemplateName(String document, OcrResponse ocrResponse) {
        String templateName = "TemplateName";
        OcrFieldData ocrFieldData = getFieldDataById(templateName, ocrResponse);
        OcrFieldValue ocrFieldValue = getFieldValueById(document + "##" + templateName, ocrFieldData);
        return ocrFieldValue.getValue();
    }

    public String getTemplateCategory(String templateName) throws CustomValidationException {
        TemplateCategory templateCategory = templateCategoryFactory.getTemplateCategory(templateName, "Common");
        if(templateCategory.getCategoryName() == null) {
            throw new CustomValidationException("No valid Template Category Found for the relevant " +
                    "Template Name : " + templateName);
        }
        return templateCategory.getCategoryName();
    }

    public void setResourceNames(List<String> resourceNames) {
        this.resourceNames = resourceNames;
    }
}
