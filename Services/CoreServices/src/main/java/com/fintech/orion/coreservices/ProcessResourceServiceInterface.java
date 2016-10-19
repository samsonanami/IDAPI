package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.processresource.ProcessResourceDTO;
import com.fintech.orion.dto.resource.ResourceDTO;

import java.util.List;

/**
 * ProcessResource entity service interface
 */
public interface ProcessResourceServiceInterface extends ServiceInterface<ProcessResource, Integer> {

    List<ProcessResourceDTO> getAllDTOs();

    ProcessResourceDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ProcessResourceDTO processResourceDTO);

    void delete(ProcessResourceDTO processResourceDTO);

    ProcessResource save(ProcessDTO process, ResourceDTO resource, String resourceName);
}
