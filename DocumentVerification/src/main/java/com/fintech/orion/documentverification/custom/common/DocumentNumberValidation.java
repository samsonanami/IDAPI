package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.springframework.stereotype.Component;

/**
 * Created by MudithaJ on 12/27/2016.
 */
@Component
public class DocumentNumberValidation  extends ValidationHelper implements CustomValidation {

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        validationData.setId("Document Number Verification");
        OcrFieldData fieldData=getFieldDataById(getOcrExtractionFieldName(),ocrResponse);
        OcrFieldValue fieldValue = getFieldValueById(resourceName.getName()+"##" + getOcrExtractionFieldName(), fieldData);
        if (fieldData != null && fieldData.getValue() != null && !fieldData.getValue().isEmpty()) {
            validationData.setValue(fieldValue.getValue());
            validationData.setOcrConfidence(fieldValue.getConfidence());
            validationData.setRemarks(getSuccessRemarksMessage());
            validationData.setValidationStatus(true);
        }
        else {
            validationData.setValue("Unknown");
            validationData.setRemarks("Could not verify document version. No document number found in the given " +
                    "document "+ resourceName.getName());
            validationData.setValidationStatus(false);
        }
        return validationData;
    }
}
