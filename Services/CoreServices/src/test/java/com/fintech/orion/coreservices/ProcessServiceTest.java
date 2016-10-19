package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessRepositoryInterface;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.mapping.process.ProcessMapper;
import com.fintech.orion.mapping.processingrequest.ProcessingRequestMapper;
import com.fintech.orion.mapping.processingstatus.ProcessingStatusMapper;
import com.fintech.orion.mapping.processtype.ProcessTypeMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Process entity service tests
 */
public class ProcessServiceTest {

    private final String REPOSITORY_INTERFACE = "processRepositoryInterface";

    @Ignore
    @Test
    public void shouldSaveObjectWhenSaveCalled() {
        ProcessServiceInterface serviceInterface = new ProcessService();
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessMapper processMapper = Mappers.getMapper(ProcessMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "processMapper", processMapper);

        ProcessingRequestMapper processingRequestMapper = Mappers.getMapper(ProcessingRequestMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "processingRequestMapper", processingRequestMapper);

        ProcessingStatusMapper processingStatusMapper = Mappers.getMapper(ProcessingStatusMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "processingStatusMapper", processingStatusMapper);

        ProcessTypeMapper processTypeMapper = Mappers.getMapper(ProcessTypeMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "processTypeMapper", processTypeMapper);

        Object found = serviceInterface.save(ObjectCreator.createProcessTypeDTOObject(), ObjectCreator.createProcessingRequestDTOObject(), ObjectCreator.createProcessingStatusDTOObject());
        assertThat(found, instanceOf(ProcessDTO.class));
    }

    @Ignore
    @Test
    public void shouldProcessObjectWhenFindByIdentificationCodeCalled() throws ItemNotFoundException {
        ProcessServiceInterface serviceInterface = new ProcessService();
        String identificationCode = "12345";
        ProcessRepositoryInterface repositoryInterfaceMock = mock(ProcessRepository.class);
        Process process = ObjectCreator.createProcessObject();
        when(repositoryInterfaceMock.findByIdentificationCode(identificationCode)).thenReturn(process);
        ReflectionTestUtils.setField(serviceInterface, REPOSITORY_INTERFACE, repositoryInterfaceMock);

        ProcessMapper processMapper = Mappers.getMapper(ProcessMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "processMapper", processMapper);

        Object found = serviceInterface.findByIdentificationCode(identificationCode);
        assertThat(found, instanceOf(Process.class));
    }

}
