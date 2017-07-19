package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Response;
import org.springframework.data.repository.CrudRepository;

public interface ResponseRepositoryInterface extends CrudRepository<Response , Integer> {
}
