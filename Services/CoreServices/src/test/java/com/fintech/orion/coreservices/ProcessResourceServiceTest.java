package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepositoryInterface;
import com.fintech.orion.mapping.process.ProcessMapper;
import com.fintech.orion.mapping.resource.ResourceMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;
import static org.mockito.Mockito.*;

/**
 * ProcessResource entity service tests
 */
public class ProcessResourceServiceTest  {

    private final String REPOSITORY_INTERFACE = "processResourceRepositoryInterface";

    @Test
    public void shouldSaveObjectWhenSaveCalled() {
        ProcessResourceServiceInterface serviceInterface = new ProcessResourceService();
        ProcessResourceRepositoryInterface repositoryInterfaceMock = mock(ProcessResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessMapper processMapper = Mappers.getMapper(ProcessMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "processMapper", processMapper);

        ResourceMapper resourceMapper = Mappers.getMapper(ResourceMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "resourceMapper", resourceMapper);

        ProcessResource processResource = serviceInterface.save(ObjectCreator.createProcessDTOObject(), ObjectCreator.createResourceDTOObject(), "resourceName");
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(processResource);
    }

}

