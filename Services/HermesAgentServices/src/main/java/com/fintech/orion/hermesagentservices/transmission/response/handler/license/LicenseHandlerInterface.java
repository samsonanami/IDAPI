package com.fintech.orion.hermesagentservices.transmission.response.handler.license;

import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.request.GenericRequest;

/**
 * Created by TharinduMP on 10/24/2016.
 * LicenseHandlerInterface
 */
public interface LicenseHandlerInterface {
    void updateLicense(ProcessDTO processDTO, GenericRequest genericRequest);
}
