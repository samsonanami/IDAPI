package com.fintech.orion.hermesagentservices.transmission.response.handler;

import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.request.GenericRequest;

/**
 * Created by TharinduMP on 10/24/2016.
 */
public interface LicenseHandlerInterface {
    void updateLicense(ProcessDTO processDTO, GenericRequest genericRequest);
}
