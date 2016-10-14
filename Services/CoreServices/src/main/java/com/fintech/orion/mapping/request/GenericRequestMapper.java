package com.fintech.orion.mapping.request;

import com.fintech.orion.dto.messaging.GenericMapMessage;
import com.fintech.orion.dto.request.GenericRequest;
import com.fintech.orion.dto.request.RequestProcessDTO;
import org.mapstruct.Mapper;

/**
 * Created by TharinduMP on 10/14/2016.
 * GenericRequestMapper
 */
@Mapper(componentModel = "spring")
@FunctionalInterface
public interface GenericRequestMapper {
    GenericRequest mapMessageAndRequestProcessToGenericRequest(RequestProcessDTO requestProcessDTO, GenericMapMessage genericMapMessage);
}
