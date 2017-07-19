package com.fintech.orion.documentverification.factory;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sasitha on 2/19/17.
 */
public abstract class AbstractCustomValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCustomValidation.class);

    List<Object> executeCustomValidations(List<CustomValidation> customValidationList, ResourceName resourceName,
                                          OcrResponse ocrResponse){
        List<Object> idDocFullValidationList = new ArrayList<>();
        ValidationData errorDataSet = new ValidationData();
        errorDataSet.setRemarks("");
        errorDataSet.setId("critical_error_set");
        LOGGER.debug("Starting custom validation with resource name {} and ocr response {}", resourceName, ocrResponse);
        for (CustomValidation validation : customValidationList) {
            try {
                ValidationData validationData = (ValidationData) validation.validate(resourceName, ocrResponse);
                idDocFullValidationList.add(validationData);
                if (!validationData.getValidationStatus() && validation.isCriticalValidation()) {
                    errorDataSet.setRemarks(errorDataSet.getRemarks() + validationData.getRemarks());
                }
            } catch (CustomValidationException ex) {
                LOGGER.error("Unable to execute custom validation in Identification document full verification" +
                        " the validation is {}", validation, ex);
            }
        }

        idDocFullValidationList.add(errorDataSet);

        return idDocFullValidationList;
    }
}
