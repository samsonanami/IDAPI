package com.fintech.orion.dataabstraction.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequestStatus;

@Repository
public interface ProcessingRequestStatusRepositoryInterface extends CrudRepository<ProcessingRequestStatus, Integer>{
    
    ProcessingRequestStatus findProcessingRequestStatusByProcessingRequest(ProcessingRequest processingRequestEntity);

}
