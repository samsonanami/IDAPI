package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

public interface ClientServiceInterface extends ServiceInterface<Client, Integer> {

    Client findByAuthToken(String authToken) throws ItemNotFoundException;

}
