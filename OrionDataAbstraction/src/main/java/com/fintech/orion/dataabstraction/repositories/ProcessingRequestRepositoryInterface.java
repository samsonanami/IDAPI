package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingRequestRepositoryInterface extends JpaRepository<ProcessingRequest, Integer> {
    ProcessingRequest findProcessingRequestByProcessingRequestIdentificationCode(String identificationCode);

    ProcessingRequest findProcessingRequestByProcessingRequestIdentificationCodeAndClient(String identificationCode, Client client);

    Page<ProcessingRequest> findProcessingRequestByClient(Client client, Pageable pageable);
}
