package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Response;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

public interface ResponseRepositoryInterface extends CrudRepository<Response , Integer> {
	
	@Transactional
	@Query("select r from Response r where  r.processId = ?1")
	Response findProcessByProcessingIdentificationCode(int responseId);
}
