package com.fintech.orion.documentverification.custom.datavalidation;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.DataValidation;

/**
 * Created by sasitha on 2/7/17.
 *
 */
public class SurnameVizMrzValidation extends AbstractDataValidation implements CustomValidation {

    @Override
    public DataValidation validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        return super.ocrExtractionFieldVizMrzDataValidation(resourceName, ocrResponse);
    }

}
