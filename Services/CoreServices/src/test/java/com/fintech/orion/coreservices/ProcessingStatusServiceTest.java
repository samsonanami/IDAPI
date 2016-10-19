package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessingStatus;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.Status;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessingStatusRepositoryInterface;
import com.fintech.orion.dto.processingstatus.ProcessingStatusDTO;
import com.fintech.orion.mapping.processingstatus.ProcessingStatusMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
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

        ProcessingStatusMapper processingStatusMapper = Mappers.getMapper(ProcessingStatusMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "processingStatusMapper", processingStatusMapper);

        ProcessingStatusDTO found = serviceInterface.findByStatus(Status.PROCESSING_COMPLETE);
        assertThat(found, instanceOf(ProcessingStatusDTO.class));
    }

}
