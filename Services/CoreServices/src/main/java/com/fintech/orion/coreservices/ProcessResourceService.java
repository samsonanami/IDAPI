package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepositoryInterface;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.processresource.ProcessResourceDTO;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.mapping.process.ProcessMapper;
import com.fintech.orion.mapping.processresource.ProcessResourceMapper;
import com.fintech.orion.mapping.resource.ResourceMapper;
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

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Transactional
    @Override
    public List<ProcessResourceDTO> getAllDTOs() {
        return processResourceMapper.processResourcesToProcessResourceDTOs(getAll());
    }

    @Transactional
    @Override
    public ProcessResourceDTO findById(int id) throws ItemNotFoundException {
        return processResourceMapper.processResourceToProcessResourceDTO(findById(new Integer(id)));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ProcessResourceDTO processResourceDTO) {
        saveOrUpdate(processResourceMapper.processResourceDTOToProcessResource(processResourceDTO));
    }

    @Transactional
    @Override
    public void delete(ProcessResourceDTO processResourceDTO) {
        delete(processResourceMapper.processResourceDTOToProcessResource(processResourceDTO));
    }

    @Transactional
    @Override
    public ProcessResource save(ProcessDTO processDTO, ResourceDTO resourceDTO, String resourceName) {
        ProcessResource processResource = new ProcessResource();
        processResource.setProcess(processMapper.processDTOToProcess(processDTO));
        processResource.setResource(resourceMapper.resourceDTOToResource(resourceDTO));
        processResource.setResourceName(resourceName);
        processResourceRepositoryInterface.saveOrUpdate(processResource);
        return processResource;
    }
}
