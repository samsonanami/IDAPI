package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;

import java.util.List;

/**
 * Created by sasitha on 12/26/16.
 */
public class ValidationHelper {

    private boolean isCriticalValidation;
    private String successRemarksMessage;
    private String failedRemarksMessage;
    private String ocrExtractionFieldName;

    public OcrFieldValue getFieldValueById(String id, OcrFieldData fieldData){
        OcrFieldValue fieldValue = new OcrFieldValue();
        for (OcrFieldValue f : fieldData.getValue()){
            if (f.getId().equalsIgnoreCase(id)){
                fieldValue = f;
            }
        }
        return fieldValue;
    }

    public OcrFieldData getFieldDataById(String id, OcrResponse ocrResponse){
        OcrFieldData data = new OcrFieldData();
        for (OcrFieldData fieldData : ocrResponse.getData()){
            if (fieldData.getId().equalsIgnoreCase(id)){
                data = fieldData;
            }
        }
        return data;
    }

    public boolean isCriticalValidation() {
        return isCriticalValidation;
    }

    public void setCriticalValidation(boolean criticalValidation) {
        isCriticalValidation = criticalValidation;
    }

    public String getSuccessRemarksMessage() {
        return successRemarksMessage;
    }

    public void setSuccessRemarksMessage(String successRemarksMessage) {
        this.successRemarksMessage = successRemarksMessage;
    }

    public String getFailedRemarksMessage() {
        return failedRemarksMessage;
    }

    public void setFailedRemarksMessage(String failedRemarksMessage) {
        this.failedRemarksMessage = failedRemarksMessage;
    }

    public String getOcrExtractionFieldName() {
        return ocrExtractionFieldName;
    }

    public void setOcrExtractionFieldName(String ocrExtractionFieldName) {
        this.ocrExtractionFieldName = ocrExtractionFieldName;
    }

    public ValidationData validateData(List<OcrFieldValue> values) throws CustomValidationException
    { ValidationData  validationData= new  ValidationData();
        int valueCount = 1;
        String dataValue="";
        if(values.size() > 1){
            validationData.setValidationStatus(false);
            validationData.setRemarks("Only one documentation available");
        }
        for(OcrFieldValue value:values) {

                if(value.getValue().equals(dataValue)){
                    validationData.setValidationStatus(true);
                    validationData.setRemarks("");
                }else{
                    validationData.setValidationStatus(false);
                    validationData.setRemarks("Documents data not matched");
                    break;
                }
            valueCount++;
            dataValue=value.getValue();
            }


       return  validationData;
    }

    public ValidationData isAllOcrFieldValueHasSameValueField(List<OcrFieldValue> values){
        boolean isValuesEqual = false;
        ValidationData validationData = new ValidationData();
        String valueOfTheFirstObject = values.iterator().next().getValue();
        if (valueOfTheFirstObject != null){
            valueOfTheFirstObject = valueOfTheFirstObject.trim();
        }
        if (values.size() > 1){
            for (OcrFieldValue fieldValue : values){
                if (fieldValue.getValue() != null && fieldValue.getValue().trim().equalsIgnoreCase(valueOfTheFirstObject)){
                    validationData.setRemarks(successRemarksMessage);
                    isValuesEqual = true;
                }else {
                    validationData.setRemarks(failedRemarksMessage);
                    isValuesEqual = false;
                    break;
                }
            }
        }else {
            validationData.setRemarks("Verification could not be performed : Only one document is present");
            isValuesEqual = false;
        }
        validationData.setValue(valueOfTheFirstObject);
        validationData.setValidationStatus(isValuesEqual);
        return validationData;
    }

    public ValidationData validateInput(OcrFieldData fieldData){
        ValidationData validationData = new ValidationData();
        if (fieldData != null && fieldData.getValue() != null && !fieldData.getValue().isEmpty()) {
            validationData.setValidationStatus(true);
        } else {
            validationData.setValue("Unknown");
            validationData.setRemarks("Could not perform validation : Not enough data available to perform validation." +
                    "Please contact support");
            validationData.setValidationStatus(false);
        }
        return validationData;
    }

    public String getDocumentNameFromOcrFieldValueId(String fieldValueId){
        String[] strings = fieldValueId.split("##");
        return strings[0];
    }
}
