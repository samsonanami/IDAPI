package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
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
        validationData.setId("document_number");
        OcrFieldData fieldData=getFieldDataById("document_number",ocrResponse);
        if (fieldData != null && fieldData.getValue() != null && !fieldData.getValue().isEmpty()) {
            validationData = validateData(fieldData.getValue());
        }
        else {
            validationData.setValue("Unknown");
            validationData.setRemarks("Could not verify document version");
            validationData.setValidationStatus(false);
        }
        return validationData;
    }
}
