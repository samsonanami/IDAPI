package com.fintech.orion.coreservices;

import com.fintech.orion.dataabstraction.entities.orion.ProcessType;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepository;
import com.fintech.orion.dataabstraction.repositories.ProcessTypeRepositoryInterface;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.mapping.processtype.ProcessTypeMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
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

        ProcessTypeMapper processTypeMapper = Mappers.getMapper(ProcessTypeMapper.class);
        ReflectionTestUtils.setField(serviceInterface, "processTypeMapper", processTypeMapper);

        Object found = serviceInterface.findByType("type");
        assertThat(found, instanceOf(ProcessTypeDTO.class));
    }
}
