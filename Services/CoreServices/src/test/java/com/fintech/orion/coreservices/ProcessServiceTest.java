package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.repositories.ProcessRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import static org.mockito.Mockito.*;

public class ProcessServiceTest extends ObjectCreator {

    private final String REPOSITORY_INTERFACE = "processRepositoryInterface";

    @Test
    public void shouldSaveObjectWhenSaveCalled() {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        Process process = serviceInterface.save(createProcessTypeObject(), createProcessingRequestObject(), createProcessingStatusObject());
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(process);
    }

}
