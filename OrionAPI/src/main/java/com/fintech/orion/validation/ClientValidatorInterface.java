package com.fintech.orion.validation;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.client.ClientDTO;

/**
 * Verify client interface
 */
public interface ClientValidatorInterface {

    ClientDTO checkClientValidity(String authToken) throws ItemNotFoundException;

}
