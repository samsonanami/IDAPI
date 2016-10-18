package com.fintech.orion.mapping.client;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.mapping.ObjectCreator;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static junit.framework.TestCase.assertEquals;

/**
 * Client entity mapper tests
 */
public class ClientMapperTest {

    @Test
    public void shouldMapClientToClientDTO(){
        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

        ClientDTO clientDTO = ObjectCreator.aClientDTO();
        Client client = clientMapper.clientDTOToClient(clientDTO);

        assertEquals(client.getId(), clientDTO.getId());
        assertEquals(client.getAuthToken(), clientDTO.getAuthToken());
    }

    @Test
    public void shouldMapClientDTOToClient(){
        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

        Client client = ObjectCreator.aClient();
        ClientDTO clientDTO = clientMapper.clientToClientDTO(client);

        assertEquals(client.getId(), clientDTO.getId());
        assertEquals(client.getAuthToken(), clientDTO.getAuthToken());
    }

}
