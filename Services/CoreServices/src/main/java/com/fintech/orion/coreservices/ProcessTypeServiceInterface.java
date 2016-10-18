package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

/**
 * ProcessType entity service interface
 */
public interface ProcessTypeServiceInterface extends ServiceInterface<ProcessType, Integer> {

    ProcessType findByType(String type) throws ItemNotFoundException;

}
