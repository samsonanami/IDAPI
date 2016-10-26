package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;

/**
 * ProcessingRequest entity service interface
 */
public interface ProcessingRequestServiceInterface {

    ProcessingRequestDTO save(ClientDTO clientDTO);

    ProcessingRequestDTO findByIdIdentificationCode(String identificationCode) throws ItemNotFoundException;
}
