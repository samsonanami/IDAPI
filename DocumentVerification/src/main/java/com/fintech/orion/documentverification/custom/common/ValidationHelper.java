package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;

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
}
