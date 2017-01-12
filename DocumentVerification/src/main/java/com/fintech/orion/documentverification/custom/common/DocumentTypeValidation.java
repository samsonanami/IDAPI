package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;

/**
 * Created by sasitha on 12/26/16.
 *
 */
public class DocumentTypeValidation extends ValidationHelper implements CustomValidation{


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        validationData.setId("Document Type");
        validationData.setValue(resourceName.getName());
        return validationData;
    }
}
