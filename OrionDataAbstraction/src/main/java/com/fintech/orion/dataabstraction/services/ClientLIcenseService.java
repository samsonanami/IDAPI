package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientLicenseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/14/2016.
 */
public class ClientLicenseService implements ClientLicenseServiceInterface {

    @Autowired
    private ClientLicenseRepositoryInterface repositoryInterface;

    @Override
    public List<ClientLicense> getClientLicenseList() {
        return repositoryInterface.getAll();
    }

    @Override
    public ClientLicense getClientLicenseById(int id) throws ItemNotFoundException {
        ClientLicense clientLicense = repositoryInterface.findById(id);
        if (clientLicense != null) {
            return clientLicense;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveClientLicense(ClientLicense clientLicense) {
        repositoryInterface.saveOrUpdate(clientLicense);
    }

    @Override
    public void updateClientLicense(ClientLicense clientLicense) {
        repositoryInterface.saveOrUpdate(clientLicense);
    }

    @Override
    public boolean deleteClientLicenseById(int id) throws ItemNotFoundException {
        ClientLicense clientLicense = repositoryInterface.findById(id);
        if(clientLicense != null){
            repositoryInterface.delete(clientLicense);
            return true;
        }
        return false;
    }

    @Override
    public void deleteClientLicense(ClientLicense clientLicense) {
        repositoryInterface.delete(clientLicense);
    }
}
