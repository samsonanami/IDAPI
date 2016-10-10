package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class ProcessResourceServiceTest extends ObjectCreator {

    private ProcessResource processResource;
    private List<ProcessResource> processResources;
    private final String REPOSITORY_INTERFACE = "processResourceRepositoryInterface";

    @Before
    public void setup() {
        processResources = new ArrayList<>();
        processResources.add(createProcessResourceObject());
        processResources.add(createProcessResourceObject());
        processResources.add(createProcessResourceObject());
        processResource = new ProcessResource();
    }

    @Test
    public void should_returnTrueFor5MBJpegImage_when_checkSizeCalled() throws Exception {
        ProcessResourceServiceInterface serviceInterface = new ProcessResourceService();
        ProcessResourceRepositoryInterface repositoryInterfaceMock = mock(ProcessResourceRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(processResources);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<ProcessResource> found = serviceInterface.getProcessResourceList();
        assertEquals(3, found.size());
        for(ProcessResource p : found){
            assertTrue(processResources.contains(p));
        }
    }

    @Test
    public void should_returnProcessResourceObject_when_getProcessResourceByIdCalled() throws ItemNotFoundException {
        ProcessResourceServiceInterface serviceInterface = new ProcessResourceService();
        ProcessResourceRepositoryInterface repositoryInterfaceMock = mock(ProcessResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processResource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessResource found = serviceInterface.getProcessResourceById(1);
        assertTrue(processResource.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getProcessResourceByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ProcessResourceServiceInterface serviceInterface = new ProcessResourceService();
        ProcessResourceRepositoryInterface repositoryInterfaceMock = mock(ProcessResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processResource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getProcessResourceById(2);
    }

    @Test
    public void should_saveProcessResourceObject_when_saveOrUpdateProcessResourceCalled() {
        ProcessResourceServiceInterface serviceInterface = new ProcessResourceService();
        ProcessResourceRepositoryInterface repositoryInterfaceMock = mock(ProcessResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveOrUpdateProcessResource(processResource);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(processResource);
    }

    @Test
    public void should_deleteProcessResourceObject_when_deleteProcessResourceByIdCalled() throws ItemNotFoundException {
        ProcessResourceServiceInterface serviceInterface = new ProcessResourceService();
        ProcessResourceRepositoryInterface repositoryInterfaceMock = mock(ProcessResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processResource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteProcessResourceById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteProcessResourceById_for_incorrectId() throws ItemNotFoundException {
        ProcessResourceServiceInterface serviceInterface = new ProcessResourceService();
        ProcessResourceRepositoryInterface repositoryInterfaceMock = mock(ProcessResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processResource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteProcessResourceById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteProcessResourceObject_when_deleteProcessResourceCalled() {
        ProcessResourceServiceInterface serviceInterface = new ProcessResourceService();
        ProcessResourceRepositoryInterface repositoryInterfaceMock = mock(ProcessResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteProcessResource(processResource);
        verify(repositoryInterfaceMock, times(1)).delete(processResource);
    }
    
}

