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
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by MudithaJ on 1/2/2017.
 */
public class AgeLimitValidation  extends ValidationHelper implements CustomValidation {


    private int minimumAge;
    private int maximumAge;


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (minimumAge <= 0 || getOcrExtractionFieldName() == null){
            throw new CustomValidationException("Minimum age / extraction field name parameters missing");
        }

        if (maximumAge <= 0 || getOcrExtractionFieldName() == null){
            throw new CustomValidationException("Maximum age / extraction field name parameters missing");
        }

        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()){
            try {
                validationData = validateAgeLimit(fieldData);
            } catch (DateComparatorException e) {
                throw new CustomValidationException("Error Occurred while performing age limit verification ", e);
            }
        }
        if (!validationData.getValidationStatus()){
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setId("Age limit verification");
        return validationData;
    }

    private ValidationData validateAgeLimit(OcrFieldData ocrFieldData) throws DateComparatorException {
        ValidationData validationData = new ValidationData();
        DateDecoder dateDecoder = new DateDecoder();
        LocalDate today = new LocalDate();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()){
            Date date = dateDecoder.decodeDate(fieldValue.getValue());
            LocalDate birthday = new LocalDate(date);
            Years age = Years.yearsBetween(birthday, today);

            if (age.getYears()>minimumAge && age.getYears()<maximumAge ){
                validationData.setValidationStatus(true);
                validationData.setValue(String.valueOf(age.getYears()));
            }else {
                validationData.setRemarks(getFailedRemarksMessage());
                validationData.setValue(String.valueOf(age.getYears()));
                validationData.setValidationStatus(false);
                break;
            }

        }
        return validationData;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

}