package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by MudithaJ on 1/4/2017.
 */
public class NameUtilityBillValidation extends ValidationHelper implements CustomValidation {

    private String utilityBillNameOcrExtractionField;
    private String surnameOcrExtractionFieldName;
    private String givenNamesOcrExtractionFieldName;
    private List<String> resourceNameListToCheck;

    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        if (utilityBillNameOcrExtractionField == null) {
            throw new CustomValidationException("Utility bill full name  extraction field name parameters missing");
        }

        if (surnameOcrExtractionFieldName == null || givenNamesOcrExtractionFieldName == null) {
            throw new CustomValidationException("SurName / Given Names extraction field name parameters missing");
        }
        ValidationData validationData = new ValidationData();
        List<String> idVerificationFullNameList = new ArrayList<String>();
        List<String> addressVerificationFullNameList = new ArrayList<String>();

        OcrFieldData fieldDataSurname = getFieldDataById(surnameOcrExtractionFieldName, ocrResponse);
        OcrFieldData fieldDataGivenNames = getFieldDataById(givenNamesOcrExtractionFieldName, ocrResponse);
        OcrFieldData fieldDataUtilityBillFullName = getFieldDataById(utilityBillNameOcrExtractionField, ocrResponse);

        List<String> documentTypeSuffixList = new ArrayList<String>();
        documentTypeSuffixList.add("PP");
        documentTypeSuffixList.add("NPP");

        for (String resourceNameToValidate : resourceNameListToCheck) {
            for (String documentTypeSuffix : documentTypeSuffixList) {
                idVerificationFullNameList.add(getFullName(resourceNameToValidate, fieldDataSurname, fieldDataGivenNames, documentTypeSuffix));
            }
        }

        for (String documentTypeSuffix : documentTypeSuffixList) {
            String fullName = getFieldValueById("utilityBill" + "##" + utilityBillNameOcrExtractionField + "##" + documentTypeSuffix,
                    fieldDataUtilityBillFullName).getValue();
            addressVerificationFullNameList.add(fullName);
        }

        validationData = validateInput(fieldDataUtilityBillFullName);

        if (validationData.getValidationStatus()) {

            validationData = validateBillName(idVerificationFullNameList, addressVerificationFullNameList);

        } else {
            validationData.setRemarks(getFailedRemarksMessage());
        }

        validationData.setId("Utility bill name verification");
        validationData.setCriticalValidation(isCriticalValidation());
        return validationData;
    }

    private ValidationData validateBillName(List<String> stringList, List<String> billFullNameList) {
        ValidationData validationData = new ValidationData();
        stringList.removeAll(Collections.singleton(null));

        for (String billFullName : billFullNameList) {

            for (String fullName : stringList) {
                if (fullName.equalsIgnoreCase(billFullName)) {
                    validationData.setValidationStatus(true);
                    validationData.setValue(billFullName);
                    validationData.setRemarks(getSuccessRemarksMessage());
                    return validationData;
                }
            }
        }
        validationData.setRemarks(getFailedRemarksMessage());
        validationData.setValue(String.valueOf(null));
        validationData.setValidationStatus(false);
        return validationData;
    }


    private String getFullName(String resourceName, OcrFieldData fieldDataSurname, OcrFieldData fieldDataGivename, String documetTypeSuffix) {
        String fullName;

        OcrFieldValue valueSurName = getFieldValueById(resourceName + "##" + surnameOcrExtractionFieldName + "##" + documetTypeSuffix, fieldDataSurname);
        OcrFieldValue valueGivenName = getFieldValueById(resourceName + "##" + givenNamesOcrExtractionFieldName + "##" + documetTypeSuffix, fieldDataGivename);
        if (valueSurName.getValue() == null || valueGivenName.getValue() == null) {
            fullName = null;
        } else {
            fullName = valueSurName.getValue() + " " + valueGivenName.getValue();
        }
        return fullName;
    }

    public String getUtilityBillNameOcrExtractionField() {
        return utilityBillNameOcrExtractionField;
    }

    public void setUtilityBillNameOcrExtractionField(String utilityBillNameOcrExtractionField) {
        this.utilityBillNameOcrExtractionField = utilityBillNameOcrExtractionField;
    }

    public void setSurnameOcrExtractionFieldName(String surnameOcrExtractionFieldName) {
        this.surnameOcrExtractionFieldName = surnameOcrExtractionFieldName;
    }

    public void setGivenNamesOcrExtractionFieldName(String givenNamesOcrExtractionFieldName) {
        this.givenNamesOcrExtractionFieldName = givenNamesOcrExtractionFieldName;
    }

    public void setResourceNameListToCheck(List<String> resourceNameListToCheck) {
        this.resourceNameListToCheck = resourceNameListToCheck;
    }
}
