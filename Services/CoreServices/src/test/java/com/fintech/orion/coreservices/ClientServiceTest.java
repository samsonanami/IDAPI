package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepository;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.mapping.client.ClientMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Client entity service tests
 */
public class ClientServiceTest {

    private final String REPOSITORY_INTERFACE = "clientRepositoryInterface";

    @Test
    public void shouldReturnClientObjectWhenFindByAuthTokenCalled() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);

        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

        Client client = ObjectCreator.createClientObject();
        when(repositoryInterfaceMock.findByAuthToken("token")).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        ReflectionTestUtils.setField(serviceInterface, "clientMapper", clientMapper);

        Object found = serviceInterface.findByAuthToken("token");
        verify(repositoryInterfaceMock, times(1)).findByAuthToken("token");
        assertThat(found, instanceOf(ClientDTO.class));
    }

}
