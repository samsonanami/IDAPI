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
        List<String> fullNameList = new ArrayList<String>();

        OcrFieldData fieldDataSurname = getFieldDataById(surnameOcrExtractionFieldName, ocrResponse);
        OcrFieldData fieldDataGivenNames = getFieldDataById(givenNamesOcrExtractionFieldName, ocrResponse);
        OcrFieldData fieldDataUtilityBillFullName = getFieldDataById(utilityBillNameOcrExtractionField, ocrResponse);

        for (String resourceNameToValidate : resourceNameListToCheck) {
            fullNameList.add(getFullName(resourceNameToValidate, fieldDataSurname, fieldDataGivenNames));
        }

        String fullName = getFieldValueById(resourceName.getName() + "##" + utilityBillNameOcrExtractionField, fieldDataUtilityBillFullName).getValue();

        validationData = validateInput(fieldDataUtilityBillFullName);

        if (validationData.getValidationStatus()) {

            validationData = validateBillName(fullNameList, fullName);

        } else {
            validationData.setRemarks(getSuccessRemarksMessage());
        }

        return validationData;
    }

    private ValidationData validateBillName(List<String> stringList, String billFullName) {
        ValidationData validationData = new ValidationData();
        stringList.removeAll(Collections.singleton(null));
        for (String fullName : stringList) {
            if (fullName.equalsIgnoreCase(billFullName)) {
                validationData.setValidationStatus(true);
                validationData.setValue(billFullName);

            } else {
                validationData.setRemarks(getFailedRemarksMessage());
                validationData.setValue(String.valueOf(billFullName));
                validationData.setValidationStatus(false);
                break;
            }
        }
        return validationData;
    }


    private String getFullName(String resourceName, OcrFieldData fieldDataSurname, OcrFieldData fieldDataGivename) {
        String fullName;

        OcrFieldValue valueSurName = getFieldValueById(resourceName + "##" + surnameOcrExtractionFieldName, fieldDataSurname);
        OcrFieldValue valueGivenName = getFieldValueById(resourceName + "##" + givenNamesOcrExtractionFieldName, fieldDataGivename);
        if (valueSurName.getValue() == null || valueGivenName.getValue() == null) {
            fullName = null;
        } else {
            fullName = valueSurName.getValue() + " " + valueGivenName.getValue();
        }
        return fullName;
    }

    public void setutilityBillNameOcrExtractionField(String utilityBillNameOcrExtractionField) {
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
