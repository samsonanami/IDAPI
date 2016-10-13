package com.fintech.orion.helper;

import com.fintech.orion.coreservices.ClientServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientValidator implements ClientValidatorInterface {

    @Autowired
    private ClientServiceInterface clientServiceInterface;

    @Override
    public Client checkClientValidity(String authToken) throws ItemNotFoundException {
        return clientServiceInterface.findByAuthToken(authToken);
    }
}
