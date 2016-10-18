package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepositoryInterface;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ProcessingStatus entity service tests
 */
public class ProcessingStatusServiceTest {

    @Test
    public void shouldReturnStatusObjectWhenFindByStatusCalled() throws ItemNotFoundException {
        ProcessingStatusServiceInterface serviceInterface = new ProcessingStatusService();
        ProcessingStatusRepositoryInterface repositoryInterfaceMock = mock(ProcessingStatusRepository.class);
        ProcessingStatus processingStatus = ObjectCreator.createProcessingStatusObject();
        when(repositoryInterfaceMock.findByStatus(Status.PROCESSING_COMPLETE)).thenReturn(processingStatus);
        ReflectionTestUtils.setField(serviceInterface, "processingStatusRepositoryInterface", repositoryInterfaceMock);

        ProcessingStatus found = serviceInterface.findByStatus(Status.PROCESSING_COMPLETE);
        assertTrue(processingStatus.equals(found));
    }

}
