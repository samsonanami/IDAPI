package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.checkdigit.PassportCheckDigitFormation;
import com.fintech.orion.documentverification.common.configuration.DocumentMrzDecodingConfigurations;
import com.fintech.orion.documentverification.common.configuration.factory.ConfigurationFactory;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.common.exception.MRZValidatingException;
import com.fintech.orion.documentverification.common.mrz.PassportMRZDecodingStrategy;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.documentverification.template.category.TemplateCategory;
import com.fintech.orion.documentverification.template.category.factory.TemplateCategoryFactory;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sasitha on 1/13/17.
 *
 */
public class MrzCheckDigitValidation extends ValidationHelper implements CustomValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(MrzCheckDigitValidation.class);
    private String ocrFieldBase;
    private int mrzLineCount;
    private String resourceNameToCheckMRZ;

    private MrzLineBuilder mrzLineBuilder = new MrzLineBuilder();

    @Autowired
    private PassportMRZDecodingStrategy passportMRZDecodingStrategy;

    @Autowired
    private PassportCheckDigitFormation passportCheckDigitFormation;

    @Autowired
    private OcrResponseReader ocrResponseReader;

    @Autowired
    private TemplateCategoryFactory templateCategoryFactory;

    @Autowired
    private ConfigurationFactory commonConfigurationFactory;


    private List<String> resourceNames;

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {

        ValidationData validationData = new ValidationData();

        List<String> unFilteredResourceList = getResourceListFromOcrResponse(ocrResponse);
        for (String documentName: filterResourceNameList(unFilteredResourceList)){
            String completeMrzLine = mrzLineBuilder.buildSingleLineMRZ(ocrResponse, documentName,
                    ocrFieldBase, mrzLineCount);
            if (completeMrzLine != null && !completeMrzLine.isEmpty()){
                try {
                    validationData = validateMrz(completeMrzLine, documentName, ocrResponse);
                } catch (MRZValidatingException e) {
                    validationData.setValidationStatus(false);
                    validationData.setRemarks(getFailedRemarksMessage());
                    LOGGER.error("Error occured while performing mrz check dight validation ", e);
                }
            }

        }
        if(validationData.getValidationStatus()){
            validationData.setRemarks(getSuccessRemarksMessage());
        }else {
            validationData.setRemarks(getFailedRemarksMessage());
        }
        validationData.setId("MRZ Check digit validation");
        validationData.setCriticalValidation(isCriticalValidation());
        return validationData;
    }

    private ValidationData validateMrz(String fullMrzLine, String resourceName, OcrResponse ocrResponse)
            throws CustomValidationException, MRZValidatingException {

        String templateName = getTemplateName(resourceName, ocrResponse);

        DocumentMrzDecodingConfigurations configuration =
                commonConfigurationFactory.getConfiguration(getTemplateCategory(templateName));

        ValidationData validationData = new ValidationData();
        validationData.setValidationStatus(false);
        String mrzValidationResult = configuration.getMrzValidationStrategy().validate(fullMrzLine).getValidationResult();
        validationData.setValidationStatus(Boolean.valueOf(mrzValidationResult));
        validationData.setValue(fullMrzLine);
        return validationData;
    }

    public void setOcrFieldBase(String ocrFieldBase) {
        this.ocrFieldBase = ocrFieldBase;
    }

    public void setMrzLineCount(int mrzLineCount) {
        this.mrzLineCount = mrzLineCount;
    }

    public void setResourceNameToCheckMRZ(String resourceNameToCheckMRZ) {
        this.resourceNameToCheckMRZ = resourceNameToCheckMRZ;
    }

    private String getTemplateName(String document, OcrResponse ocrResponse) {
        String templateName = "TemplateName";
        OcrFieldData ocrFieldData = getFieldDataById(templateName, ocrResponse);
        OcrFieldValue ocrFieldValue = getFieldValueById(document + "##" + templateName, ocrFieldData);
        return ocrFieldValue.getValue();
    }

    private List<String> filterResourceNameList(List<String> fullList){
        return fullList.stream()
                .filter(r -> this.resourceNames.contains(r)).collect(Collectors.toList());
    }

    public void setResourceNames(List<String> resourceNames) {
        this.resourceNames = resourceNames;
    }

    public String getTemplateCategory(String templateName) throws CustomValidationException {
        TemplateCategory templateCategory = templateCategoryFactory.getTemplateCategory(templateName, "Common");
        if(templateCategory.getCategoryName() == null) {
            throw new CustomValidationException("No valid Template Category Found for the relevant " +
                    "Template Name : " + templateName);
        }
        return templateCategory.getCategoryName();
    }
}
