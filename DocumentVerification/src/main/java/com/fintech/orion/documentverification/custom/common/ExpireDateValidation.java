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
public class ExpireDateValidation extends ValidationHelper implements CustomValidation {


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        validationData.setId("date_of_expiry");
        OcrFieldData fieldData=getFieldDataById("date_of_expiry",ocrResponse);
        OcrFieldValue fieldValue = getFieldValueById(resourceName.getName()+"##date_of_expiry", fieldData);
        if (fieldData != null && fieldData.getValue() != null && !fieldData.getValue().isEmpty()) {
            validationData.setValue(fieldValue.getValue());
            validationData.setOcrConfidence(fieldValue.getConfidence());
        }else
        {
            validationData.setValue("Unknown");
            validationData.setRemarks("Could not verify expire date");
            validationData.setValidationStatus(false);
        }
        return validationData;
    }
}
