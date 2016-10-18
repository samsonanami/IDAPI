package com.fintech.orion.mapping.response;

import com.fintech.orion.dataabstraction.entities.orion.Response;
import com.fintech.orion.dto.response.ResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Response entity mappings
 */
@Mapper(componentModel = "spring")
public interface ResponseMapper {

    ResponseDTO responseToResponseDTO(Response response);

    Response responseDTOToResponse(ResponseDTO responseDTO);

    List<ResponseDTO> responsesToResponseDTOs(List<Response> responses);
    
}
