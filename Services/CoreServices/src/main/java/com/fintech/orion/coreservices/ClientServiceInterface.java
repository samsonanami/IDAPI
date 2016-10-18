package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.client.ClientDTO;

/**
 * Client entity service interface
 */
public interface ClientServiceInterface extends ServiceInterface<Client, Integer> {

    ClientDTO findByAuthToken(String authToken) throws ItemNotFoundException;

}
