package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientRepository;
import com.fintech.orion.dataabstraction.repositories.ClientRepositoryInterface;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.springframework.test.util.ReflectionTestUtils;

public class ClientServiceTest extends ObjectCreator {

    private Client client;
    private List<Client> clients;

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

        List<Client> found = serviceInterface.getClientList();
        assertEquals(3, found.size());
        for(Client c : found){
            assertTrue(clients.contains(c));
        }
    }

    @Test
    public void should_returnClientObject_when_getClientByIdCalled() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        Client found = serviceInterface.getClientById(1);
        assertTrue(client.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getClientByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getClientById(2);
    }

    @Test
    public void should_saveClientObject_when_saveClientCalled() {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveClient(client);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(client);
    }

    @Test
    public void should_updateClientObject_when_updateClientCalled() {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.updateClient(client);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(client);
    }

    @Test
    public void should_deleteClientObject_when_deleteClientByIdCalled() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteClientById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteClientByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteClientById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteClientObject_when_deleteClientCalled() {
        ClientServiceInterface serviceInterface = new ClientService();
        ClientRepositoryInterface repositoryInterfaceMock = mock(ClientRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteClient(client);
        verify(repositoryInterfaceMock, times(1)).delete(client);
    }

}
