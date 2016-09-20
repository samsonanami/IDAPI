package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import org.springframework.stereotype.Repository;

/**
 * Created by ChathurangaRW on 9/13/2016.
 */
@Repository
public class ClientRepository extends AbstractConfigDAO<Client, Integer> implements ClientRepositoryInterface {

    /**
     * @param entityClass
     */
    protected ClientRepository(Class<Client> entityClass) {
        super(entityClass);
    }


}
