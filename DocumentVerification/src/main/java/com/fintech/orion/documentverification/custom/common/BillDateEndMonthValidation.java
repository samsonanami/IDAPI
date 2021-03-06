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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MudithaJ on 1/2/2017.
 */
public class BillDateEndMonthValidation extends ValidationHelper implements CustomValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillDateEndMonthValidation.class);
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
        if (fieldData.getValue().isEmpty()) {
            throw new CustomValidationException("Skipping verification because no utility bill issue date is extracted " +
                    "from any of the given documents");
        }
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            validationData = validateBillDateEndMonth(fieldData, ocrResponse);
        }
        validationData.setCriticalValidation(isCriticalValidation());
        validationData.setId("Bill date end month verification");
        return validationData;
    }

    private ValidationData validateBillDateEndMonth(OcrFieldData ocrFieldData, OcrResponse ocrResponse) {
        ValidationData validationData = new ValidationData();
        LocalDate today = new LocalDate();
        List<Date> processedDateList = new ArrayList<>();

        for (OcrFieldValue ocrFieldValue : ocrFieldData.getValue()) {
            Date date = null;
            try {
                String templateCategory = getTemplateCategory(ocrFieldValue.getId(), ocrResponse);
                date = dateDecoder.decodeDate(ocrFieldValue.getValue(), templateCategory);
                processedDateList.add(date);
            } catch (DateDecoderException e) {
                LOGGER.error("Error occurred while validating utility bill date foll ocr response is {} "
                        , ocrResponse, e);
                validationData.setRemarks(getFailedRemarksMessage());
                validationData.setValidationStatus(false);
            }
        }

        for (Date date : processedDateList) {

            LocalDate issuedDate = new LocalDate(date);
            Months monthsFromIssued = Months.monthsBetween(issuedDate, today);

            if (monthsFromIssued.getMonths() <= validMonthCount) {
                validationData.setValidationStatus(true);
                validationData.setValue(String.valueOf(issuedDate.getMonthOfYear()));
                validationData.setRemarks(getSuccessRemarksMessage());
                return validationData;
            } else {
                validationData.setRemarks("Utility bill is " + monthsFromIssued.getMonths() + " months older " +
                        getFailedRemarksMessage());
                validationData.setValue(String.valueOf(issuedDate));
                validationData.setValidationStatus(false);
            }
        }
        return validationData;
    }

    public void setValidMonthCount(int validMonthCount) {
        this.validMonthCount = validMonthCount;
    }
}
