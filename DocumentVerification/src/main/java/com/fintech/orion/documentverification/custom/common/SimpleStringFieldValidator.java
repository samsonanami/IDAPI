package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sasitha on 12/29/16.
 *
 * Simple String Field Validator validate the values of a given ocr extraction field among multiple resources.
 * Example :
 *          if the string field is set to 'surname' this class will go through the given ocrResponse and string compare
 *          values extracted from different documents like passport, drivingLicenseFront
 * If verification is successful the success message will be returned with status. If the verification is failed
 * the failed remarks message will be return with the status
 *
 * Usage is simple create a bean of this class with correct values. stringField,successRemarksMessage and
 * FailedRemarksMessage and add it to the verification list either idDocFullValidations or addressDocFullValidations
 *
 */
public class SimpleStringFieldValidator extends ValidationHelper implements CustomValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleStringFieldValidator.class);
    private String verificationDisplayName;

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (getOcrExtractionFieldName() == null || getOcrExtractionFieldName().isEmpty()) {
            throw new CustomValidationException("String field cannot be a null or empty value");
        }
        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()){
            validationData = isAllOcrFieldValueHasSameValueField(fieldData.getValue());
        }
        if (validationData.getValidationStatus()){
            validationData.setRemarks(getSuccessRemarksMessage());
        }else {
            validationData.setRemarks(getFailedRemarksMessage());
            LOGGER.warn("Custom validation "+verificationDisplayName+" failed for field {} complete validation " +
                            "status is {}", getOcrExtractionFieldName(),
                    validationData);
        }
        validationData.setId(verificationDisplayName);
        return validationData;
    }

    public void setVerificationDisplayName(String verificationDisplayName) {
        this.verificationDisplayName = verificationDisplayName;
    }
}
