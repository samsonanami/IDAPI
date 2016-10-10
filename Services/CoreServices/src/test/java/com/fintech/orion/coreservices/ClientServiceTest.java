package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepository;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest extends ObjectCreator {

    private Client client;
    private List<Client> clients;
    private final String REPOSITORY_INTERFACE = "clientRepositoryInterface";

    @Before
    public void setup() {
        clients = new ArrayList<>();
        clients.add(createClientObject());
        clients.add(createClientObject());
        clients.add(createClientObject());
        client = new Client();
    }

    @Test
    public void should_returnListOfClients_when_getClientListCalled() throws Exception {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(clients);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<Client> found = serviceInterface.getAll();
        assertEquals(3, found.size());
        for(Client c : found){
            assertTrue(clients.contains(c));
        }
    }

    @Test
    public void should_returnClientObject_when_getClientByAuthTokenCalled() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        when(repositoryInterfaceMock.findByAuthToken("token")).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        Client found = serviceInterface.findByAuthToken("token");
        assertTrue(client.equals(found));
    }

    @Test
    public void should_returnClientObject_when_getClientByIdCalled() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        Client found = serviceInterface.findById(1);
        assertTrue(client.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getClientByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.findById(2);
    }

    @Test
    public void should_saveClientObject_when_saveOrUpdateClientCalled() {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveOrUpdate(client);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(client);
    }

    @Test
    public void should_deleteClientObject_when_deleteClientByIdCalled() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteClientByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteClientObject_when_deleteClientCalled() {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.delete(client);
        verify(repositoryInterfaceMock, times(1)).delete(client);
    }

}
