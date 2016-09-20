package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/13/2016.
 */
public class ClientService implements ClientServiceInterface {

    @Autowired
    private ClientRepositoryInterface repositoryInterface;

    @Override
    public List<Client> getClientList() {
        return repositoryInterface.getAll();
    }

    @Override
    public Client getClientById(int id) throws ItemNotFoundException {
            Client client = repositoryInterface.findById(id);
            if (client != null) {
                return client;
            } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveClient(Client client) {
        repositoryInterface.saveOrUpdate(client);
    }

    @Override
    public void updateClient(Client client) {
        repositoryInterface.saveOrUpdate(client);
    }

    @Override
    public boolean deleteClientById(int id) throws ItemNotFoundException {
        Client client = repositoryInterface.findById(id);
        if(client != null){
            repositoryInterface.delete(client);
            return true;
        }
        return false;
    }

    @Override
    public void deleteClient(Client client) {
        repositoryInterface.delete(client);
    }
}
