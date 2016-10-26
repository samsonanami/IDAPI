package com.fintech.orion.mapping.request;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.request.RequestProcessDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by TharinduMP on 10/14/2016.
 * GenericRequestMapper
 */
@Mapper(componentModel = "spring")
@FunctionalInterface
public interface GenericRequestMapper {
    @Mappings({
            @Mapping(source = "processDTO.processTypeDTO.id", target = "processType"),
            @Mapping(source = "processDTO.id", target = "processId"),
            @Mapping(source = "processDTO.processIdentificationCode", target = "identificationCode")
    })
    GenericRequest mapMessageAndRequestProcessToGenericRequest(ProcessDTO processDTO, GenericMapMessage genericMapMessage);
}
