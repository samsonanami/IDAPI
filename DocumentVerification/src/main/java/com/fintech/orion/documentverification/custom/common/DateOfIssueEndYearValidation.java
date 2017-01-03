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
public class DateOfIssueEndYearValidation  extends ValidationHelper implements CustomValidation {


    private int validYearCount;



    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (validYearCount <= 0 || getOcrExtractionFieldName() == null){
            throw new CustomValidationException("Valid year count field name parameters missing");
        }


        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()){
            try {
                validationData = validateAgeLimit(fieldData);
            } catch (DateComparatorException e) {
                throw new CustomValidationException("Error Occurred while performing issued date end year verification ", e);
            }
        }
        if (!validationData.getValidationStatus()){
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setId("Issued date end year verification");
        return validationData;
    }

    private ValidationData validateAgeLimit(OcrFieldData ocrFieldData) throws DateComparatorException {
        ValidationData validationData = new ValidationData();
        DateDecoder dateDecoder = new DateDecoder();
        LocalDate today = new LocalDate();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()){
            Date date = dateDecoder.decodeDate(fieldValue.getValue());
            LocalDate issuedDate = new LocalDate(date);
           Years yearsFromIssued = Years.yearsBetween(issuedDate,today);

            if (yearsFromIssued.getYears() <= validYearCount){
                validationData.setValidationStatus(true);
                validationData.setValue(String.valueOf(issuedDate.getYear()));
            }else {
                validationData.setRemarks(getFailedRemarksMessage());
                validationData.setValue(String.valueOf(issuedDate.getYear()));
                validationData.setValidationStatus(false);
                break;
            }

        }
        return validationData;
    }

    public void setValidYearCount(int validYearCount) {
        this.validYearCount = validYearCount;
    }
}
