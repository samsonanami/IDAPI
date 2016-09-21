package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceTypeRepository;
import com.fintech.orion.dataabstraction.repositories.ResourceTypeRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResourceTypeServiceTest extends ObjectCreator {

    private ResourceType resourceType;
    private List<ResourceType> resourceTypes;
    private final String REPOSITORY_INTERFACE = "resourceTypeRepositoryInterface";

    @Before
    public void setup() {
        resourceTypes = new ArrayList<>();
        resourceTypes.add(createResourceTypeObject());
        resourceTypes.add(createResourceTypeObject());
        resourceTypes.add(createResourceTypeObject());
        resourceType = new ResourceType();
    }

    @Test
    public void should_returnListOfResourceTypes_when_getResourceTypeListCalled() throws Exception {
        ResourceTypeServiceInterface serviceInterface = new ResourceTypeService();
        ResourceTypeRepositoryInterface repositoryInterfaceMock = mock(ResourceTypeRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(resourceTypes);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<ResourceType> found = serviceInterface.getResourceTypeList();
        assertEquals(3, found.size());
        for(ResourceType r : found){
            assertTrue(resourceTypes.contains(r));
        }
    }

    @Test
    public void should_returnResourceTypeObject_when_getResourceTypeByIdCalled() throws ItemNotFoundException {
        ResourceTypeServiceInterface serviceInterface = new ResourceTypeService();
        ResourceTypeRepositoryInterface repositoryInterfaceMock = mock(ResourceTypeRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resourceType);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ResourceType found = serviceInterface.getResourceTypeById(1);
        assertTrue(resourceType.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getResourceTypeById_for_incorrectId() throws ItemNotFoundException {
        ResourceTypeServiceInterface serviceInterface = new ResourceTypeService();
        ResourceTypeRepositoryInterface repositoryInterfaceMock = mock(ResourceTypeRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resourceType);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getResourceTypeById(2);
    }

    @Test
    public void should_saveResourceTypeObject_when_saveResourceTypeCalled() {
        ResourceTypeServiceInterface serviceInterface = new ResourceTypeService();
        ResourceTypeRepositoryInterface repositoryInterfaceMock = mock(ResourceTypeRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveResourceType(resourceType);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(resourceType);
    }

    @Test
    public void should_updateResourceTypeObject_when_updateResourceTypeCalled() {
        ResourceTypeServiceInterface serviceInterface = new ResourceTypeService();
        ResourceTypeRepositoryInterface repositoryInterfaceMock = mock(ResourceTypeRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.updateResourceType(resourceType);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(resourceType);
    }

    @Test
    public void should_deleteResourceTypeObject_when_deleteResourceTypeByIdCalled() throws ItemNotFoundException {
        ResourceTypeServiceInterface serviceInterface = new ResourceTypeService();
        ResourceTypeRepositoryInterface repositoryInterfaceMock = mock(ResourceTypeRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resourceType);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteResourceTypeById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteResourceTypeByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ResourceTypeServiceInterface serviceInterface = new ResourceTypeService();
        ResourceTypeRepositoryInterface repositoryInterfaceMock = mock(ResourceTypeRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(resourceType);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteResourceTypeById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteResourceTypeObject_when_deleteResourceTypeCalled() {
        ResourceTypeServiceInterface serviceInterface = new ResourceTypeService();
        ResourceTypeRepositoryInterface repositoryInterfaceMock = mock(ResourceTypeRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteResourceType(resourceType);
        verify(repositoryInterfaceMock, times(1)).delete(resourceType);
    }

}
