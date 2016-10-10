package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Response;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

public interface ResponseServiceInterface {
    List<Response> getResponseList();

    Response getResponseById(int id) throws ItemNotFoundException;

    void saveOrUpdateResponse(Response response);

    boolean deleteResponseById(int id) throws ItemNotFoundException;

    void deleteResponse(Response response);
}
