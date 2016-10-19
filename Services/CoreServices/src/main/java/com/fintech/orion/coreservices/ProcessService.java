package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.helper.GenerateTimestamp;
import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.mapping.process.ProcessMapper;
import com.fintech.orion.mapping.processingrequest.ProcessingRequestMapper;
import com.fintech.orion.mapping.processingstatus.ProcessingStatusMapper;
import com.fintech.orion.mapping.processtype.ProcessTypeMapper;
import com.fintech.orion.mapping.resource.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Process entity service class
 */
@Service
public class ProcessService extends AbstractService<Process, Integer> implements ProcessServiceInterface {

    @Autowired
    private ProcessRepositoryInterface processRepositoryInterface;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private ProcessingRequestMapper processingRequestMapper;

    @Autowired
    private ProcessTypeMapper processTypeMapper;

    @Autowired
    private ProcessingStatusMapper processingStatusMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Transactional
    @Override
    public List<ProcessDTO> getAllDTOs() {
        return processMapper.processesToProcessDTOs(getAll());
    }

    @Transactional
    @Override
    public ProcessDTO findById(int id) throws ItemNotFoundException {
        return processMapper.processToProcessDTO(processRepositoryInterface.findById(id));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ProcessDTO processDTO) {
        saveOrUpdate(processMapper.processDTOToProcess(processDTO));
    }

    @Transactional
    @Override
    public void delete(ProcessDTO processDTO) {
        delete(processMapper.processDTOToProcess(processDTO));
    }

    @Transactional
    @Override
    public ProcessDTO save(ProcessTypeDTO processTypeDTO, ProcessingRequestDTO processingRequestDTO, ProcessingStatusDTO processingStatusDTO) {
        Process process = new Process();
        process.setRequestSentOn(GenerateTimestamp.timestamp());
        process.setProcessingRequest(processingRequestMapper.processingRequestDTOToProcessingRequest(processingRequestDTO));
        process.setProcessType(processTypeMapper.processTypeDTOToProcessType(processTypeDTO));
        process.setProcessIdentificationCode(GenerateUUID.uuidNumber());
        process.setProcessingStatus(processingStatusMapper.processingStatusDTOToProcessingStatus(processingStatusDTO));
        processRepositoryInterface.saveOrUpdate(process);
        return processMapper.processToProcessDTO(process);
    }

    @Transactional
    @Override
    public ProcessDTO findByIdentificationCode(String identificationCode) throws ItemNotFoundException {
        return processMapper.processToProcessDTO(processRepositoryInterface.findByIdentificationCode(identificationCode));
    }

    @Transactional
    @Override
    public List<ResourceDTO> resourceDTOsForProcess(int id) throws ItemNotFoundException {
        Process process = processRepositoryInterface.findById(id);
        return resourceMapper.resourcesToResourceDTOs(new ArrayList<>(process.getResources()));
    }
}
