package com.fintech.orion.documentverification.custom.datavalidation;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;

/**
 * Created by sasitha on 3/21/17.
 */
public class ExpireDateVizMrzValidation extends AbstractDataValidation implements CustomValidation {
    @Override
    public Object validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        return super.ocrExtractionFieldVizMrzDataValidation(resourceName, ocrResponse);
    }
}
