package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.client.ClientDTO;

/**
 * Client entity service interface
 */
public interface ClientServiceInterface {

    ClientDTO findByAuthToken(String authToken) throws ItemNotFoundException;

}
