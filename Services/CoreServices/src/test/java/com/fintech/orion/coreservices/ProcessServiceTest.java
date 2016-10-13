package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ProcessServiceTest {

    private final String REPOSITORY_INTERFACE = "processRepositoryInterface";

    @Test
    public void shouldSaveObjectWhenSaveCalled() {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        Process process = serviceInterface.save(ObjectCreator.createProcessTypeObject(), ObjectCreator.createProcessingRequestObject(), ObjectCreator.createProcessingStatusObject());
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(process);
    }

    @Test
    public void shouldProcessObjectWhenFindByIdentificationCodeCalled() throws ItemNotFoundException {
        ProcessServiceInterface serviceInterface = new ProcessService();
        String identificationCode = "12345";
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        Process process = ObjectCreator.createProcessObject();
        when(repositoryInterfaceMock.findByIdentificationCode(identificationCode)).thenReturn(process);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        Object found = serviceInterface.findByIdentificationCode(identificationCode);
        assertThat(found, instanceOf(Process.class));
    }

}
