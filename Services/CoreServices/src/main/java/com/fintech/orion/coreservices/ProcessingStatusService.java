package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepositoryInterface;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import com.fintech.orion.mapping.processingstatus.ProcessingStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ProcessingStatus entity service class
 */
public class ProcessingStatusService extends AbstractService<ProcessingStatus, Integer> implements ProcessingStatusServiceInterface {

    @Autowired
    private ProcessingStatusRepositoryInterface processingStatusRepositoryInterface;

    @Autowired
    private ProcessingStatusMapper processingStatusMapper;

    @Transactional
    @Override
    public List<ProcessingStatusDTO> getAllDTOs() {
        return processingStatusMapper.processingStatussToProcessingStatusDTOs(getAll());
    }

    @Transactional
    @Override
    public ProcessingStatusDTO findById(int id) throws ItemNotFoundException {
        return processingStatusMapper.processingStatusToProcessingStatusDTO(findById(new Integer(id)));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ProcessingStatusDTO processingStatusDTO) {
        saveOrUpdate(processingStatusMapper.processingStatusDTOToProcessingStatus(processingStatusDTO));
    }

    @Transactional
    @Override
    public void delete(ProcessingStatusDTO processingStatusDTO) {
        delete(processingStatusMapper.processingStatusDTOToProcessingStatus(processingStatusDTO));
    }

    @Transactional
    @Override
    public ProcessingStatus findByStatus(Status status) throws ItemNotFoundException {
        return processingStatusRepositoryInterface.findByStatus(status);
    }
}
