package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.Address.AddressCompare;
import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MudithaJ on 12/27/2016.
 *
 */
@Component
public class AddressValidation extends ValidationHelper implements CustomValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressValidation.class);

    @Autowired
    private AddressCompare addressComparator;



    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        if (getOcrExtractionFieldName() == null || getOcrExtractionFieldName().isEmpty()) {
            throw new CustomValidationException("Address field name should not be null");
        }
        OcrFieldData fieldData = getFieldDataById(getOcrExtractionFieldName(), ocrResponse);
        validationData = validateInput(fieldData);
        if (validationData.getValidationStatus()){
            validationData = validateAddress(fieldData.getValue());
        }
        if (validationData.getValidationStatus()){
            validationData.setRemarks(getSuccessRemarksMessage());
        }else {
            validationData.setRemarks(getFailedRemarksMessage());
            LOGGER.warn("Address verification failed. Full result set obtained from ocr response is {}", fieldData);
        }
        validationData.setId("Address Verification");
        return validationData;
    }

    private ValidationData validateAddress(List<OcrFieldValue> values) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        if (values.size() >= 1) {
            String baseAddress = values.iterator().next().getValue();
            validationData = compareBaseAddressWithOthers(baseAddress, values);
        } else {
            validationData.setValidationStatus(false);
            validationData.setRemarks("Not Enough date to complete the validation.");
        }
        return validationData;
    }

    private ValidationData compareBaseAddressWithOthers(String baseAddress, List<OcrFieldValue> values){
        ValidationData validationData = new ValidationData();
        for (OcrFieldValue fieldValue : values){
            try {
                if (!addressComparator.compare(baseAddress, fieldValue.getValue()).isResult()){
                    validationData.setValidationStatus(false);
                    validationData.setRemarks(getFailedRemarksMessage());
                    break;
                }else {
                    validationData.setValidationStatus(true);
                    validationData.setRemarks(getSuccessRemarksMessage());
                }
            } catch (AddressValidatingException e) {
                validationData.setValidationStatus(false);
                validationData.setValue(baseAddress);
                validationData.setRemarks("Address type not supported. Please contact support");
                LOGGER.warn("Error occurred while validating given address, address 1 {} address 2 ",
                        baseAddress, fieldValue.getValue(), e);
                break;
            }
        }
        return validationData;
    }
}
