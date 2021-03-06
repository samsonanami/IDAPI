package com.fintech.orion.api.service.client;

import com.fintech.orion.api.service.exceptions.ClientServiceException;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.LicenseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by sasitha on 11/3/16.
 */
@Service
public class ClientLicenseService implements ClientLicenseServiceInterface{

    private static final int DEFAULT_LICENSE_INDEX = 0;

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Autowired
    private LicenseRepositoryInterface licenseRepositoryInterface;

    @Override
    @Transactional
    public String getActiveLicenseOfClient(String clientName) throws ClientServiceException {
        String licenseKey;
        Client client;
        List<License> licenseList = new ArrayList<>();
        client = clientRepositoryInterface.findClientByUserName(clientName);
        if (client == null){
            throw new ClientServiceException("No client found for user name : " + clientName);
        }
        try {
            licenseList = licenseRepositoryInterface.findLicensesByClientAndLessThanEndDateAndGreaterThanStartDate(client, new Date());
        } catch (ItemNotFoundException e) {
            throw new ClientServiceException("No license found for the client with user name : " + clientName, e);
        }
        if(licenseList.isEmpty()){
            throw new ClientServiceException("No valid license found for client with id : "+ client.getId());
        }else {
            licenseKey = licenseList.get(DEFAULT_LICENSE_INDEX).getLicenseKey();
        }
        return licenseKey;
    }
}
