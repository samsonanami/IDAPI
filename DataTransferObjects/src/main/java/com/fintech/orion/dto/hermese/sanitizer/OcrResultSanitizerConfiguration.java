package com.fintech.orion.dto.hermese.sanitizer;

import java.util.List;

/**
 * Created by sasitha on 2/27/17.
 *
 */
public class OcrResultSanitizerConfiguration {

    List<String> ocrExtractionFields;

    public List<String> getOcrExtractionFields() {
        return ocrExtractionFields;
    }

    public void setOcrExtractionFields(List<String> ocrExtractionFields) {
        this.ocrExtractionFields = ocrExtractionFields;
    }
}
