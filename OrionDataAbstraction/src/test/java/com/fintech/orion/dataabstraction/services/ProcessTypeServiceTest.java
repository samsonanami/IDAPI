package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepositoryInterface;
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

public class ProcessTypeServiceTest extends ObjectCreator {

    private ProcessType processType;
    private List<ProcessType> processTypes;

    @Before
    public void setup() {
        processTypes = new ArrayList<>();
        processTypes.add(createProcessTypeObject());
        processTypes.add(createProcessTypeObject());
        processTypes.add(createProcessTypeObject());
        processType = new ProcessType();
    }

    @Test
    public void should_returnListOfProcessTypes_when_getProcessTypeListCalled() throws Exception {
        ProcessTypeServiceInterface serviceInterface = new ProcessTypeService();
        ProcessTypeRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(processTypes);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<ProcessType> found = serviceInterface.getProcessTypeList();
        assertEquals(3, found.size());
        for(ProcessType p : found){
            assertTrue(processTypes.contains(p));
        }
    }

    @Test
    public void should_returnProcessTypeObject_when_getProcessTypeByIdCalled() throws ItemNotFoundException {
        ProcessTypeServiceInterface serviceInterface = new ProcessTypeService();
        ProcessTypeRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processType);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessType found = serviceInterface.getProcessTypeById(1);
        assertTrue(processType.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getProcessTypeByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ProcessTypeServiceInterface serviceInterface = new ProcessTypeService();
        ProcessTypeRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processType);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getProcessTypeById(2);
    }

    @Test
    public void should_saveProcessTypeObject_when_saveProcessTypeCalled() {
        ProcessTypeServiceInterface serviceInterface = new ProcessTypeService();
        ProcessTypeRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveProcessType(processType);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(processType);
    }

    @Test
    public void should_updateProcessTypeObject_when_updateProcessTypeCalled() {
        ProcessTypeServiceInterface serviceInterface = new ProcessTypeService();
        ProcessTypeRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.updateProcessType(processType);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(processType);
    }

    @Test
    public void should_deleteProcessTypeObject_when_deleteProcessTypeByIdCalled() throws ItemNotFoundException {
        ProcessTypeServiceInterface serviceInterface = new ProcessTypeService();
        ProcessTypeRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processType);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteProcessTypeById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteProcessTypeByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ProcessTypeServiceInterface serviceInterface = new ProcessTypeService();
        ProcessTypeRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processType);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteProcessTypeById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteProcessTypeObject_when_deleteProcessTypeCalled() {
        ProcessTypeServiceInterface serviceInterface = new ProcessTypeService();
        ProcessTypeRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteProcessType(processType);
        verify(repositoryInterfaceMock, times(1)).delete(processType);
    }

}
