package com.fintech.orion.hermesagentservices.transformer;

import com.fintech.orion.common.exceptions.transformer.RequestTransformerException;
import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;

public interface ResponseTransformer {

    String transform(VerificationProcessDetailedResponse detailedResponse) throws RequestTransformerException;
}
