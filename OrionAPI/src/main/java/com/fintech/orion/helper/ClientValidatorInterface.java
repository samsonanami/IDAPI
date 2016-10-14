package com.fintech.orion.helper;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

public interface ClientValidatorInterface {

    Client checkClientValidity(String authToken) throws ItemNotFoundException;

}
