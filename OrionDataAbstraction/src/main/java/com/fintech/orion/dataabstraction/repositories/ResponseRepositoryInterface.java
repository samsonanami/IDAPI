package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.DAOInterface;
import com.fintech.orion.dataabstraction.entities.orion.Response;

public interface ResponseRepositoryInterface extends DAOInterface<Response , Integer> {

    void saveResponse(Response response);
}
