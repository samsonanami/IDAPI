package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeLicenseRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeLicenseRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ProcessTypeLicenseServiceTest extends ObjectCreator {

    private ProcessTypeLicense processTypeLicense;
    private List<ProcessTypeLicense> processTypeLicenses;

    @Before
    public void setup() {
        processTypeLicenses = new ArrayList<>();
        processTypeLicenses.add(createProcessTypeLicenseObject());
        processTypeLicenses.add(createProcessTypeLicenseObject());
        processTypeLicenses.add(createProcessTypeLicenseObject());
        processTypeLicense = new ProcessTypeLicense();
    }

    @Test
    public void should_returnListOfProcessTypeLicenses_when_getProcessTypeLicenseListCalled() throws Exception {
        ProcessTypeLicenseServiceInterface serviceInterface = new ProcessTypeLicenseService();
        ProcessTypeLicenseRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeLicenseRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(processTypeLicenses);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<ProcessTypeLicense> found = serviceInterface.getProcessTypeLicenseList();
        assertEquals(3, found.size());
        for(ProcessTypeLicense p : found){
            assertTrue(processTypeLicenses.contains(p));
        }
    }

    @Test
    public void should_returnProcessTypeLicenseObject_when_getProcessTypeLicenseByIdCalled() throws ItemNotFoundException {
        ProcessTypeLicenseServiceInterface serviceInterface = new ProcessTypeLicenseService();
        ProcessTypeLicenseRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeLicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processTypeLicense);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessTypeLicense found = serviceInterface.getProcessTypeLicenseById(1);
        assertTrue(processTypeLicense.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getProcessTypeLicenseByIdCalled_for_incorrect_id() throws ItemNotFoundException {
        ProcessTypeLicenseServiceInterface serviceInterface = new ProcessTypeLicenseService();
        ProcessTypeLicenseRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeLicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processTypeLicense);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getProcessTypeLicenseById(2);
    }

    @Test
    public void should_saveProcessTypeLicenseObject_when_saveProcessTypeLicenseCalled() {
        ProcessTypeLicenseServiceInterface serviceInterface = new ProcessTypeLicenseService();
        ProcessTypeLicenseRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeLicenseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveProcessTypeLicense(processTypeLicense);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(processTypeLicense);
    }

    @Test
    public void should_updateProcessTypeLicenseObject_when_updateProcessTypeLicenseCalled() {
        ProcessTypeLicenseServiceInterface serviceInterface = new ProcessTypeLicenseService();
        ProcessTypeLicenseRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeLicenseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.updateProcessTypeLicense(processTypeLicense);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(processTypeLicense);
    }

    @Test
    public void should_deleteProcessTypeLicenseObject_when_deleteProcessTypeLicenseByIdCalled() throws ItemNotFoundException {
        ProcessTypeLicenseServiceInterface serviceInterface = new ProcessTypeLicenseService();
        ProcessTypeLicenseRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeLicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processTypeLicense);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteProcessTypeLicenseById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteProcessTypeLicenseByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ProcessTypeLicenseServiceInterface serviceInterface = new ProcessTypeLicenseService();
        ProcessTypeLicenseRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeLicenseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processTypeLicense);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteProcessTypeLicenseById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteProcessTypeLicenseObject_when_deleteProcessTypeLicenseCalled() {
        ProcessTypeLicenseServiceInterface serviceInterface = new ProcessTypeLicenseService();
        ProcessTypeLicenseRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeLicenseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteProcessTypeLicense(processTypeLicense);
        verify(repositoryInterfaceMock, times(1)).delete(processTypeLicense);
    }

}
