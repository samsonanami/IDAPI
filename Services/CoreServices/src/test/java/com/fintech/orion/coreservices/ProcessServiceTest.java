package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class ProcessServiceTest extends ObjectCreator {

    private Process process;
    private List<Process> processes;
    private final String REPOSITORY_INTERFACE = "processRepositoryInterface";

    @Before
    public void setup() {
        processes = new ArrayList<>();
        processes.add(createProcessObject());
        processes.add(createProcessObject());
        processes.add(createProcessObject());
        process = new Process();
    }

    @Test
    public void should_returnListOfProcesses_when_getProcessListCalled() throws Exception {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(processes);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<Process> found = serviceInterface.getProcessList();
        assertEquals(3, found.size());
        for(Process p : found){
            assertTrue(processes.contains(p));
        }
    }

    @Test
    public void should_returnProcessObject_when_getProcessByIdCalled() throws ItemNotFoundException {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(process);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        Process found = serviceInterface.getProcessById(1);
        assertTrue(process.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getProcessByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(process);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getProcessById(2);
    }

    @Test
    public void should_saveProcessObject_when_saveProcessCalled() {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveProcess(process);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(process);
    }

    @Test
    public void should_updateProcessObject_when_updateProcessCalled() {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.updateProcess(process);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(process);
    }

    @Test
    public void should_deleteProcessObject_when_deleteProcessByIdCalled() throws ItemNotFoundException {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(process);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteProcessById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteProcessById_for_incorrectId() throws ItemNotFoundException {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(process);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteProcessById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteProcessObject_when_deleteProcessCalled() {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteProcess(process);
        verify(repositoryInterfaceMock, times(1)).delete(process);
    }

}
