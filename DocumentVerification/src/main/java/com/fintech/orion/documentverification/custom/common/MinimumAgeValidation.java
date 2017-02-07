package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.common.exception.DateComparatorException;
import com.fintech.orion.documentverification.common.exception.DateDecoderException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by sasitha on 12/29/16.
 */

public class MinimumAgeValidation extends ValidationHelper implements CustomValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinimumAgeValidation.class);

    private int minimumAge;

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (minimumAge <= 0 || getOcrExtractionFieldName() == null) {
            throw new CustomValidationException("Minimum age / extraction field name parameters missing");
        }

        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            try {
                validationData = validateMinimumAge(fieldData);
            } catch (DateComparatorException e) {
                LOGGER.warn("Error occurred while performing an minimum age" +
                        " validation for ocr response {} {}", ocrResponse, e);
                validationData.setValue(null);
                validationData.setOcrConfidence(null);
                validationData.setValidationStatus(false);
                validationData.setRemarks("Error occurred while performing the minimum age validation. " +
                        "This is most likely " +
                        "due to an unsupported date format. Supported date formats are," +
                        "DD MM/MM YY or DD.MM.YYYY");
            }
        }
        if (!validationData.getValidationStatus()) {
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setId("Minimum Age Verification");
        return validationData;
    }

    private ValidationData validateMinimumAge(OcrFieldData ocrFieldData) throws DateComparatorException {
        ValidationData validationData = new ValidationData();
        DateDecoder dateDecoder = new DateDecoder();
        LocalDate today = new LocalDate();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()) {
            Date date = null;
            try {
                date = dateDecoder.decodeDate(fieldValue.getValue());
            } catch (DateDecoderException e) {
                throw new DateComparatorException("Unable to decode the given date " , e);
            }
            LocalDate birthday = new LocalDate(date);
            Years age = Years.yearsBetween(birthday, today);
            if (age.getYears() < minimumAge) {
                validationData.setRemarks(getFailedRemarksMessage());
                validationData.setValue(String.valueOf(age.getYears()));
                validationData.setValidationStatus(false);
                break;
            } else {
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
