package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;

import java.util.List;

/**
 * ProcessType entity service interface
 */
public interface ProcessTypeServiceInterface extends ServiceInterface<ProcessType, Integer> {

    List<ProcessTypeDTO> getAllDTOs();

    ProcessTypeDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ProcessTypeDTO processTypeDTO);

    void delete(ProcessTypeDTO processTypeDTO);

    ProcessTypeDTO findByType(String type) throws ItemNotFoundException;

}
