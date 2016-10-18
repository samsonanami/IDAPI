package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.helper.GenerateTimestamp;
import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.mapping.process.ProcessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Process entity service class
 */
@Service
public class ProcessService extends AbstractService<Process, Integer> implements ProcessServiceInterface {

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Autowired
    private ProcessMapper processMapper;

    @Override
    public List<ProcessDTO> getAllDTOs() {
        return processMapper.processesToProcessDTOs(getAll());
    }

    @Override
    public ProcessDTO findById(int id) throws ItemNotFoundException {
        return processMapper.processToProcessDTO(findById(new Integer(id)));
    }

    @Override
    public void saveOrUpdate(ProcessDTO processDTO) {
        saveOrUpdate(processMapper.processDTOToProcess(processDTO));
    }

    @Override
    public void delete(ProcessDTO processDTO) {
        delete(processMapper.processDTOToProcess(processDTO));
    }

    @Transactional
    @Override
    public Process save(ProcessType processType, ProcessingRequest processingRequest, ProcessingStatus processingStatus) {
        Process process = new Process();
        process.setRequestSentOn(GenerateTimestamp.timestamp());
        process.setProcessingRequest(processingRequest);
        process.setProcessType(processType);
        process.setProcessIdentificationCode(GenerateUUID.uuidNumber());
        process.setProcessingStatus(processingStatus);
        processRepositoryInterface.saveOrUpdate(process);
        return process;
    }

    @Transactional
    @Override
    public Process findByIdentificationCode(String identificationCode) throws ItemNotFoundException {
        return processRepositoryInterface.findByIdentificationCode(identificationCode);
    }
}
