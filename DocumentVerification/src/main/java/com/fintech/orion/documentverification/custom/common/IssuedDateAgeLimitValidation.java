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
public class IssuedDateAgeLimitValidation extends ValidationHelper implements CustomValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssuedDateAgeLimitValidation.class);
    private int minimumAge;
    private int maximumAge;
    private String dateOfBirthOcrExtractionField;

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
        OcrFieldData fieldDataIssuedDate = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        OcrFieldValue valueIssuedDate = getFieldValueById(resourceName.getName() + "##" + getOcrExtractionFieldName(), fieldDataIssuedDate);
        OcrFieldData fieldDataDateOfBirth = getFieldDataById(dateOfBirthOcrExtractionField, ocrResponse);
        OcrFieldValue valueDateOfBirth = getFieldValueById(resourceName.getName() + "##" + dateOfBirthOcrExtractionField, fieldDataDateOfBirth);

        validationData = validateInput(fieldDataIssuedDate);
        if (validationData.getValidationStatus()) {
            try {
                validationData = validateIssuedDateAgeLimit(valueIssuedDate, valueDateOfBirth);
            } catch (DateDecoderException e) {
                LOGGER.warn("Error occurred while performing an date of birth at issue date" +
                        " validation for ocr response {} {}", ocrResponse, e);
                validationData.setValue(null);
                validationData.setOcrConfidence(null);
                validationData.setValidationStatus(false);
                validationData.setRemarks("Error occurred while performing the issue date year range validation. " +
                        "This is most likely " +
                        "due to an unsupported date format. Supported date formats are," +
                        "DD MM/MM YY or DD.MM.YYYY");
            }
        }
        if (!validationData.getValidationStatus()) {
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setId("Date of Birth at Document Issue Date Validation");
        return validationData;
    }

    private ValidationData validateIssuedDateAgeLimit(OcrFieldValue ocrFieldValueIssuedDate, OcrFieldValue ocrFieldValueDateOfBirth) throws DateDecoderException {
        ValidationData validationData = new ValidationData();
        Date issuedDate = dateDecoder.decodeDate(ocrFieldValueIssuedDate.getValue());
        Date dateOfBirth = dateDecoder.decodeDate(ocrFieldValueDateOfBirth.getValue());

        int age = getAgeforIssueDate(issuedDate, dateOfBirth);
        if (age > minimumAge && age < maximumAge) {
            validationData.setValidationStatus(true);
            validationData.setValue(String.valueOf(age));
            validationData.setRemarks(getSuccessRemarksMessage());
        } else {
            validationData.setRemarks(getFailedRemarksMessage());
            validationData.setValue(String.valueOf(age));
            validationData.setValidationStatus(false);
        }
        return validationData;
    }

    private int getAgeforIssueDate(Date dateIssueDate, Date dateDateOfBirth) {
        int age;
        LocalDate issuedDate = new LocalDate(dateIssueDate);
        LocalDate dateOfBirth = new LocalDate(dateDateOfBirth);
        age = Years.yearsBetween(dateOfBirth, issuedDate).getYears();
        return age;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    public void setDateOfBirthOcrExtractionField(String dateOfBirthOcrExtractionField) {
        this.dateOfBirthOcrExtractionField = dateOfBirthOcrExtractionField;
    }

}
