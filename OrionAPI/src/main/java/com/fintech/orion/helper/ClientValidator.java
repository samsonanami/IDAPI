package com.fintech.orion.helper;

import com.fintech.orion.coreservices.ClientServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.client.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Verify client is valid using token
 */
public class ClientValidator implements ClientValidatorInterface {

    @Autowired
    private ClientServiceInterface clientServiceInterface;

    @Override
    public ClientDTO checkClientValidity(String authToken) throws ItemNotFoundException {
        return clientServiceInterface.findByAuthToken(authToken);
    }
}
