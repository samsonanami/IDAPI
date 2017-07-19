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
        List<Date> issueDateList = this.getDecodedDateList(getFieldDataById(getOcrExtractionFieldName(), ocrResponse), ocrResponse);
        List<Date> dateOfBirthList = this.getDecodedDateList(getFieldDataById(dateOfBirthOcrExtractionField, ocrResponse), ocrResponse);

        validationData = this.validateIssuedDateAgeLimit(issueDateList, dateOfBirthList);

        validationData.setId("Date of Birth at Document Issue Date Validation");
        validationData.setCriticalValidation(isCriticalValidation());
        return validationData;
    }

    private List<Date> getDecodedDateList(OcrFieldData dateOcrFieldData, OcrResponse ocrResponse){
        List<Date> issueDateList = new ArrayList<>();
        for (OcrFieldValue ocrFieldValue : dateOcrFieldData.getValue()){
            String templateCategory = getTemplateCategory(ocrFieldValue.getId(), ocrResponse);
            Date date = null;
            try {
                date = dateDecoder.decodeDate(ocrFieldValue.getValue(), templateCategory);
                issueDateList.add(date);
            } catch (DateDecoderException e) {
                LOGGER.error("Error while decoding the date {} ", ocrFieldValue.getValue(), e);
            }
        }

        return issueDateList;
    }

    private ValidationData validateIssuedDateAgeLimit(List<Date> issueDateList, List<Date> dateOfBirthList){
        ValidationData validationData = new ValidationData();

        for (Date issuedDate: issueDateList){
            for (Date dateOfBirth: dateOfBirthList){
                int age = getAgeforIssueDate(issuedDate, dateOfBirth);
                if (age > minimumAge && age < maximumAge) {
                    validationData.setValidationStatus(true);
                    validationData.setValue(String.valueOf(age));
                    validationData.setRemarks(getSuccessRemarksMessage());
                    break;
                } else {
                    validationData.setRemarks(getFailedRemarksMessage());
                    validationData.setValue(String.valueOf(age));
                    validationData.setValidationStatus(false);
                }
            }
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
