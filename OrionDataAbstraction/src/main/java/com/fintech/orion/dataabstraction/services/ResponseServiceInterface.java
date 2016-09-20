package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.Response;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public interface ResponseServiceInterface {
    List<Response> getResponseList();

    Response getResponseById(int id) throws ItemNotFoundException;

    void saveResponse(Response response);

    void updateResponse(Response response);

    boolean deleteResponseById(int id) throws ItemNotFoundException;

    void deleteResponse(Response response);
}
