package com.fintech.orion.hermesagentservices.transformer;

import com.fintech.orion.dto.response.external.VerificationResponse;

public interface ReVerificationDataTransformer<E> {

    E transform(VerificationResponse verificationResponse);
}
