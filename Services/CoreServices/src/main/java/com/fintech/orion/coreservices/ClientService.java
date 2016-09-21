package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService implements ClientServiceInterface {

    @Autowired
    private ClientRepositoryInterface clientRepositoryInterface;

    @Transactional
    @Override
    public List<Client> getClientList() {
        return clientRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public Client getClientById(int id) throws ItemNotFoundException {
            Client client = clientRepositoryInterface.findById(id);
            if (client != null) {
                return client;
            } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveClient(Client client) {
        clientRepositoryInterface.saveOrUpdate(client);
    }

    @Transactional
    @Override
    public void updateClient(Client client) {
        clientRepositoryInterface.saveOrUpdate(client);
    }

    @Transactional
    @Override
    public boolean deleteClientById(int id) throws ItemNotFoundException {
        Client client = clientRepositoryInterface.findById(id);
        if(client != null){
            clientRepositoryInterface.delete(client);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteClient(Client client) {
        clientRepositoryInterface.delete(client);
    }
}
