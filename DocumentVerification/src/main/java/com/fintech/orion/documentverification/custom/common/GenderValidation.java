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
 */
@Component
public class GenderValidation extends ValidationHelper implements CustomValidation {


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            validationData = validateGender(fieldData);
        }
        validationData.setId("Gender Verification");
        return validationData;
    }

    private ValidationData validateGender(OcrFieldData ocrFieldData) {
        ValidationData validationData = new ValidationData();
        if (ocrFieldData.getValue().size() >= 1) {
            Gender genderBaseValue = getGender(ocrFieldData.getValue().iterator().next().getValue());
            for (OcrFieldValue value : ocrFieldData.getValue()) {
                Gender compare = getGender(value.getValue());
                if (genderBaseValue == Gender.OTHER || !genderBaseValue.equals(compare)) {
                    validationData.setRemarks(getFailedRemarksMessage());
                    validationData.setValidationStatus(false);
                    break;
                } else {
                    validationData.setRemarks(getSuccessRemarksMessage() + genderBaseValue);
                    validationData.setValidationStatus(true);
                }
            }
            validationData.setValue(genderBaseValue.toString());
        }

        return validationData;
    }

    private Gender getGender(String gender) {
        Gender ge = Gender.OTHER;
        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")) {
            ge = Gender.MALE;
        } else if (gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female")) {
            ge = Gender.FEMALE;
        } else {
            ge = Gender.OTHER;
        }

        return ge;
    }
}
