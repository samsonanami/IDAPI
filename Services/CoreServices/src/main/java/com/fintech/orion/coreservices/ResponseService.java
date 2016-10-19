package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.Response;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.response.ResponseDTO;
import com.fintech.orion.mapping.response.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Response entity service class
 */
@Service
public class ResponseService extends AbstractService<Response, Integer> implements ResponseServiceInterface {

    @Autowired
    private ResponseMapper responseMapper;

    @Transactional
    @Override
    public List<ResponseDTO> getAllDTOs() {
        return responseMapper.responsesToResponseDTOs(getAll());
    }

    @Transactional
    @Override
    public ResponseDTO findById(int id) throws ItemNotFoundException {
        return responseMapper.responseToResponseDTO(findById(new Integer(id)));
    }

    @Transactional
    @Override
    public void saveOrUpdate(ResponseDTO responseDTO) {
        saveOrUpdate(responseMapper.responseDTOToResponse(responseDTO));
    }

    @Transactional
    @Override
    public void delete(ResponseDTO responseDTO) {
        delete(responseMapper.responseDTOToResponse(responseDTO));
    }

}