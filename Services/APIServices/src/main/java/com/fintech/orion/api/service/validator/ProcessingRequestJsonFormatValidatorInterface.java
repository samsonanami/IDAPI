package com.fintech.orion.api.service.validator;

import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.dto.request.api.VerificationRequest;

/**
 *
 * Created by sasitha on 11/2/16.
 */
public interface ProcessingRequestJsonFormatValidatorInterface {

    boolean validate(VerificationRequest processingRequest);
}
