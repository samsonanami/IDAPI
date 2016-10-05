package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientLicenseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientLicenseService implements ClientLicenseServiceInterface {

    @Autowired
    private ClientLicenseRepositoryInterface clientLicenseRepositoryInterface;

    @Transactional
    @Override
    public List<ClientLicense> getClientLicenseList() {
        return clientLicenseRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public ClientLicense getClientLicenseById(int id) throws ItemNotFoundException {
        ClientLicense clientLicense = clientLicenseRepositoryInterface.findById(id);
        if (clientLicense != null) {
            return clientLicense;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveClientLicense(ClientLicense clientLicense) {
        clientLicenseRepositoryInterface.saveOrUpdate(clientLicense);
    }

    @Transactional
    @Override
    public void updateClientLicense(ClientLicense clientLicense) {
        clientLicenseRepositoryInterface.saveOrUpdate(clientLicense);
    }

    @Transactional
    @Override
    public boolean deleteClientLicenseById(int id) throws ItemNotFoundException {
        ClientLicense clientLicense = clientLicenseRepositoryInterface.findById(id);
        if(clientLicense != null){
            clientLicenseRepositoryInterface.delete(clientLicense);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteClientLicense(ClientLicense clientLicense) {
        clientLicenseRepositoryInterface.delete(clientLicense);
    }
}
