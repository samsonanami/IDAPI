package com.fintech.orion.helper;

import com.fintech.orion.coreservices.ClientService;
import com.fintech.orion.coreservices.ClientServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.helper.objectcreators.TestClients;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ClientValidatorTest {

//    @Test
//    public void shouldReturnTrueWhenIsValidClientCalled() throws ItemNotFoundException {
//        ClientValidatorInterface clientValidator = new ClientValidator();
//        String authToken = "12345";
//
//        Client client = TestClients.aDefaultClient().withAuthToken("12345").build();
//
//        ClientServiceInterface clientServiceInterfaceMock = mock(ClientService.class);
//        //when(clientServiceInterfaceMock.findByAuthToken(authToken)).thenReturn(client);
//        ReflectionTestUtils.setField(clientValidator, "clientServiceInterface", clientServiceInterfaceMock);
//        Object found = clientValidator.checkClientValidity(authToken);
//        assertThat(found, instanceOf(Client.class));
//        verify(clientServiceInterfaceMock, times(1)).findByAuthToken(authToken);
//        assertTrue(authToken.equals(client.getAuthToken()));
//    }

}
