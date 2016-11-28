package com.fintech.orion.helper;

import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;

/**
 *
 * Created by sasitha on 11/2/16.
 */
public interface ProcessingRequestJsonFormatValidatorInterface {

    boolean validate(ProcessingRequest processingRequest);
}
