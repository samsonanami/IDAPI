package com.fintech.orion.hermesagentservices.sanitizer.chain;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.hermese.sanitizer.OcrResultSanitizerConfiguration;

/**
 * Created by sasitha on 2/27/17.
 */
public abstract class OracleResponseSanitizerChain {

    private OracleResponseSanitizerChain next;

    private OcrResultSanitizerConfiguration sanitizerConfiguration;

    public void setNext(OracleResponseSanitizerChain next) {
        this.next = next;
    }

    public final void start(OcrResponse ocrResponse){
        execute(ocrResponse);
        if (next != null){
            next.start(ocrResponse);
        }
    }

    protected abstract void execute(OcrResponse ocrResponse);

    public OcrResultSanitizerConfiguration getSanitizerConfiguration() {
        return sanitizerConfiguration;
    }

    public void setSanitizerConfiguration(OcrResultSanitizerConfiguration sanitizerConfiguration) {
        this.sanitizerConfiguration = sanitizerConfiguration;
    }

    public boolean checkOcrFieldIdInConfiguration(String ocrFieldId){
        boolean isOcrFieldIdInConfiguration = false;
        for (String idFromConfiguration : getSanitizerConfiguration().getOcrExtractionFields()){
            if (idFromConfiguration.equalsIgnoreCase(ocrFieldId)){
                isOcrFieldIdInConfiguration = true;
                break;
            }
        }

        return isOcrFieldIdInConfiguration;
    }
}
