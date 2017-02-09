package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.documentverification.strategy.OperationDateComparator;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MudithaJ on 12/27/2016.
 */
@Component
public class DateOfBirthValidation extends ValidationHelper implements CustomValidation {

    @Autowired
    private OperationDateComparator dateComparator;


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            validationData = validateDateOfBirth(fieldData.getValue());
        }
        validationData.setId("Date of Birth Validation");
        return validationData;
    }

    private ValidationData validateDateOfBirth(List<OcrFieldValue> values) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        if (!values.isEmpty()) {
            String firstDateOfBirth = values.iterator().next().getValue();
            validationData = compareRestOfTheDatesWithBaseDate(firstDateOfBirth, values);
        } else {
            validationData.setValidationStatus(false);
            validationData.setRemarks("Not Enough data to complete the validation. Need two or more date of births from" +
                    "multiple documents to complete this verification.");
        }
        return validationData;
    }

    private ValidationData compareRestOfTheDatesWithBaseDate(String base, List<OcrFieldValue> values) {
        ValidationData validationData = new ValidationData();
        for (OcrFieldValue value : values) {
            if (!dateComparator.doDataValidationOperation(base, value.getValue()).isStatus()) {
                validationData.setValidationStatus(false);
                validationData.setValue(value.getValue());
                validationData.setRemarks(getFailedRemarksMessage());
                break;
            } else {
                validationData.setValidationStatus(true);
                validationData.setValue(base);
                validationData.setRemarks(getSuccessRemarksMessage());
            }
        }
        return validationData;
    }
}
