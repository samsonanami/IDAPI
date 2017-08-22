package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MudithaJ on 1/2/2017.
 * TODO fix this
 */
public class AgeLimitValidation extends ValidationHelper implements CustomValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgeLimitValidation.class);

    private int minimumAge;
    private int maximumAge;


    @Autowired
    private DateDecoder dateDecoder;

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (minimumAge <= 0 || getOcrExtractionFieldName() == null) {
            throw new CustomValidationException("Minimum age / extraction field name parameters missing");
        }

        if (maximumAge <= 0 || getOcrExtractionFieldName() == null) {
            throw new CustomValidationException("Maximum age / extraction field name parameters missing");
        }

        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);

        ValidationData validationData = new ValidationData();
        validationData = validateInput(fieldData);
        List<OcrFieldValue> parsableOcrFieldValueaList = new ArrayList<>();

        if (validationData.getValidationStatus()) {

            for (OcrFieldValue ocrFieldValue : fieldData.getValue()) {

                if (isParsableDateFormat(ocrFieldValue, ocrResponse)) {

                    validationData = validateAgeLimit(ocrFieldValue, ocrResponse);
                    if (validationData.getValidationStatus()) {
                        validationData.setRemarks(getSuccessRemarksMessage());
                        validationData.setId("Age limit verification");
                        validationData.setCriticalValidation(isCriticalValidation());
                        return validationData;
                    }
                }
            }

        }
        validationData.setValidationStatus(false);
        validationData.setRemarks(getFailedRemarksMessage());
        validationData.setId("Age limit verification");
        validationData.setCriticalValidation(isCriticalValidation());
        return validationData;
    }

    private boolean isParsableDateFormat(OcrFieldValue ocrFieldValue, OcrResponse ocrResponse) {

        String templateCategory = getTemplateCategory(ocrFieldValue.getId(), ocrResponse);
        try {
            Date date = dateDecoder.decodeDate(ocrFieldValue.getValue(), templateCategory);
            return true;
        } catch (DateDecoderException dEx) {
            LOGGER.warn("Error occurred while parsing D.O.B date field on ocr response {} {}"
                    , ocrResponse, dEx);
            return false;
        }
    }

    private ValidationData validateAgeLimit(OcrFieldValue ocrFieldValue, OcrResponse ocrResponse) {
        ValidationData validationData = new ValidationData();
        LocalDate today = new LocalDate();

        String templateCategory = getTemplateCategory(ocrFieldValue.getId(), ocrResponse);

        try {
            Date date = dateDecoder.decodeDate(ocrFieldValue.getValue(), templateCategory);
            LocalDate birthday = new LocalDate(date);
            Years age = Years.yearsBetween(birthday, today);

            if (age.getYears() > minimumAge && age.getYears() < maximumAge) {
                validationData.setValidationStatus(true);
                validationData.setValue(String.valueOf(age.getYears()));
            } else {
                validationData.setRemarks(getFailedRemarksMessage());
                validationData.setValue(String.valueOf(age.getYears()));
                validationData.setValidationStatus(false);
            }
        } catch (DateDecoderException dEx) {
            LOGGER.warn("Error occurred while parsing D.O.B date field on ocr response {} {}"
                    , ocrResponse, dEx);
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
