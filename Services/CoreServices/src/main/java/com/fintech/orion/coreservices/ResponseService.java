package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Response;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResponseRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResponseService implements ResponseServiceInterface {

    @Autowired
    private ResponseRepositoryInterface responseRepositoryInterface;

    @Transactional
    @Override
    public List<Response> getResponseList() {
        return responseRepositoryInterface.getAll();
    }

    @Transactional
    @Override
    public Response getResponseById(int id) throws ItemNotFoundException {
        Response response = responseRepositoryInterface.findById(id);
        if (response != null) {
            return response;
        } else { throw new ItemNotFoundException("Item Not Found"); }
    }

    @Transactional
    @Override
    public void saveResponse(Response response) {
        responseRepositoryInterface.saveOrUpdate(response);
    }

    @Transactional
    @Override
    public void updateResponse(Response response) {
        responseRepositoryInterface.saveOrUpdate(response);
    }

    @Transactional
    @Override
    public boolean deleteResponseById(int id) throws ItemNotFoundException {
        Response response = responseRepositoryInterface.findById(id);
        if(response != null){
            responseRepositoryInterface.delete(response);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteResponse(Response response) {
        responseRepositoryInterface.delete(response);
    }
}
