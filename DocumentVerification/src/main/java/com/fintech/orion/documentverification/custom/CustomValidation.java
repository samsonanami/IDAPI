package com.fintech.orion.documentverification.custom;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CriticalValidationFailedException;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;

import java.util.Map;

/**
 * Created by sasitha on 12/26/16.
 *
 */
public interface CustomValidation {

    ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException;

    boolean isCriticalValidation();
}
