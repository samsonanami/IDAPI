package com.fintech.orion.hermesagentservices.sanitizer.chain;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;

/**
 * Created by sasitha on 2/27/17.
 *
 */
public class DrivingLicenseMrzSanitizer extends OracleResponseSanitizerChain {

    @Override
    protected void execute(OcrResponse ocrResponse) {
        for (OcrFieldData ocrFieldData : ocrResponse.getData()){
            for (OcrFieldValue ocrFieldValue : ocrFieldData.getValue()){
                if (checkOcrFieldIdInConfiguration(ocrFieldValue.getId())){
                    ocrFieldValue.setValue(sanitizeMrz(ocrFieldValue.getValue()));
                }
            }
        }
    }

    private String sanitizeMrz(String dirtyString) {
        return dirtyString.replace(" ", "");
    }


}
