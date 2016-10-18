package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProcessType entity service class
 */
@Service
public class ProcessTypeService extends AbstractService<ProcessType, Integer> implements ProcessTypeServiceInterface {

    @Autowired
    private ProcessTypeRepositoryInterface processTypeRepositoryInterface;

    @Transactional
    @Override
    public ProcessType findByType(String type) throws ItemNotFoundException {
        return processTypeRepositoryInterface.findByType(type);
    }

}
