package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepositoryInterface extends CrudRepository<Client, Integer> {

    Client findClientByUserName(String userName);
}
