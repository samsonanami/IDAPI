package com.fintech.orion.hermesagentservices.license;

import com.fintech.orion.common.exceptions.license.LicenseHandlerException;

/**
 * Created by sasitha on 1/15/17.
 *
 */
public interface LicenseHandlerInterface {

    void updateLicense(String clientLicenseId, String processingRequestId) throws LicenseHandlerException;
}
