package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.helper.GenerateTimestamp;
import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;
import com.fintech.orion.mapping.processrequest.ProcessRequestMapper;
import com.fintech.orion.mapping.processingrequest.ProcessingRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ProcessingRequest entity service class
 */
@Service
public class ProcessingRequestService extends AbstractService<ProcessingRequest, Integer> implements ProcessingRequestServiceInterface {

    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;
	
	@Autowired
    private ProcessingRequestMapper processingRequestMapper;

    @Autowired
    private ProcessRequestMapper processRequestMapper;

    @Transactional
    @Override
    public List<ProcessingRequestDTO> getAllDTOs() {
        return processingRequestMapper.processingRequestsToProcessingRequestDTOs(getAll());
    }

    @Transactional
    @Override
    public ProcessingRequestDTO findById(int id) throws ItemNotFoundException {
        return processingRequestMapper.processingRequestToProcessingRequestDTO(findById(new Integer(id)));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ProcessingRequestDTO processingRequestDTO) {
        saveOrUpdate(processingRequestMapper.processingRequestDTOToProcessingRequest(processingRequestDTO));
    }

    @Transactional
    @Override
    public void delete(ProcessingRequestDTO processingRequestDTO) {
        delete(processingRequestMapper.processingRequestDTOToProcessingRequest(processingRequestDTO));
    }

    @Transactional
    @Override
    public ProcessingRequest save(Client client) {
        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setReceivedOn(GenerateTimestamp.timestamp());
        processingRequest.setClient(client);
        processingRequest.setProcessingRequestIdentificationCode(GenerateUUID.uuidNumber());
        processingRequestRepositoryInterface.saveOrUpdate(processingRequest);
        return processingRequest;
    }

    @Transactional
    @Override
    public ProcessingRequestDTO findByIdIdentificationCode(String identificationCode) throws ItemNotFoundException {
        return processRequestMapper.processingRequestToProcessingRequestDTO(processingRequestRepositoryInterface.findByIdIdentificationCode(identificationCode));
    }
}
