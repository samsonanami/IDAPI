package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingStatusRepositoryInterface extends CrudRepository<ProcessingStatus, Integer> {

    ProcessingStatus findProcessingStatusByStatus(String status);

}
