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
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by MudithaJ on 12/27/2016.
 */
@Component
public class ExpireDateValidation extends ValidationHelper implements CustomValidation {

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();

        OcrFieldData fieldData=getFieldDataById(getOcrExtractionFieldName(),ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()){
            try {
                validationData = checkDocumentExpirationDate(fieldData);
            } catch (DateComparatorException e) {
                throw new CustomValidationException("Error occurred while performing document expiry date verification ");
            }
        }
        if(validationData.getValidationStatus()){
            validationData.setRemarks(getSuccessRemarksMessage());
        }
        validationData.setId("Document Expiry Date Verification");
        return validationData;
    }

    private ValidationData checkDocumentExpirationDate(OcrFieldData ocrFieldData) throws DateComparatorException {
        ValidationData validationData = new ValidationData();
        DateDecoder dateDecoder = new DateDecoder();
        for (OcrFieldValue fieldValue : ocrFieldData.getValue()){
            Date date = dateDecoder.decodeDate(fieldValue.getValue());
            if (date.before(new Date())){
                validationData.setRemarks(getDocumentNameFromOcrFieldValueId(fieldValue.getId()) + getFailedRemarksMessage());
                validationData.setValidationStatus(false);
                validationData.setValue(date.toString());
                break;
            }else {
                validationData.setValidationStatus(true);
            }
        }
        return validationData;
    }

}
