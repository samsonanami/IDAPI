package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.address.AddressCompare;
import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The <code>AddressValidation</code> is the class which performs the address validation.
 * <p/>
 * Address validation will be done as follows.
 * <p/>
 * Once the class is being called with the
 * {@link com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse OcrResponse} returned
 * by the <code>ORACLE API</code>, one address value will be taken as the base address.
 * rest of the addresses will be compare against the base address and if they are not matching
 * verification will be marked as failed.
 * <p/>
 * However if no address is detected from a given resource it will not be used for the comparison.
 * If the only the base address is present then the no verification will be done but the value
 * of the base address will be set in the output.
 */
@Component
public class AddressValidation extends ValidationHelper implements CustomValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressValidation.class);

    @Autowired
    private AddressCompare addressComparator;

    @Autowired
    private AddressBuilder addressBuilder;

    private String ocrFieldBase;
    private int addressLineCount;

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        ValidationData validationData = new ValidationData();
        if (ocrFieldBase == null || ocrFieldBase.isEmpty()) {
            throw new CustomValidationException("address field name should not be null");
        }

        String firstResourceWithAnAddress =  getFirstResourceNameWhichHasAnAddress(ocrResponse);
        String baseAddress = addressBuilder.buildSingleLineAddressFromOcrResponse(ocrResponse, firstResourceWithAnAddress,
                ocrFieldBase, addressLineCount);

        if (!baseAddress.isEmpty()) {
            List<String> resourceList = getResourceListFromOcrResponse(ocrResponse);
            compareAddressWithBaseAddress(ocrResponse, validationData, baseAddress, resourceList);
        } else {
            validationData.setValidationStatus(false);
            validationData.setValue("");
            validationData.setRemarks("Could not perform address validation. Resource " + firstResourceWithAnAddress + " " +
                    "dose not have any address field to do the address verification");
        }
        validationData.setId("address Verification");
        return validationData;
    }

    private String getFirstResourceNameWhichHasAnAddress(OcrResponse ocrResponse){
        String resourceName = "";
        String ocrFieldValueId = "";
        String ocrFieldValueIdResourceNameFieldNameSeparator = "##";
        int firstElementInFieldValueArray =0;
        int firstAddressLineSuffix = 1;
        int resourceNamePositionInOcrFieldValueId = 0;

        OcrFieldData ocrFieldValueSetForAddressLine1 = getFieldDataById(ocrFieldBase+firstAddressLineSuffix, ocrResponse);
        if(!ocrFieldValueSetForAddressLine1.getValue().isEmpty()){
            ocrFieldValueId = ocrFieldValueSetForAddressLine1.getValue().get(firstElementInFieldValueArray).getId();
            resourceName = ocrFieldValueId.split(ocrFieldValueIdResourceNameFieldNameSeparator)[resourceNamePositionInOcrFieldValueId];
        }
        return resourceName;
    }

    private void compareAddressWithBaseAddress(OcrResponse ocrResponse, ValidationData validationData,
                                               String baseAddress, List<String> resourceList) {
        for (String resource : resourceList) {
            String address = addressBuilder.buildSingleLineAddressFromOcrResponse(ocrResponse, resource,
                    ocrFieldBase, addressLineCount);
            if (!compareSingleAddressWithBaseAddress(validationData, baseAddress, address)){
                break;
            }
        }
    }

    private boolean compareSingleAddressWithBaseAddress(ValidationData validationData, String baseAddress, String address) {
        boolean isAddressMatch = false;
        try {
            if (address.isEmpty() ) {
                validationData.setValidationStatus(true);
                validationData.setRemarks(getSuccessRemarksMessage());
                validationData.setValue(baseAddress);
                isAddressMatch = true;
            } else if(!address.isEmpty() && !addressComparator.compare(baseAddress, address).isResult()) {
                validationData.setValidationStatus(false);
                validationData.setRemarks(getFailedRemarksMessage());
                validationData.setValue(baseAddress);
                isAddressMatch = false;
            }
        } catch (AddressValidatingException e) {
            validationData.setValidationStatus(false);
            validationData.setValue(baseAddress);
            validationData.setRemarks("address type not supported. Please contact support");
            LOGGER.warn("Error occurred while validating given address, address 1 {} address 2 ",
                    baseAddress, address, e);
            isAddressMatch = false;
        }
        return isAddressMatch;
    }


    public void setOcrFieldBase(String ocrFieldBase) {
        this.ocrFieldBase = ocrFieldBase;
    }

    public void setAddressLineCount(int addressLineCount) {
        this.addressLineCount = addressLineCount;
    }

    public void setAddressBuilder(AddressBuilder addressBuilder) {
        this.addressBuilder = addressBuilder;
    }
}
