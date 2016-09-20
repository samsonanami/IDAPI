package com.fintech.orion.dataabstraction.services;

import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceRepository;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
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

public class ResourceServiceTest extends ObjectCreator {

    private Resource resource;
    private List<Resource> resources;

    @Before
    public void setup() {
        resources = new ArrayList<>();
        resources.add(createResourceObject());
        resources.add(createResourceObject());
        resources.add(createResourceObject());
        resource = new Resource();
    }

    @Test
    public void should_returnListOfResources_when_getResourceListCalled() throws Exception {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(resources);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<Resource> found = serviceInterface.getResourceList();
        assertEquals(3, found.size());
        for(Resource r : found){
            assertTrue(resources.contains(r));
        }
    }

    @Test
    public void should_returnResourceObject_when_getResourceByIdCalled() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        Resource found = serviceInterface.getResourceById(1);
        assertTrue(resource.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getResourceByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getResourceById(2);
    }

    @Test
    public void should_saveResourceObject_when_saveResourceCalled() {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveResource(resource);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(resource);
    }

    @Test
    public void should_updateResourceObject_when_updateResourceCalled() {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.updateResource(resource);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(resource);
    }

    @Test
    public void should_deleteResourceObject_when_deleteResourceByIdCalled() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteResourceById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteResourceByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteResourceById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteResourceObject_when_deleteResourceCalled() {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteResource(resource);
        verify(repositoryInterfaceMock, times(1)).delete(resource);
    }

}
