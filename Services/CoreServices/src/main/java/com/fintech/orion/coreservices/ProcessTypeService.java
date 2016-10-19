package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepositoryInterface;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.mapping.processtype.ProcessTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ProcessType entity service class
 */
@Service
public class ProcessTypeService extends AbstractService<ProcessType, Integer> implements ProcessTypeServiceInterface {

    @Autowired
    private ProcessTypeMapper processTypeMapper;

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;

    @Transactional
    @Override
    public List<ProcessTypeDTO> getAllDTOs() {
        return processTypeMapper.processTypesToProcessTypeDTOs(getAll());
    }

    @Transactional
    @Override
    public ProcessTypeDTO findById(int id) throws ItemNotFoundException {
        return processTypeMapper.processTypeToProcessTypeDTO(processTypeRepositoryInterface.findById(id));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ProcessTypeDTO processTypeDTO) {
        saveOrUpdate(processTypeMapper.processTypeDTOToProcessType(processTypeDTO));
    }

    @Transactional
    @Override
    public void delete(ProcessTypeDTO processTypeDTO) {
        delete(processTypeMapper.processTypeDTOToProcessType(processTypeDTO));
    }

    @Transactional
    @Override
    public ProcessTypeDTO findByType(String type) throws ItemNotFoundException {
        return processTypeMapper.processTypeToProcessTypeDTO(processTypeRepositoryInterface.findByType(type));
    }

}
