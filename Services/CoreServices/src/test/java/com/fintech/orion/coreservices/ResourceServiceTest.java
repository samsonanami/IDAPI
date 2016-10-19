package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.entities.orion.ResourceType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceRepository;
import com.fintech.orion.dataabstraction.repositories.ResourceRepositoryInterface;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.resource.ResourceDTO;
import com.fintech.orion.mapping.client.ClientMapper;
import com.fintech.orion.mapping.resource.ResourceMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Resource entity service tests
 */
public class ResourceServiceTest {

    private final String REPOSITORY_INTERFACE = "resourceRepositoryInterface";

    @Test
    public void shouldReturnResourceObjectFindByIdentificationCodeCalled() throws Exception {
        ResourceServiceInterface serviceInterface = new ResourceService();
        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);

        Resource resource = ObjectCreator.createResourceObject();

        when(repositoryInterfaceMock.findByIdentificationCode("code")).thenReturn(resource);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ResourceMapper resourceMapper = Mappers.getMapper(ResourceMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "resourceMapper", resourceMapper);

        Object found = serviceInterface.findByIdentificationCode("code");
        assertThat(found, instanceOf(ResourceDTO.class));
    }

    @Test
    public void shouldSaveObjectWhenSaveCalled() throws ItemNotFoundException {
        ResourceServiceInterface serviceInterface = new ResourceService();

        ClientDTO clientDTO = ObjectCreator.createClientDTOObject();
        ResourceType resourceType = ObjectCreator.createResourceTypeObject();

        ClientServiceInterface clientService = mock(ClientService.class);
        when(clientService.findByAuthToken("123456")).thenReturn(clientDTO);
        ReflectionTestUtils.setField(serviceInterface, "clientServiceInterface", clientService);

        ResourceTypeServiceInterface resourceTypeService = mock(ResourceTypeService.class);
        when(resourceTypeService.findByType("image")).thenReturn(resourceType);
        ReflectionTestUtils.setField(serviceInterface, "resourceTypeServiceInterface", resourceTypeService);

        ResourceRepositoryInterface repositoryInterfaceMock = mock(ResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "clientMapper", clientMapper);

        ResourceMapper resourceMapper = Mappers.getMapper(ResourceMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "resourceMapper", resourceMapper);

        Object found = serviceInterface.save("12345abcde.jpg", "12345abcde", "image", "123456");
        assertThat(found, instanceOf(ResourceDTO.class));
    }

}
