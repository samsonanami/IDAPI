package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.springframework.stereotype.Component;

/**
 * Created by MudithaJ on 12/26/2016.
 *
 */
@Component
public class GenderValidation extends ValidationHelper implements CustomValidation {


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        if(fieldData.getValue().isEmpty()){
            throw new CustomValidationException("Could not verify gender not enough data");
        }
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            validationData = validateGender(fieldData);
        }
        validationData.setId("Gender Verification");
        validationData.setCriticalValidation(isCriticalValidation());
        return validationData;
    }

    private ValidationData validateGender(OcrFieldData ocrFieldData) {
        ValidationData validationData = new ValidationData();
        if (!ocrFieldData.getValue().isEmpty()) {
            Gender genderBaseValue = null;
            for (OcrFieldValue value : ocrFieldData.getValue()) {
                Gender compare = getGender(value.getValue());
                if (genderBaseValue == null && compare != null){
                    genderBaseValue = compare;
                }
                if (genderBaseValue != null && genderBaseValue.equals(compare)) {
                    validationData.setRemarks(getSuccessRemarksMessage() + genderBaseValue);
                    validationData.setValue(genderBaseValue.toString());
                    validationData.setValidationStatus(true);
                    return validationData;
                } else {
                    validationData.setRemarks(getFailedRemarksMessage());
                    validationData.setValidationStatus(false);
                }
            }
            if (genderBaseValue != null) {
                validationData.setValue(genderBaseValue.toString());
            }
        }

        return validationData;
    }

    private Gender getGender(String gender) {
        Gender ge = null;
        if ("M".equalsIgnoreCase(gender) || "Male".equalsIgnoreCase(gender)) {
            ge = Gender.MALE;
        } else if ("F".equalsIgnoreCase(gender) || "Female".equalsIgnoreCase(gender)) {
            ge = Gender.FEMALE;
        }
        return ge;
    }
}
