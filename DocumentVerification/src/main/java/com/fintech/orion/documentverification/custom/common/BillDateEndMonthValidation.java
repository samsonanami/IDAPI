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
import org.joda.time.Months;

import java.util.Date;

/**
 * Created by MudithaJ on 1/2/2017.
 */
public class BillDateEndMonthValidation extends ValidationHelper implements CustomValidation {


    private int validMonthCount;


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (validMonthCount <= 0 || getOcrExtractionFieldName() == null) {
            throw new CustomValidationException("Valid month count field name parameters missing");
        }


        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            try {
                validationData = validateBillDateEndMonth(fieldData);
            } catch (DateDecoderException e) {
                throw new CustomValidationException("Error Occurred while performing bill date end month verification ", e);
            }
        }
        if (!validationData.getValidationStatus()) {
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setId("Bill date end month verification");
        return validationData;
    }

    private ValidationData validateBillDateEndMonth(OcrFieldData ocrFieldData) throws DateDecoderException {
        ValidationData validationData = new ValidationData();
        DateDecoder dateDecoder = new DateDecoder();
        LocalDate today = new LocalDate();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()) {
            Date date = dateDecoder.decodeDate(fieldValue.getValue());
            LocalDate issuedDate = new LocalDate(date);
            Months monthsFromIssued = Months.monthsBetween(issuedDate, today);

            if (monthsFromIssued.getMonths() <= validMonthCount) {
                validationData.setValidationStatus(true);
                validationData.setValue(String.valueOf(issuedDate.getMonthOfYear()));
            } else {
                validationData.setRemarks(getFailedRemarksMessage());
                validationData.setValue(String.valueOf(issuedDate.getMonthOfYear()));
                validationData.setValidationStatus(false);
                break;
            }

        }
        return validationData;
    }

    public void setValidMonthCount(int validMonthCount) {
        this.validMonthCount = validMonthCount;
    }
}
