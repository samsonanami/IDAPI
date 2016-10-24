package com.fintech.orion.hermesagentservices.transmission.response.handler;

import com.fintech.orion.coreservices.LicenseServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.license.LicenseDTO;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.request.GenericRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TharinduMP on 10/24/2016.
 * LicenseHandler
 */
public class LicenseHandler implements LicenseHandlerInterface {

    @Autowired
    private LicenseServiceInterface licenseService;

    @Override
    public void updateLicense(ProcessDTO processDTO, GenericRequest genericRequest) {
        //for the moment, update license on all instances
        try {
            //get license
            LicenseDTO licenseDTO = licenseService.findById(genericRequest.getLicenseId().intValue());

            //update count
            licenseDTO.setRequestCount(licenseDTO.getRequestCount() + 1);

            //save
            licenseService.saveOrUpdate(licenseDTO);

        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }

    }
}
