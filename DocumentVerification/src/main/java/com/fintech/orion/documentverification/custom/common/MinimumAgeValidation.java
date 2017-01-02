package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.common.exception.DateComparatorException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by sasitha on 12/29/16.
 *
 */

public class MinimumAgeValidation extends ValidationHelper implements CustomValidation{

    private static final Logger LOGGER = LoggerFactory.getLogger(MinimumAgeValidation.class);

    private int minimumAge;

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (minimumAge <= 0 || getOcrExtractionFieldName() == null){
            throw new CustomValidationException("Minimum age / extraction field name parameters missing");
        }

        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()){
            try {
                validationData = validateMinimumAge(fieldData);
            } catch (DateComparatorException e) {
                throw new CustomValidationException("Error Occurred while performing minimum age verification ", e);
            }
        }
        if (!validationData.getValidationStatus()){
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setId("Minimum age verification");
        return validationData;
    }

    private ValidationData validateMinimumAge(OcrFieldData ocrFieldData) throws DateComparatorException {
        ValidationData validationData = new ValidationData();
        DateDecoder dateDecoder = new DateDecoder();
        LocalDate today = new LocalDate();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()){
            Date date = dateDecoder.decodeDate(fieldValue.getValue());
            LocalDate birthday = new LocalDate(date);
            Years age = Years.yearsBetween(birthday, today);
            if (age.getYears()<minimumAge){
                validationData.setRemarks(getFailedRemarksMessage());
                validationData.setValue(String.valueOf(age.getYears()));
                validationData.setValidationStatus(false);
                break;
            }else {
                validationData.setValidationStatus(true);
                validationData.setValue(String.valueOf(age.getYears()));
            }

        }
        return validationData;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }
}
