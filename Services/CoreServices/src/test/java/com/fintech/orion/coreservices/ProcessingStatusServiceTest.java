package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ProcessingStatusServiceTest extends ObjectCreator {

    private ProcessingStatus processingStatus;
    private List<ProcessingStatus> processingStatuses;
    private final String REPOSITORY_INTERFACE = "processingStatusRepositoryInterface";

    @Before
    public void setup() {
        processingStatuses = new ArrayList<>();
        processingStatuses.add(createProcessingStatusObject());
        processingStatuses.add(createProcessingStatusObject());
        processingStatuses.add(createProcessingStatusObject());
        processingStatus = new ProcessingStatus();
    }

    @Test
    public void should_returnListOfProcesses_when_getProcessingStatusListCalled() throws Exception {
        ProcessingStatusServiceInterface serviceInterface = new ProcessingStatusService();
        ProcessingStatusRepositoryInterface repositoryInterfaceMock = mock(ProcessingStatusRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(processingStatuses);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<ProcessingStatus> found = serviceInterface.getAll();
        assertEquals(3, found.size());
        for(ProcessingStatus p : found){
            assertTrue(processingStatuses.contains(p));
        }
    }

    @Test
    public void should_returnProcessObject_when_getProcessByIdCalled() throws ItemNotFoundException {
        ProcessingStatusServiceInterface serviceInterface = new ProcessingStatusService();
        ProcessingStatusRepositoryInterface repositoryInterfaceMock = mock(ProcessingStatusRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processingStatus);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessingStatus found = serviceInterface.findById(1);
        assertTrue(processingStatuses.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getProcessByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ProcessingStatusServiceInterface serviceInterface = new ProcessingStatusService();
        ProcessingStatusRepositoryInterface repositoryInterfaceMock = mock(ProcessingStatusRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processingStatus);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.findById(2);
    }

    @Test
    public void should_saveProcessObject_when_saveOrUpdateProcessCalled() {
        ProcessingStatusServiceInterface serviceInterface = new ProcessingStatusService();
        ProcessingStatusRepositoryInterface repositoryInterfaceMock = mock(ProcessingStatusRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveOrUpdate(processingStatus);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(processingStatus);
    }

    @Test
    public void should_deleteProcessObject_when_deleteProcessByIdCalled() throws ItemNotFoundException {
        ProcessingStatusServiceInterface serviceInterface = new ProcessingStatusService();
        ProcessingStatusRepositoryInterface repositoryInterfaceMock = mock(ProcessingStatusRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processingStatus);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteProcessById_for_incorrectId() throws ItemNotFoundException {
        ProcessingStatusServiceInterface serviceInterface = new ProcessingStatusService();
        ProcessingStatusRepositoryInterface repositoryInterfaceMock = mock(ProcessingStatusRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processingStatus);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteProcessObject_when_deleteProcessCalled() {
        ProcessingStatusServiceInterface serviceInterface = new ProcessingStatusService();
        ProcessingStatusRepositoryInterface repositoryInterfaceMock = mock(ProcessingStatusRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.delete(processingStatus);
        verify(repositoryInterfaceMock, times(1)).delete(processingStatus);
    }

}
