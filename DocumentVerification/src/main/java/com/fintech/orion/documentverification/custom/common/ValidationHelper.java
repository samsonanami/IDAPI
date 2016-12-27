package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.strategy.ValidationResult;
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
}
