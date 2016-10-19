package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Response;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.response.ResponseDTO;

import java.util.List;

/**
 * Response entity service interface
 */
public interface ResponseServiceInterface extends ServiceInterface<Response, Integer> {

    List<ResponseDTO> getAllDTOs();

    ResponseDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ResponseDTO responseDTO);

    void delete(ResponseDTO responseDTO);

}
