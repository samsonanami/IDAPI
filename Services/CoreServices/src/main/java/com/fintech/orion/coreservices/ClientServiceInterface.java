package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ClientServiceInterface {
    List<Client> getClientList();

    Client getClientById(int id) throws ItemNotFoundException;

    void saveClient(Client client);

    void updateClient(Client client);

    boolean deleteClientById(int id) throws ItemNotFoundException;

    void deleteClient(Client client);

    Client getClientByAuthToken(String authToken) throws ItemNotFoundException;
}
