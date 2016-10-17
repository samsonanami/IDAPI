package com.fintech.orion.mapping.client;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dto.client.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO clientToClientDTO(Client client);

    Client clientDTOToClient(ClientDTO clientDTO);
}
