package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProcessingRequestServiceTest extends ObjectCreator {

    private ProcessingRequest processingRequest;
    private List<ProcessingRequest> processingRequests;
    private final String REPOSITORY_INTERFACE = "processingRequestRepositoryInterface";

    @Before
    public void setup() {
        processingRequests = new ArrayList<>();
        processingRequests.add(createProcessingRequestObject());
        processingRequests.add(createProcessingRequestObject());
        processingRequests.add(createProcessingRequestObject());
        processingRequest = new ProcessingRequest();
    }

    @Test
    public void should_returnListOfProcessingRequests_when_getProcessingRequestListCalled() throws Exception {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(processingRequests);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<ProcessingRequest> found = serviceInterface.getProcessingRequestList();
        assertEquals(3, found.size());
        for(ProcessingRequest p : found){
            assertTrue(processingRequests.contains(p));
        }
    }

    @Test
    public void should_returnProcessingRequestObject_when_getProcessingRequestByIdCalled() throws ItemNotFoundException {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processingRequest);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessingRequest found = serviceInterface.getProcessingRequestById(1);
        assertTrue(processingRequest.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getProcessingRequestByIdCalled_for_incorrect_id() throws ItemNotFoundException {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processingRequest);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getProcessingRequestById(2);
    }

    @Test
    public void should_saveProcessingRequestObject_when_saveOrUpdateProcessingRequestCalled() {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveOrUpdateProcessingRequest(processingRequest);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(processingRequest);
    }

    @Test
    public void should_deleteProcessingRequestObject_when_deleteProcessingRequestByIdCalled() throws ItemNotFoundException {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processingRequest);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteProcessingRequestById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItem_when_deleteProcessingRequestByIdCalled_for_incorrectId() throws ItemNotFoundException {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(processingRequest);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteProcessingRequestById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteProcessingRequestObject_when_deleteProcessingRequestCalled() {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteProcessingRequest(processingRequest);
        verify(repositoryInterfaceMock, times(1)).delete(processingRequest);
    }

}
