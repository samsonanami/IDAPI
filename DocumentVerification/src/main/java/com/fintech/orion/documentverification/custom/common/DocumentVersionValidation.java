package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.springframework.stereotype.Component;

/**
 * Created by sasitha on 12/26/16.
 */
@Component
public class DocumentVersionValidation extends ValidationHelper implements CustomValidation {


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();

        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        OcrFieldValue fieldValue = getFieldValueById(resourceName.getName() + "##" + getOcrExtractionFieldName(), fieldData);
        if (fieldValue != null && fieldValue.getValue() != null && !fieldValue.getValue().isEmpty()) {
            validationData.setValue(fieldValue.getValue());
            validationData.setOcrConfidence(fieldValue.getConfidence());
            validationData.setRemarks(getSuccessRemarksMessage());
            validationData.setValidationStatus(true);
        } else {
            validationData.setValue("Unknown");
            validationData.setRemarks("Could not verify document version No document number found in the given " +
                    "document " + resourceName.getName());
            validationData.setValidationStatus(false);
        }
        validationData.setId("Document Version Verification");
        return validationData;
    }
}
