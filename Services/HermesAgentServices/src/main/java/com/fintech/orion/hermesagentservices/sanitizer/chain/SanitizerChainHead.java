package com.fintech.orion.hermesagentservices.sanitizer.chain;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sasitha on 2/27/17.
 *
 */
public class SanitizerChainHead extends OracleResponseSanitizerChain {
    private static final Logger LOGGER = LoggerFactory.getLogger(SanitizerChainHead.class);

    @Override
    protected void execute(OcrResponse ocrResponse) {
        LOGGER.debug("Start sanitizing ocr response {} ", ocrResponse);
    }
}
