package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.Response;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResponseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ChathurangaRW on 9/19/2016.
 */
public class ResponseService implements ResponseServiceInterface {

    @Autowired
    private ResponseRepositoryInterface repositoryInterface;

    @Override
    public List<Response> getResponseList() {
        return repositoryInterface.getAll();
    }

    @Override
    public Response getResponseById(int id) throws ItemNotFoundException {
        Response response = repositoryInterface.findById(id);
        if (response != null) {
            return response;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Override
    public void saveResponse(Response response) {
        repositoryInterface.saveOrUpdate(response);
    }

    @Override
    public void updateResponse(Response response) {
        repositoryInterface.saveOrUpdate(response);
    }

    @Override
    public boolean deleteResponseById(int id) throws ItemNotFoundException {
        Response response = repositoryInterface.findById(id);
        if(response != null){
            repositoryInterface.delete(response);
            return true;
        }
        return false;
    }

    @Override
    public void deleteResponse(Response response) {
        repositoryInterface.delete(response);
    }
}
