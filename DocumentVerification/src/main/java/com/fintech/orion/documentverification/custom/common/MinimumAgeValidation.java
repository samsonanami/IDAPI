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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sasitha on 12/29/16.
 *
 */

public class MinimumAgeValidation extends ValidationHelper implements CustomValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinimumAgeValidation.class);

    @Autowired
    private DateDecoder dateDecoder;

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
                validationData = validateMinimumAge(fieldData, ocrResponse);
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
        if (validationData.getValidationStatus()) {
            validationData.setRemarks(getSuccessRemarksMessage());
        }else {
            validationData.setRemarks(getFailedRemarksMessage());
        }
        validationData.setId("Minimum Age Verification");
        validationData.setCriticalValidation(isCriticalValidation());
        return validationData;
    }

    private ValidationData validateMinimumAge(OcrFieldData ocrFieldData, OcrResponse ocrResponse) throws DateComparatorException {
        ValidationData validationData = new ValidationData();
        LocalDate today = new LocalDate();
        List<Date> processedDateList = new ArrayList<>();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()) {
            Date date = null;
            try {
                String templateCategory = getTemplateCategory(fieldValue.getId(), ocrResponse);
                date = dateDecoder.decodeDate(fieldValue.getValue(), templateCategory);
                processedDateList.add(date);
            } catch (DateDecoderException e) {
                throw new DateComparatorException("Unable to decode the given date " , e);
            }
        }

        for (Date date : processedDateList){
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
