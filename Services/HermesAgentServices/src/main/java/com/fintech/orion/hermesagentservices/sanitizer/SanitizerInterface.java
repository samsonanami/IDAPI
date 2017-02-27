package com.fintech.orion.hermesagentservices.sanitizer;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;

/**
 * Created by sasitha on 2/27/17.
 */
public interface SanitizerInterface {

    void sanitize(OcrResponse ocrResponse);
}
