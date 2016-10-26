package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;

/**
 * ProcessType entity service interface
 */
public interface ProcessTypeServiceInterface {

    ProcessTypeDTO findById(int id) throws ItemNotFoundException;

    ProcessTypeDTO findByType(String type) throws ItemNotFoundException;

}
