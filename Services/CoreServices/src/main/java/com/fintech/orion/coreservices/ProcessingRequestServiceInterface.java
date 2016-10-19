package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;

import java.util.List;

/**
 * ProcessingRequest entity service interface
 */
public interface ProcessingRequestServiceInterface extends ServiceInterface<ProcessingRequest, Integer> {

    List<ProcessingRequestDTO> getAllDTOs();

    ProcessingRequestDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ProcessingRequestDTO processingRequestDTO);

    void delete(ProcessingRequestDTO processingRequestDTO);

    ProcessingRequestDTO save(ClientDTO clientDTO);

    ProcessingRequestDTO findByIdIdentificationCode(String identificationCode) throws ItemNotFoundException;
}