package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingRequestRepositoryInterface extends CrudRepository<ProcessingRequest, Integer> {
    ProcessingRequest findProcessingRequestByProcessingRequestIdentificationCode(String identificationCode);

    ProcessingRequest findProcessingRequestByProcessingRequestIdentificationCodeAndClient(String identificationCode, Client client);
}
