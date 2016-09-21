package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.Response;
import org.springframework.stereotype.Repository;

@Repository
public class ResponseRepository extends AbstractDAO<Response, Integer> implements ResponseRepositoryInterface {

    protected ResponseRepository() {
        super(Response.class);
    }

}
