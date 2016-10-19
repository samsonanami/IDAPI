package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.dto.resource.ResourceDTO;

import java.util.List;

/**
 * Process entity service interface
 */
public interface ProcessServiceInterface extends ServiceInterface<Process, Integer> {

    List<ProcessDTO> getAllDTOs();

    ProcessDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ProcessDTO processDTO);

    void delete(ProcessDTO processDTO);

    ProcessDTO save(ProcessTypeDTO processTypeDTO, ProcessingRequestDTO processingRequestDTO, ProcessingStatusDTO processingStatusDTO);

    ProcessDTO findByIdentificationCode(String identificationCode) throws ItemNotFoundException;

    List<ResourceDTO> resourceDTOsForProcess(int id) throws ItemNotFoundException;
}
