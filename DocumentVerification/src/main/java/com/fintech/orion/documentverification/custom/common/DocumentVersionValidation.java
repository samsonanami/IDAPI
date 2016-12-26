package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CriticalValidationFailedException;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.springframework.stereotype.Component;

/**
 * Created by sasitha on 12/26/16.
 *
 */
@Component
public class DocumentVersionValidation extends ValidationHelper implements CustomValidation {


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        validationData.setId("Document Version");
        OcrFieldData fieldData = getFieldDataById("document_version", ocrResponse);
        OcrFieldValue fieldValue = getFieldValueById(resourceName.getName()+"##document_version", fieldData);
        if (fieldValue != null && fieldValue.getValue() != null && !fieldValue.getValue().isEmpty()){
            validationData.setValue(fieldValue.getValue());
            validationData.setOcrConfidence(fieldValue.getConfidence());
        }else{
            validationData.setValue("Unknown");
            validationData.setRemarks("Could not verify document version");
            validationData.setValidationStatus(false);
        }
        return validationData;
    }
}
