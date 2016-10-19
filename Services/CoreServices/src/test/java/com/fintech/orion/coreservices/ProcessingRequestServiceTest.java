package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import com.fintech.orion.dto.processingrequest.ProcessingRequestDTO;
import com.fintech.orion.mapping.client.ClientMapper;
import com.fintech.orion.mapping.processingrequest.ProcessingRequestMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * ProcessingRequest entity service tests
 */
public class ProcessingRequestServiceTest {

    private final String REPOSITORY_INTERFACE = "processingRequestRepositoryInterface";

    @Ignore
    @Test
    public void shouldReturnClientObjectWhenFindByAuthTokenCalled() throws ItemNotFoundException {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        ProcessingRequest processingRequest = ObjectCreator.createProcessingRequestObject();
        when(repositoryInterfaceMock.findByIdIdentificationCode("123456")).thenReturn(processingRequest);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessingRequestMapper processingRequestMapper = Mappers.getMapper(ProcessingRequestMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "processingRequestMapper", processingRequestMapper);

        ProcessingRequestDTO found = serviceInterface.findByIdIdentificationCode("123456");
        assertTrue(processingRequest.equals(found));
    }

    @Ignore
    @Test
    public void shouldSaveObjectWhenSaveCalled() {
        ProcessingRequestServiceInterface serviceInterface = new ProcessingRequestService();
        ProcessingRequestRepositoryInterface repositoryInterfaceMock = mock(ProcessingRequestRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessingRequestMapper processingRequestMapper = Mappers.getMapper(ProcessingRequestMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "processingRequestMapper", processingRequestMapper);

        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "clientMapper", clientMapper);

        ProcessingRequestDTO found = serviceInterface.save(ObjectCreator.createClientDTOObject());
        assertThat(found, instanceOf(ProcessingRequestDTO.class));
    }

}
