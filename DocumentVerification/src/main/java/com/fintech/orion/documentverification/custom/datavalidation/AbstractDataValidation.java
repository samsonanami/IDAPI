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
import com.fintech.orion.documentverification.comparison.ComparisonValueHolder;
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

    private Integer vizValueSubStringLength;

    public DataValidation ocrExtractionFieldVizMrzDataValidation(ResourceName resourceName, OcrResponse ocrResponse)
            throws CustomValidationException {
        DataValidation dataValidation = new DataValidation();
        dataValidation.setId(getOcrExtractionFieldName());
        List<DataValidationValue> dataValidationValueList = new ArrayList<>();
        List<String> unFilteredResourceList = getResourceListFromOcrResponse(ocrResponse);
        for (String documentName : filterResourceNameList(unFilteredResourceList)) {

            List<ComparisonValueHolder> visualValueSet = new ArrayList<>();
            List<ComparisonValueHolder> mrzValueSet = new ArrayList<>();
            DataValidationValue dataValidationValue = new DataValidationValue();
            dataValidationValue.setDocumentName(documentName);

            List<String> processedStatus = new ArrayList<>();
            processedStatus.add("NPP");
            processedStatus.add("PP");
            OcrValueTranslator<String, String> visualOcrValeTranslator =
                    this.ocrValueTranslatorFactory.getOcrValueTranslator(getOcrExtractionFieldName());
            OcrValueTranslator<String, String> mrzOcrValueTranslator =
                    this.ocrValueTranslatorFactory.getOcrValueTranslator("MRZ_" + getOcrExtractionFieldName());
            String templateCategory = ocrResponseReader.getTemplateCategory(documentName, ocrResponse);
            MrzLineBuilder mrzLineBuilder = new MrzLineBuilder();
            for (String preProcessedStatus : processedStatus) {
                ComparisonValueHolder visualValue = new ComparisonValueHolder();
                ComparisonValueHolder mrZvalue = new ComparisonValueHolder();
                visualValue.setId(documentName + "##" + getOcrExtractionFieldName() + "##VIS##" + preProcessedStatus);
                mrZvalue.setId(documentName + "##" + getOcrExtractionFieldName() + "##MRZ##" + preProcessedStatus);
                String vizValue = getVizValue(getOcrExtractionFieldName(), documentName, ocrResponse, preProcessedStatus);

                try {
                    Object translatedVisualValue = visualOcrValeTranslator.translate(vizValue, templateCategory);
                    translatedVisualValue = getVisualValueSubstring(translatedVisualValue);
                    visualValue.setValue(translatedVisualValue);
                    visualValueSet.add(visualValue);
                } catch (TranslatorException e) {
                    LOGGER.warn("Error while translating {} field value from visual inspection zone. " +
                                    "Extracted OCR value : {} ",
                            getOcrExtractionFieldName(), vizValue, e);
                }
                String rawMRZValue = null;
                try {
                    rawMRZValue = extractMrzValueFromOcrResponse(documentName, ocrResponse, mrzLineBuilder,
                            commonConfigurationFactory.getConfiguration(getTemplateCategory(getTemplateName(documentName, ocrResponse))),
                            preProcessedStatus);
                    mrZvalue.setValue(mrzOcrValueTranslator.translate(rawMRZValue, templateCategory));
                    mrzValueSet.add(mrZvalue);
                } catch (MRZValidatingException e) {
                    LOGGER.warn("Could not validate the given MRZ " +
                            "(MRZ validation is mandatory prior to extracting results from MRZ). ", e);
                } catch (MRZDecodingException e) {
                    LOGGER.warn("Could not decode the given MRZ ", e);
                } catch (TranslatorException e) {
                    LOGGER.warn("Error translating {} field value from magnetic read zone. Extracted value is : {} ",
                            getOcrExtractionFieldName(), rawMRZValue, e);
                }
            }
            validateMrzViZvalue(dataValidationValue, visualValueSet, mrzValueSet,
                    ocrResponseReader.getTemplateCategory(getTemplateName(documentName, ocrResponse)));
            dataValidationValueList.add(dataValidationValue);
        }
        dataValidation.setValue(dataValidationValueList);
        return dataValidation;
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

    public void setVizValueSubStringLength(Integer vizValueSubStringLength) {
        this.vizValueSubStringLength = vizValueSubStringLength;
    }

    private Object getVisualValueSubstring(Object translatedVisualValue) {
        Object visualValue = translatedVisualValue;
        if(visualValue.getClass().equals(String.class) && vizValueSubStringLength != null){
            visualValue = visualValue.toString().substring(0, vizValueSubStringLength);
        }
        return visualValue;
    }

    private void validateMrzViZvalue(DataValidationValue dataValidationValue,
                                     List<ComparisonValueHolder> visualValueSet,
                                     List<ComparisonValueHolder> mrzValueSet,
                                     String templateCategory) {

        for (ComparisonValueHolder visualComparisonValue: visualValueSet){
            for (ComparisonValueHolder mrzComparisonValue: mrzValueSet){
                DataValidationStrategy strategy = dataValidationStrategyProvider
                        .getValidationStrategy(dataValidationStrategyType);
                ValidationResult result = new ValidationResult();
                result.setStatus(false);
                DocumentDataValidator validator =  new DocumentDataValidator(strategy);
                result = validator.executeStrategy(mrzComparisonValue.getValue(),
                        visualComparisonValue.getValue(),
                        templateCategory);
                if (result.isStatus()){
                    dataValidationValue.setMrzValue(mrzComparisonValue.getValue().toString());
                    dataValidationValue.setVizValue(visualComparisonValue.getValue().toString());
                    dataValidationValue.setStatus(result.isStatus());
                    break;
                }

            }
        }

    }

    private String extractMrzValueFromOcrResponse(String documentName, OcrResponse ocrResponse,
                                                MrzLineBuilder mrzLineBuilder,
                                                DocumentMrzDecodingConfigurations configuration, String preProcessedStatus) throws MRZValidatingException, MRZDecodingException {
        MRZDecodeResults decodeResults;
        String singleMrzLine = mrzLineBuilder.buildSingleLineMRZ(ocrResponse, documentName,
                configuration.getMrzOcrExtractionFieldBase(),
                configuration.getMrzLineCount(), preProcessedStatus);
        String mrzValue ="";
        ValidateMRZResult validateMRZResult = validateMrz(singleMrzLine, configuration.getMrzValidationStrategy());
        if ("true".equalsIgnoreCase(validateMRZResult.getValidationResult())) {
            decodeResults = configuration.getMrzDecodingStrategy().decode(singleMrzLine);
            mrzValue = getMrzValueForOcrExtractionField(documentName,
                    getOcrExtractionFieldName(), decodeResults);
        }

        return mrzValue;
    }

    private List<String> filterResourceNameList(List<String> fullList){
        return fullList.stream()
                .filter(r -> this.resourceNames.contains(r)).collect(Collectors.toList());
    }

    private String getVizValue(String extractionFieldName, String documentName, OcrResponse ocrResponse, String preProcessedStatus){
        OcrFieldData fieldData = getFieldDataById(extractionFieldName, ocrResponse);
        OcrFieldValue fieldValue = getFieldValueById(documentName+"##"+extractionFieldName + "##" + preProcessedStatus, fieldData);
        return fieldValue.getValue();
    }

    private ValidateMRZResult validateMrz(String mrz, ValidateMRZ mrzValidator) throws MRZValidatingException {
        return mrzValidator.validate(mrz);
    }

    private String getMrzValueForOcrExtractionField(String documentName,
                                                   String ocrExtractionField, MRZDecodeResults decodeResults){
        MrzDecodeDataExtraction extraction = new MrzDecodeDataExtraction();
        return extraction.getMRZValue(documentName, ocrExtractionField, decodeResults);
    }

    public void setDataValidationStrategyType(DataValidationStrategyType dataValidationStrategyType) {
        this.dataValidationStrategyType = dataValidationStrategyType;
    }

    private String getTemplateName(String document, OcrResponse ocrResponse) {
        String templateName = "TemplateName";
        OcrFieldData ocrFieldData = getFieldDataById(templateName, ocrResponse);
        OcrFieldValue ocrFieldValue = getFieldValueById(document + "##" + templateName, ocrFieldData);
        return ocrFieldValue.getValue();
    }

}
