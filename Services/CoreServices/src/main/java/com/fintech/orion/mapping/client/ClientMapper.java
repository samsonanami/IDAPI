package com.fintech.orion.mapping.client;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dto.client.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "authToken", target = "authToken"),
            @Mapping(source = "registeredOn", target = "registeredOn"),
            @Mapping(source = "refreshToken", target = "refreshToken"),
            @Mapping(source = "ennabled", target = "ennabled")
    })
    ClientDTO clientToClientDTO(Client client);

}
