package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * ProcessingRequest entity service tests
 */
public class ProcessingRequestServiceTest {

    private final String REPOSITORY_INTERFACE = "processingRequestRepositoryInterface";

    @Test
    public void shouldReturnClientObjectWhenFindByAuthTokenCalled() throws ItemNotFoundException {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        ProcessingRequest processingRequest = ObjectCreator.createProcessingRequestObject();
        when(repositoryInterfaceMock.findByIdIdentificationCode("123456")).thenReturn(processingRequest);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessingRequest found = serviceInterface.findByIdIdentificationCode("123456");
        assertTrue(processingRequest.equals(found));
    }

    @Test
    public void shouldSaveObjectWhenSaveCalled() {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        ProcessingRequest processingRequest = serviceInterface.save(ObjectCreator.createClientObject());
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(processingRequest);
    }

}
