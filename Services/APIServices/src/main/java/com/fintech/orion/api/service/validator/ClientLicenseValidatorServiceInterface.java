package com.fintech.orion.api.service.validator;

import com.fintech.orion.api.service.exceptions.ClientLicenseValidatorException;
import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;

/**
 *
 * Created by sasitha on 11/2/16.
 */
public interface ClientLicenseValidatorServiceInterface {
    boolean validate(String licenseKey, ProcessingRequest processingRequest) throws ClientLicenseValidatorException;
}
