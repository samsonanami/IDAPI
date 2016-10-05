package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Response;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResponseRepository;
import com.fintech.orion.dataabstraction.repositories.ResponseRepositoryInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResponseServiceTest extends ObjectCreator {

    private Response response;
    private List<Response> responses;
    private final String REPOSITORY_INTERFACE = "responseRepositoryInterface";

    @Before
    public void setup() {
        responses = new ArrayList<>();
        responses.add(createResponseObject());
        responses.add(createResponseObject());
        responses.add(createResponseObject());
        response = new Response();
    }

    @Test
    public void should_returnListOfResponses_when_getResponseListCalled() throws Exception {
        ResponseServiceInterface serviceInterface = new ResponseService();
        ResponseRepositoryInterface repositoryInterfaceMock = mock(ResponseRepository.class);
        when(repositoryInterfaceMock.getAll()).thenReturn(responses);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        List<Response> found = serviceInterface.getResponseList();
        assertEquals(3, found.size());
        for(Response r : found){
            assertTrue(responses.contains(r));
        }
    }

    @Test
    public void should_returnResponseObject_when_getResponseByIdCalled() throws ItemNotFoundException {
        ResponseServiceInterface serviceInterface = new ResponseService();
        ResponseRepositoryInterface repositoryInterfaceMock = mock(ResponseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(response);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        Response found = serviceInterface.getResponseById(1);
        assertTrue(response.equals(found));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_returnItemNotFoundException_when_getResponseById_for_incorrectId() throws ItemNotFoundException {
        ResponseServiceInterface serviceInterface = new ResponseService();
        ResponseRepositoryInterface repositoryInterfaceMock = mock(ResponseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(response);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.getResponseById(2);
    }

    @Test
    public void should_saveResponseObject_when_saveResponseCalled() {
        ResponseServiceInterface serviceInterface = new ResponseService();
        ResponseRepositoryInterface repositoryInterfaceMock = mock(ResponseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.saveResponse(response);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(response);
    }

    @Test
    public void should_updateResponseObject_when_updateResponseCalled() {
        ResponseServiceInterface serviceInterface = new ResponseService();
        ResponseRepositoryInterface repositoryInterfaceMock = mock(ResponseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);
        serviceInterface.updateResponse(response);
        verify(repositoryInterfaceMock, times(1)).saveOrUpdate(response);
    }

    @Test
    public void should_deleteResponseObject_when_deleteResponseByIdCalled() throws ItemNotFoundException {
        ResponseServiceInterface serviceInterface = new ResponseService();
        ResponseRepositoryInterface repositoryInterfaceMock = mock(ResponseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(response);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteResponseById(1);
        assertTrue(found);
    }

    @Test
    public void should_notDeleteItemNotFound_when_deleteResponseByIdCalled_for_incorrect_id() throws ItemNotFoundException {
        ResponseServiceInterface serviceInterface = new ResponseService();
        ResponseRepositoryInterface repositoryInterfaceMock = mock(ResponseRepository.class);
        when(repositoryInterfaceMock.findById(1)).thenReturn(response);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        boolean found = serviceInterface.deleteResponseById(2);
        assertFalse(found);
    }

    @Test
    public void should_deleteResponseObject_when_deleteResponseCalled() {
        ResponseServiceInterface serviceInterface = new ResponseService();
        ResponseRepositoryInterface repositoryInterfaceMock = mock(ResponseRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        serviceInterface.deleteResponse(response);
        verify(repositoryInterfaceMock, times(1)).delete(response);
    }

}
