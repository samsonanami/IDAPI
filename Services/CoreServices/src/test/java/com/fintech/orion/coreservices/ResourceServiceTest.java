package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceRepository;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResourceServiceTest {

    private final String REPOSITORY_INTERFACE = "resourceRepositoryInterface";

    @Test
    public void shouldReturnResourceObjectFindByIdentificationCodeCalled() throws Exception {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);

        Resource resource = ObjectCreator.createResourceObject();

        when(repositoryInterfaceMock.findByIdentificationCode("code")).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        Resource found = serviceInterface.findByIdentificationCode("code");
        assertTrue(resource.equals(found));
    }

    @Test
    public void shouldSaveObjectWhenSaveCalled() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();

        Client client = ObjectCreator.createClientObject();
        ResourceType resourceType = ObjectCreator.createResourceTypeObject();

        ClientServiceInterface clientService = mock(ClientService.class);
        when(clientService.findByAuthToken("123456")).thenReturn(client);
        ReflectionTestUtils.setField(serviceInterface, "clientServiceInterface", clientService);

        ResourceTypeServiceInterface resourceTypeService = mock(ResourceTypeService.class);
        when(resourceTypeService.findByType("image")).thenReturn(resourceType);
        ReflectionTestUtils.setField(serviceInterface, "resourceTypeServiceInterface", resourceTypeService);

        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        Resource resource = serviceInterface.save("12345abcde.jpg", "12345abcde", "image", "123456");
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(resource);
    }

}
