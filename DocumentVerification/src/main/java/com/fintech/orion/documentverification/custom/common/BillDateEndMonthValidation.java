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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by MudithaJ on 1/2/2017.
 */
public class BillDateEndMonthValidation extends ValidationHelper implements CustomValidation {


    private int validMonthCount;

    @Autowired
    private DateDecoder dateDecoder;


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (validMonthCount <= 0 || getOcrExtractionFieldName() == null) {
            throw new CustomValidationException("Valid month count field name parameters missing");
        }


        ValidationData validationData = new ValidationData();
        validationData.setValidationStatus(false);
        validationData.setRemarks(getFailedRemarksMessage());
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            try {
                validationData = validateBillDateEndMonth(fieldData, ocrResponse);
            } catch (DateDecoderException e) {
                throw new CustomValidationException("Error Occurred while performing bill date end month verification ", e);
            }
        }
        if (validationData.getValidationStatus()) {
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setId("Bill date end month verification");
        return validationData;
    }

    private ValidationData validateBillDateEndMonth(OcrFieldData ocrFieldData, OcrResponse ocrResponse) throws DateDecoderException {
        ValidationData validationData = new ValidationData();
        LocalDate today = new LocalDate();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()) {
            Date date = dateDecoder.decodeDate(fieldValue.getValue(), getTemplateCategory(fieldValue.getId(), ocrResponse));
            LocalDate issuedDate = new LocalDate(date);
            Months monthsFromIssued = Months.monthsBetween(issuedDate, today);

            if (monthsFromIssued.getMonths() <= validMonthCount) {
                validationData.setValidationStatus(true);
                validationData.setValue(String.valueOf(issuedDate.getMonthOfYear()));
            } else {
                validationData.setRemarks("Utility bill is "+ monthsFromIssued.getMonths() + " months older " +
                getFailedRemarksMessage());
                validationData.setValue(String.valueOf(issuedDate));
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
