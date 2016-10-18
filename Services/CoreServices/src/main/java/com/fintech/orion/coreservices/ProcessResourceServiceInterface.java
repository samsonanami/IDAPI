package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.entities.orion.Resource;

/**
 * ProcessResource entity service interface
 */
public interface ProcessResourceServiceInterface extends ServiceInterface<ProcessResource, Integer> {

    ProcessResource save(Process process, Resource resource, String resourceName);
}
