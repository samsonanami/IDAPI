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

import java.util.Date;

/**
 * Created by MudithaJ on 1/2/2017.
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

        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            try {
                validationData = validateAgeLimit(fieldData, ocrResponse);
            } catch (DateDecoderException e) {
                LOGGER.warn("Error occurred while performing an age limit verification on ocr response {} {}"
                        , ocrResponse, e);
                validationData.setValue(null);
                validationData.setRemarks("Error occurred while performing age limit verification. This is most likely " +
                        "due to an unsupported date format. Supported date formats are," +
                        "DD MM/MM YY or DD.MM.YYYY");
                validationData.setOcrConfidence(null);
                validationData.setValidationStatus(false);
            }
        }
        if(validationData.getValidationStatus()){
            validationData.setRemarks(getSuccessRemarksMessage());
        }else {
            validationData.setRemarks(getFailedRemarksMessage());
        }
        validationData.setId("Age limit verification");
        validationData.setCriticalValidation(isCriticalValidation());
        return validationData;
    }

    private ValidationData validateAgeLimit(OcrFieldData ocrFieldData, OcrResponse ocrResponse) throws DateDecoderException {
        ValidationData validationData = new ValidationData();
        LocalDate today = new LocalDate();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()) {
            String templateCategory = getTemplateCategory(fieldValue.getId(), ocrResponse);
            Date date = dateDecoder.decodeDate(fieldValue.getValue(), templateCategory);
            LocalDate birthday = new LocalDate(date);
            Years age = Years.yearsBetween(birthday, today);

            if (age.getYears() > minimumAge && age.getYears() < maximumAge) {
                validationData.setValidationStatus(true);
                validationData.setValue(String.valueOf(age.getYears()));
            } else {
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
