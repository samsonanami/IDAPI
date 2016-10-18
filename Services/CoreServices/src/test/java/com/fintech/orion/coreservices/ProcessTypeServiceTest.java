package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepositoryInterface;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

/**
 * ProcessType entity service tests
 */
public class ProcessTypeServiceTest {

    private final String REPOSITORY_INTERFACE = "processTypeRepositoryInterface";

    @Test
    public void shouldReturnClientObjectWhenFindByAuthTokenCalled() throws ItemNotFoundException {
        ProcessTypeServiceInterface serviceInterface = new ProcessTypeService();
        ProcessTypeRepositoryInterface repositoryInterfaceMock = mock(ProcessTypeRepository.class);
        ProcessType processType = ObjectCreator.createProcessTypeObject();
        when(repositoryInterfaceMock.findByType("type")).thenReturn(processType);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessType found = serviceInterface.findByType("type");
        assertTrue(processType.equals(found));
    }
}
