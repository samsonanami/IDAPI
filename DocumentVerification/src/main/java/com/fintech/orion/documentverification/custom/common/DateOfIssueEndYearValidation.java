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
public class DateOfIssueEndYearValidation extends ValidationHelper implements CustomValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateOfIssueEndYearValidation.class);
    private int validYearCount;

    @Autowired
    private DateDecoder dateDecoder;


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (validYearCount <= 0 || getOcrExtractionFieldName() == null) {
            throw new CustomValidationException("Valid year count /field name parameters missing");
        }


        ValidationData validationData = new ValidationData();
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        if(fieldData.getValue().isEmpty()){
            throw new CustomValidationException("Skipping validation since no document issue date was found");
        }
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()) {
            try {
                validationData.setValidationStatus(false);
                validationData.setRemarks(getFailedRemarksMessage());
                validationData = validateDateofIusseEndYear(fieldData, ocrResponse);
            } catch (DateDecoderException e) {
                LOGGER.warn("Error occurred while performing an date of issue year validation for ocr response {} {}",
                        ocrResponse, e);
                validationData.setValue(null);
                validationData.setOcrConfidence(null);
                validationData.setValidationStatus(false);
                validationData.setRemarks("Error occurred while performing the issue date year range validation. " +
                        "This is most likely " +
                        "due to an unsupported date format. Supported date formats are," +
                        "DD MM/MM YY or DD.MM.YYYY");
            }
        }
        if (validationData.getValidationStatus()) {
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setCriticalValidation(isCriticalValidation());
        validationData.setId("Issued date end year verification");
        return validationData;
    }

    private ValidationData validateDateofIusseEndYear(OcrFieldData ocrFieldData, OcrResponse ocrResponse) throws DateDecoderException {
        ValidationData validationData = new ValidationData();
        LocalDate today = new LocalDate();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()) {
            Date date = dateDecoder.decodeDate(fieldValue.getValue(), getTemplateCategory(fieldValue.getId(), ocrResponse));
            LocalDate issuedDate = new LocalDate(date);
            Years yearsFromIssued = Years.yearsBetween(issuedDate, today);

            if (yearsFromIssued.getYears() <= validYearCount) {
                validationData.setValidationStatus(true);
                validationData.setValue(String.valueOf(issuedDate.getYear()));
            } else {
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
