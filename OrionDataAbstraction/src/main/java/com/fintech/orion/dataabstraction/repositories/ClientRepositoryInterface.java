package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

public interface ClientRepositoryInterface extends DAOInterface<Client, Integer> {

    Client findByAuthToken(String authToken) throws ItemNotFoundException;

    Client findByUserName(String username) throws ItemNotFoundException;

}
