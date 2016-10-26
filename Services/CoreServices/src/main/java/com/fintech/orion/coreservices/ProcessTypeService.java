package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepositoryInterface;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.mapping.processtype.ProcessTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProcessType entity service class
 */
@Service
public class ProcessTypeService implements ProcessTypeServiceInterface {

    @Autowired
    private ProcessTypeMapper processTypeMapper;

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;

    @Transactional
    @Override
    public ProcessTypeDTO findById(int id) throws ItemNotFoundException {
        return processTypeMapper.processTypeToProcessTypeDTO(processTypeRepositoryInterface.findById(id));
    }

    @Transactional
    @Override
    public ProcessTypeDTO findByType(String type) throws ItemNotFoundException {
        return processTypeMapper.processTypeToProcessTypeDTO(processTypeRepositoryInterface.findByType(type));
    }

}
