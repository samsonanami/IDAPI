package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeLicenseRepositoryInterface;
import com.fintech.orion.dto.processtypelicense.ProcessTypeLicenseDTO;
import com.fintech.orion.mapping.processtypelicense.ProcessTypeLicenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ProcessTypeLicense entity service class
 */
@Service
public class ProcessTypeLicenseService extends AbstractService<ProcessTypeLicense, Integer> implements ProcessTypeLicenseServiceInterface {

    @Autowired
    private ProcessTypeLicenseMapper processTypeLicenseMapper;

    @Autowired
    private ProcessTypeLicenseRepositoryInterface processTypeLicenseRepositoryInterface;

    @Transactional
    @Override
    public List<ProcessTypeLicenseDTO> getAllDTOs() {
        return processTypeLicenseMapper.processTypeLicensesToProcessTypeLicenseDTOs(processTypeLicenseRepositoryInterface.getAll());
    }

    @Transactional
    @Override
    public ProcessTypeLicenseDTO findById(int id) throws ItemNotFoundException {
        return processTypeLicenseMapper.processTypeLicenseToProcessTypeLicenseDTO(processTypeLicenseRepositoryInterface.findById(id));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ProcessTypeLicenseDTO processTypeLicenseDTO) {
        processTypeLicenseRepositoryInterface.saveOrUpdate(processTypeLicenseMapper.processTypeLicenseDTOToProcessTypeLicense(processTypeLicenseDTO));
    }

    @Transactional
    @Override
    public void delete(ProcessTypeLicenseDTO processTypeLicenseDTO) {
        processTypeLicenseRepositoryInterface.delete(processTypeLicenseMapper.processTypeLicenseDTOToProcessTypeLicense(processTypeLicenseDTO));
    }

}
