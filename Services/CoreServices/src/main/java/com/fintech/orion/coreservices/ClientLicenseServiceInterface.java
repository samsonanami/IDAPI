package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ClientLicenseServiceInterface {

    List<ClientLicense> getClientLicenseList();

    ClientLicense getClientLicenseById(int id) throws ItemNotFoundException;

    void saveOrUpdateClientLicense(ClientLicense clientLicense);

    boolean deleteClientLicenseById(int id) throws ItemNotFoundException;

    void deleteClientLicense(ClientLicense clientLicense);

}
