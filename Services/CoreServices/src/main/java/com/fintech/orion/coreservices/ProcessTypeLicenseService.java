package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.processtypelicense.ProcessTypeLicenseDTO;
import com.fintech.orion.mapping.processtypelicense.ProcessTypeLicenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProcessTypeLicense entity service class
 */
@Service
public class ProcessTypeLicenseService extends AbstractService<ProcessTypeLicense, Integer> implements ProcessTypeLicenseServiceInterface {

    @Autowired
    private ProcessTypeLicenseMapper processTypeLicenseMapper;

    @Override
    public List<ProcessTypeLicenseDTO> getAllDTOs() {
        return processTypeLicenseMapper.processTypeLicensesToProcessTypeLicenseDTOs(getAll());
    }

    @Override
    public ProcessTypeLicenseDTO findById(int id) throws ItemNotFoundException {
        return processTypeLicenseMapper.processTypeLicenseToProcessTypeLicenseDTO(findById(new Integer(id)));
    }

    @Override
    public void saveOrUpdate(ProcessTypeLicenseDTO processTypeLicenseDTO) {
        saveOrUpdate(processTypeLicenseMapper.processTypeLicenseDTOToProcessTypeLicense(processTypeLicenseDTO));
    }

    @Override
    public void delete(ProcessTypeLicenseDTO processTypeLicenseDTO) {
        delete(processTypeLicenseMapper.processTypeLicenseDTOToProcessTypeLicense(processTypeLicenseDTO));
    }

}
