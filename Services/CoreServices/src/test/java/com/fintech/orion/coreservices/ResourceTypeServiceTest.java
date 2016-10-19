package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.repositories.ResourceTypeRepository;
import com.fintech.orion.dataabstraction.repositories.ResourceTypeRepositoryInterface;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * ResourceType entity service tests
 */
public class ResourceTypeServiceTest {

    private final String REPOSITORY_INTERFACE = "resourceTypeRepositoryInterface";

    @Test
    public void shouldReturnResourceTypeObjectFindByTypeCalled() throws Exception {
        ResourceTypeServiceInterface serviceInterface = new ResourceTypeService();
        ResourceTypeRepositoryInterface repositoryInterfaceMock = mock(ResourceTypeRepository.class);

        ResourceType resource = ObjectCreator.createResourceTypeObject();

        when(repositoryInterfaceMock.findByType("type")).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ResourceType found = serviceInterface.findByType("type");
        assertTrue(resource.equals(found));
    }

}
