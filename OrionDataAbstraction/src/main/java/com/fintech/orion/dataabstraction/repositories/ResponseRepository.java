package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractConfigDAO;
import com.fintech.orion.dataabstraction.entities.orion.Response;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ResponseRepository extends AbstractConfigDAO<Response, Integer> implements ResponseRepositoryInterface {

    protected ResponseRepository(Class<Response> entityClass) {
        super(entityClass);
    }

}
