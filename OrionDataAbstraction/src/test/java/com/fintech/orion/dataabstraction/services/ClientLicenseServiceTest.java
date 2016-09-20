package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ClientLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ClientLicenseRepository;
import com.fintech.orion.dataabstraction.repositories.ClientLicenseRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ClientLicenseServiceTest extends ObjectCreator {

    private ClientLicense clientLicense;
    private List<ClientLicense> clientLicenses;

    @Before
    public void setup() {
        clientLicenses = new ArrayList<>();
        clientLicenses.add(createClientLicenseObject());
        clientLicenses.add(createClientLicenseObject());
        clientLicenses.add(createClientLicenseObject());
        clientLicense = new ClientLicense();
    }

    @Test
    public void should_returnListOfClientLicenses_when_getClientLicenseListCalled() throws Exception {
        ClientLicenseServiceInterface serviceInterface = new ClientLicenseService();

        ClientLicenseRepositoryInterface repositoryInterfaceMock = mock(ClientLicenseRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(clientLicenses);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<ClientLicense> found = serviceInterface.getClientLicenseList();
        assertEquals(3, found.size());
        for(ClientLicense c : found){
            assertTrue(clientLicenses.contains(c));
        }
    }

    @Test
    public void should_returnClientLicenseObject_when_getClientLicenseByIdCalled() throws ItemNotFoundException {
        ClientLicenseServiceInterface serviceInterface = new ClientLicenseService();
        ClientLicenseRepositoryInterface repositoryInterfaceMock = mock(ClientLicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(clientLicense);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ClientLicense found = serviceInterface.getClientLicenseById(1);
        assertTrue(clientLicense.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getClientLicenseByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ClientLicenseServiceInterface serviceInterface = new ClientLicenseService();
        ClientLicenseRepositoryInterface repositoryInterfaceMock = mock(ClientLicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(clientLicense);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getClientLicenseById(2);
    }

    @Test
    public void should_saveClientLicenseObject_when_saveClientLicenseCalled() {
        ClientLicenseServiceInterface serviceInterface = new ClientLicenseService();
        ClientLicenseRepositoryInterface repositoryInterfaceMock = mock(ClientLicenseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveClientLicense(clientLicense);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(clientLicense);
    }

    @Test
    public void should_updateClientLicenseObject_when_updateClientLicenseCalled() {
        ClientLicenseServiceInterface serviceInterface = new ClientLicenseService();
        ClientLicenseRepositoryInterface repositoryInterfaceMock = mock(ClientLicenseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.updateClientLicense(clientLicense);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(clientLicense);
    }

    @Test
    public void should_deleteClientLicenseObject_when_deleteClientLicenseByIdCalled() throws ItemNotFoundException {
        ClientLicenseServiceInterface serviceInterface = new ClientLicenseService();
        ClientLicenseRepositoryInterface repositoryInterfaceMock = mock(ClientLicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(clientLicense);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteClientLicenseById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteClientLicenseByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ClientLicenseServiceInterface serviceInterface = new ClientLicenseService();
        ClientLicenseRepositoryInterface repositoryInterfaceMock = mock(ClientLicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(clientLicense);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteClientLicenseById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteClientLicenseObject_when_deleteClientLicenseCalled() {
        ClientLicenseServiceInterface serviceInterface = new ClientLicenseService();
        ClientLicenseRepositoryInterface repositoryInterfaceMock = mock(ClientLicenseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteClientLicense(clientLicense);
        verify(repositoryInterfaceMock, times(1)).delete(clientLicense);
    }

}
