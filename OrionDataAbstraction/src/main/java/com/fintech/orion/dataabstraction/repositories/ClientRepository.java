package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository extends AbstractDAO<Client, Integer> implements ClientRepositoryInterface {

    public ClientRepository() {
        super(Client.class);
    }

}
