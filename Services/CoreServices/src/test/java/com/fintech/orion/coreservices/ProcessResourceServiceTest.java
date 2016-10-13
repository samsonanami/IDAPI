package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessResource;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessResourceRepositoryInterface;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import static org.mockito.Mockito.*;

public class ProcessResourceServiceTest  {

    private final String REPOSITORY_INTERFACE = "processResourceRepositoryInterface";

    @Test
    public void shouldSaveObjectWhenSaveCalled() {
        ProcessResourceServiceInterface serviceInterface = new ProcessResourceService();
        ProcessResourceRepositoryInterface repositoryInterfaceMock = mock(ProcessResourceRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        ProcessResource processResource = serviceInterface.save(ObjectCreator.createProcessObject(), ObjectCreator.createResourceObject(), "resourceName");
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(processResource);
    }

}

