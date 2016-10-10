package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ProcessingRequestServiceInterface extends ServiceInterface<ProcessingRequest, Integer> {

    ProcessingRequest save(Client client);

    ProcessingRequest findByIdIdentificationCode(String identificationCode) throws ItemNotFoundException;
}
