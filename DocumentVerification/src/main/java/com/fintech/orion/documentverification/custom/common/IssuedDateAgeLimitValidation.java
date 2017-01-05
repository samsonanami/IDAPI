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
import java.util.Date;

/**
 * Created by MudithaJ on 1/2/2017.
 */
public class IssuedDateAgeLimitValidation  extends ValidationHelper implements CustomValidation {

    private int minimumAge;
    private int maximumAge;
    private String dateofBirthString;



    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (minimumAge <= 0 || getOcrExtractionFieldName() == null){
            throw new CustomValidationException("Minimum age / extraction field name parameters missing");
        }

        if (maximumAge <= 0 || getOcrExtractionFieldName() == null){
            throw new CustomValidationException("Maximum age / extraction field name parameters missing");
        }

        ValidationData validationData = new ValidationData();
        OcrFieldData fieldDataIssuedDate = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        OcrFieldValue valueIssuedDate = getFieldValueById(resourceName.getName()+"##"+getOcrExtractionFieldName(),fieldDataIssuedDate);
        OcrFieldData fieldDataDateOfBirth = getFieldDataById(dateofBirthString, ocrResponse);
        OcrFieldValue valueDateOfBirth = getFieldValueById(resourceName.getName()+"##"+dateofBirthString,fieldDataDateOfBirth);

        validationData = validateInput(fieldDataIssuedDate);
        if (validationData.getValidationStatus()){
            try {
             validationData = validateIssuedDateAgeLimit(valueIssuedDate,valueDateOfBirth);
            } catch (DateComparatorException e) {
                throw new CustomValidationException("Error Occurred while performing issued date age limit verification ", e);
            }
        }
        if (!validationData.getValidationStatus()){
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setId("Issued date age limit verification");
        return validationData;
    }

    private ValidationData validateIssuedDateAgeLimit(OcrFieldValue ocrFieldValueIssuedDate,OcrFieldValue ocrFieldValueDateOfBirth) throws DateComparatorException {
        ValidationData validationData = new ValidationData();
        DateDecoder dateDecoder = new DateDecoder();
        Date issuedDate = dateDecoder.decodeDate(ocrFieldValueIssuedDate.getValue());
        Date dateOfBirth = dateDecoder.decodeDate(ocrFieldValueDateOfBirth.getValue());

         int age=getAgeforIssueDate(issuedDate,dateOfBirth);
        if (age>minimumAge && age<maximumAge ){
            validationData.setValidationStatus(true);
            validationData.setValue(String.valueOf(age));
        }else {
            validationData.setRemarks(getFailedRemarksMessage());
            validationData.setValue(String.valueOf(age));
            validationData.setValidationStatus(false);
        }
        return validationData;
    }
     private int getAgeforIssueDate(Date dateIssueDate,Date dateDateOfBirth)
     {
         int age;
         LocalDate issuedDate = new LocalDate(dateIssueDate);
         LocalDate dateOfBirth = new LocalDate(dateDateOfBirth);
         age = Years.yearsBetween(dateOfBirth,issuedDate).getYears();
         return age;
     }
    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    public void setDateofBirthString(String dateofBirthString) {
        this.dateofBirthString = dateofBirthString;
    }

}
