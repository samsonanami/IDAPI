package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.License;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositoryInterface extends CrudRepository<Client, Integer> {

    Client findClientByUserName(String userName);

    Client findClientByLicenses(License license);
    
    @Query("select client from Client client where client.id in ?1")
    List<Client> findClientsById(List<Integer> ids);
}
