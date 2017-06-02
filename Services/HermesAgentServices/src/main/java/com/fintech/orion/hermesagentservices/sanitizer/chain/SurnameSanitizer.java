package com.fintech.orion.hermesagentservices.sanitizer.chain;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;

public class SurnameSanitizer extends OracleResponseSanitizerChain {

    private String surnameOcrExtractionField;
    @Override
    protected void execute(OcrResponse ocrResponse) {
        for (OcrFieldData ocrFieldData : ocrResponse.getData()){
            if(ocrFieldData.getId().equals(surnameOcrExtractionField)){
                for (OcrFieldValue fieldValue : ocrFieldData.getValue()){
                    fieldValue.setValue(fieldValue.getValue().replaceAll(" ", ""));
                }
            }
        }
    }


    public void setSurnameOcrExtractionField(String surnameOcrExtractionField) {
        this.surnameOcrExtractionField = surnameOcrExtractionField;
    }
}
