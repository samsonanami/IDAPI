package com.fintech.orion.hermesagentservices.status;

import com.fintech.orion.dto.response.api.VerificationProcessDetailedResponse;
import com.fintech.orion.dto.response.external.CustomValidation;
import com.fintech.orion.dto.response.external.DocumentMrzVizValidation;

import java.util.List;

public interface VerificationProcessStatusCalculator<E, I> {

    I calculateSingleVerificationProcessStatus(List<CustomValidation> customValidations,
                                                     List<DocumentMrzVizValidation> documentMrzVizValidations);

    I calculateFinalVerificationStatus(VerificationProcessDetailedResponse detailedResponse,
                                             E finalVerificationResponse);
}
