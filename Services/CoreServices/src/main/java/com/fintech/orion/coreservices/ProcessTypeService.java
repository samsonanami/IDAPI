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

    @Override
    public List<ProcessTypeDTO> getAllDTOs() {
        return processTypeMapper.processTypesToProcessTypeDTOs(getAll());
    }

    @Override
    public ProcessTypeDTO findById(int id) throws ItemNotFoundException {
        return processTypeMapper.processTypeToProcessTypeDTO(findById(new Integer(id)));
    }

    @Override
    public void saveOrUpdate(ProcessTypeDTO processTypeDTO) {
        saveOrUpdate(processTypeMapper.processTypeDTOToProcessType(processTypeDTO));
    }

    @Override
    public void delete(ProcessTypeDTO processTypeDTO) {
        delete(processTypeMapper.processTypeDTOToProcessType(processTypeDTO));
    }

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;

    @Transactional
    @Override
    public ProcessType findByType(String type) throws ItemNotFoundException {
        return processTypeRepositoryInterface.findByType(type);
    }

}
