package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceRepository;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResourceServiceTest extends ObjectCreator {

    private Resource resource;
    private List<Resource> resources;
    private final String REPOSITORY_INTERFACE = "resourceRepositoryInterface";

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

        List<Resource> found = serviceInterface.getAll();
        assertEquals(3, found.size());
        for(Resource r : found){
            assertTrue(resources.contains(r));
        }
    }

    @Test
    public void should_saveResourceObject_when_saveResource1Called() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();

        Client client = createClientObject();
        ResourceType resourceType = createResourceTypeObject();

        ClientServiceInterface clientService = mock(ClientService.class);
        when(clientService.findByAuthToken("123456")).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, "clientServiceInterface", clientService);

        ResourceTypeServiceInterface resourceTypeService = mock(ResourceTypeService.class);
        when(resourceTypeService.findByType("image")).thenReturn(resourceType);
        ReflectionTestUtils.setField(serviceInterface, "resourceTypeServiceInterface", resourceTypeService);

        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveOrUpdate(resource);

        serviceInterface.save("12345abcde.jpg", "12345abcde", "image", "123456");
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(resource);
    }

    @Test
    public void should_returnResourceObject_when_getResourceByIdCalled() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        Resource found = serviceInterface.findById(1);
        assertTrue(resource.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getResourceByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.findById(2);
    }

    @Test
    public void should_saveResourceObject_when_saveOrUpdateResourceCalled() {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveOrUpdate(resource);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(resource);
    }

    @Test
    public void should_deleteResourceObject_when_deleteResourceByIdCalled() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteResourceByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteResourceObject_when_deleteResourceCalled() {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.delete(resource);
        verify(repositoryInterfaceMock, times(1)).delete(resource);
    }

}
