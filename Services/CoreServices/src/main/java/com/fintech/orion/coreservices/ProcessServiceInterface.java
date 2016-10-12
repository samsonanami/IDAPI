package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

public interface ProcessServiceInterface extends ServiceInterface<Process, Integer> {

    Process save(ProcessType processType, ProcessingRequest processingRequest, ProcessingStatus processingStatus);

    Process findByIdentificationCode(String identificationCode) throws ItemNotFoundException;
}
