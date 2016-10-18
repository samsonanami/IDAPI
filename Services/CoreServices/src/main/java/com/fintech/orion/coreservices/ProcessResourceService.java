package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepositoryInterface;
import com.fintech.orion.dto.processresource.ProcessResourceDTO;
import com.fintech.orion.mapping.processresource.ProcessResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ProcessResource entity service class
 */
@Service
public class ProcessResourceService extends AbstractService<ProcessResource, Integer> implements ProcessResourceServiceInterface {

    @Autowired
    private ProcessResourceRepositoryInterface processResourceRepositoryInterface;

    @Autowired
    private ProcessResourceMapper processResourceMapper;

    @Override
    public List<ProcessResourceDTO> getAllDTOs() {
        return processResourceMapper.processResourcesToProcessResourceDTOs(getAll());
    }

    @Override
    public ProcessResourceDTO findById(int id) throws ItemNotFoundException {
        return processResourceMapper.processResourceToProcessResourceDTO(findById(new Integer(id)));
    }

    @Override
    public void saveOrUpdate(ProcessResourceDTO processResourceDTO) {
        saveOrUpdate(processResourceMapper.processResourceDTOToProcessResource(processResourceDTO));
    }

    @Override
    public void delete(ProcessResourceDTO processResourceDTO) {
        delete(processResourceMapper.processResourceDTOToProcessResource(processResourceDTO));
    }

    @Transactional
    @Override
    public ProcessResource save(Process process, Resource resource, String resourceName) {
        ProcessResource processResource = new ProcessResource();
        processResource.setProcess(process);
        processResource.setResource(resource);
        processResource.setResourceName(resourceName);
        processResourceRepositoryInterface.saveOrUpdate(processResource);
        return processResource;
    }
}
