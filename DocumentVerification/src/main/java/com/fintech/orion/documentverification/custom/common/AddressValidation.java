package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.address.AddressCompare;
import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by MudithaJ on 12/27/2016.
 */
@Component
public class AddressValidation extends ValidationHelper implements CustomValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressValidation.class);

    @Autowired
    private AddressCompare addressComparator;

    private AddressBuilder addressBuilder = new AddressBuilder();

    private String ocrFieldBase;
    private int addressLineCount;

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        if (ocrFieldBase == null || ocrFieldBase.isEmpty()) {
            throw new CustomValidationException("address field name should not be null");
        }

        String baseAddress = addressBuilder.buildSingleLineAddressFromOcrResponse(ocrResponse, resourceName.getName(),
                ocrFieldBase, addressLineCount);

        if (!baseAddress.isEmpty()) {
            List<String> resourceList = getResourceListFromOcrResponse(ocrResponse);
            compareAddressWithBaseAddress(ocrResponse, validationData, baseAddress, resourceList);
        } else {
            validationData.setValidationStatus(false);
            validationData.setValue("");
            validationData.setRemarks("Could not perform address validation. Resource " + resourceName.getName() + " " +
                    "dose not have any address field to do the address verification");
        }
        validationData.setId("address Verification");
        return validationData;
    }

    private void compareAddressWithBaseAddress(OcrResponse ocrResponse, ValidationData validationData,
                                               String baseAddress, List<String> resourceList) {
        for (String resource : resourceList) {
            String address = addressBuilder.buildSingleLineAddressFromOcrResponse(ocrResponse, resource,
                    ocrFieldBase, addressLineCount);
            try {
                if (!addressComparator.compare(baseAddress, address).isResult()) {
                    validationData.setValidationStatus(false);
                    validationData.setRemarks(getFailedRemarksMessage());
                    validationData.setValue(address);
                    break;
                } else {
                    validationData.setValidationStatus(true);
                    validationData.setRemarks(getSuccessRemarksMessage());
                    validationData.setValue(baseAddress);
                }
            } catch (AddressValidatingException e) {
                validationData.setValidationStatus(false);
                validationData.setValue(address);
                validationData.setRemarks("address type not supported. Please contact support");
                LOGGER.warn("Error occurred while validating given address, address 1 {} address 2 ",
                        baseAddress, address, e);
                break;
            }
        }
    }

    private List<String> getResourceListFromOcrResponse(OcrResponse ocrResponse) {
        List<String> resourceList = new ArrayList<>();
        for (OcrFieldData fieldData : ocrResponse.getData()) {
            for (OcrFieldValue fieldValue : fieldData.getValue()) {
                String[] splitArray = fieldValue.getId().split("##");
                if (splitArray.length > 0) {
                    resourceList.add(splitArray[0]);
                }
            }
        }
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>(resourceList);
        return new ArrayList<>(linkedHashSet);
    }

    public void setOcrFieldBase(String ocrFieldBase) {
        this.ocrFieldBase = ocrFieldBase;
    }

    public void setAddressLineCount(int addressLineCount) {
        this.addressLineCount = addressLineCount;
    }
}
