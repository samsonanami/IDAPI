package com.fintech.orion.validation;

import com.fintech.orion.coreservices.*;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.client.ClientDTO;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ClientValidatorTest {

    @Test
    public void shouldReturnTrueWhenIsValidClientCalled() throws ItemNotFoundException {
        ClientValidatorInterface clientValidator = new ClientValidator();
        String authToken = "12345";

        ClientDTO clientDTO = ObjectCreator.aClientDTO();

        ClientServiceInterface clientServiceInterfaceMock = mock(ClientService.class);
        when(clientServiceInterfaceMock.findByAuthToken(authToken)).thenReturn(clientDTO);
        ReflectionTestUtils.setField(clientValidator, "clientServiceInterface", clientServiceInterfaceMock);
        Object found = clientValidator.checkClientValidity(authToken);
        assertThat(found, instanceOf(ClientDTO.class));
        verify(clientServiceInterfaceMock, times(1)).findByAuthToken(authToken);
    }

}
