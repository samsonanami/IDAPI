package com.fintech.orion.documentverification.factory;



import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 12/25/16.
 *
 */
public interface DocumentVerification {

    List<Object> verifyExtractedDocumentResult(OcrResponse ocrResponse, Map<String, VerificationConfiguration> configurations);
}
