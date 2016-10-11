package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepository;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest extends ObjectCreator {

    private final String REPOSITORY_INTERFACE = "clientRepositoryInterface";

    @Test
    public void shouldReturnClientObjectWhenFindByAuthTokenCalled() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        Client client = createClientObject();
        when(repositoryInterfaceMock.findByAuthToken("token")).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        Client found = serviceInterface.findByAuthToken("token");
        assertTrue(client.equals(found));
    }

}
