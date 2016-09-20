package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
public interface ClientLicenseServiceInterface {

    List<ClientLicense> getClientLicenseList();

    ClientLicense getClientLicenseById(int id) throws ItemNotFoundException;

    void saveClientLicense(ClientLicense clientLicense);

    void updateClientLicense(ClientLicense clientLicense);

    boolean deleteClientLicenseById(int id) throws ItemNotFoundException;

    void deleteClientLicense(ClientLicense clientLicense);

}
