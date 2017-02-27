package com.fintech.orion.hermesagentservices.sanitizer;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.hermesagentservices.sanitizer.chain.SanitizerChainHead;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sasitha on 2/27/17.
 */
public class Sanitizer implements SanitizerInterface {

    @Autowired
    private SanitizerChainHead sanitizerChainHead;

    @Override
    public void sanitize(OcrResponse ocrResponse) {
        sanitizerChainHead.start(ocrResponse);
    }
}
