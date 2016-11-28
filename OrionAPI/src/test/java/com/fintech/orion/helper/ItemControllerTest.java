package com.fintech.orion.helper;

import com.fintech.orion.coreservices.ResourceService;
import com.fintech.orion.coreservices.ResourceServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;
import com.fintech.orion.handlers.OrionJobHandler;
import com.fintech.orion.handlers.OrionJobHandlerInterface;
import com.fintech.orion.model.ContentUploadResourceResult;
import com.fintech.orion.model.VerificationResponseMessage;
import com.fintech.orion.rest.ItemController;
import com.fintech.orion.validation.ClientValidator;
import com.fintech.orion.validation.ClientValidatorInterface;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.awt.image.BufferedImage;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Item controller endpoint tests
 */
public class ItemControllerTest {

    private static final String ACCESS_TOKEN = "12345";
    private static final String PROCESSING_REQUEST_HANDLER_INTERFACE = "processingRequestHandlerInterface";

    /**
     * UUID issue
     */
    @Ignore
    @Test
    public void shouldReturnResourceReferenceCodeWhenUploadContentCalled() throws ItemNotFoundException {
        ItemController itemController = new ItemController();

        final String filename = "image.jpg";
        final byte[] content = "image data".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("content", filename, "text/plain", content);
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        String contentType = "image";
        String uuidNumber = "1234-5678";
        String newFilename = uuidNumber + "jpeg";

        FileExtensionValidatorInterface fileExtensionValidatorInterfaceMock = mock(FileExtensionValidator.class);
        when(fileExtensionValidatorInterfaceMock.validate("jpg")).thenReturn(true);
        ReflectionTestUtils.setField(itemController, "fileExtensionValidatorInterface", fileExtensionValidatorInterfaceMock);

        FileUploadHandlerInterface fileUploadHandlerInterfaceMock = mock(FileUploadHandler.class);
        when(fileUploadHandlerInterfaceMock.upload(mockMultipartFile, newFilename)).thenReturn(true);
        ReflectionTestUtils.setField(itemController, "fileUploadHandlerInterface", fileUploadHandlerInterfaceMock);

        ResourceServiceInterface resourceServiceInterfaceMock = mock(ResourceService.class);
        when(resourceServiceInterfaceMock.save(newFilename, uuidNumber, contentType, ACCESS_TOKEN)).thenReturn(ObjectCreator.aResourceDTO());
        ReflectionTestUtils.setField(itemController, "resourceServiceInterface", resourceServiceInterfaceMock);

        Object found = itemController.uploadContent(contentType, response, request, mockMultipartFile);
        assertThat(found, instanceOf(ContentUploadResourceResult.class));
    }

    @Test
    public void shouldReturnProcessingRequestIdWhenVerificationCalled() throws ItemNotFoundException, ReflectiveOperationException {
       /* ItemController itemController = new ItemController();

        boolean integrationRequest = true;
        MockHttpServletResponse response = new MockHttpServletResponse();
        ProcessingRequest data = ObjectCreator.aProcessingRequest();
        MockHttpServletRequest request = new MockHttpServletRequest();
        ProcessingRequestHandlerInterface processingRequestHandlerInterfaceMock = mock(ProcessingRequestHandler.class);
        when(processingRequestHandlerInterfaceMock.saveVerificationProcessData(ACCESS_TOKEN, data.getVerificationProcesses())).thenReturn("12341231234");
        ReflectionTestUtils.setField(itemController, PROCESSING_REQUEST_HANDLER_INTERFACE, processingRequestHandlerInterfaceMock);

        ProcessingRequestJsonFormatValidatorInterface validatorInterface = mock(ProcessingRequestJsonFormatValidator.class);
        when(validatorInterface.validate(data)).thenReturn(true);
        ReflectionTestUtils.setField(itemController, "processingRequestJsonFormatValidator", validatorInterface);

        ClientValidatorInterface clientValidatorInterfaceMock = mock(ClientValidator.class);
        when(clientValidatorInterfaceMock.checkClientValidity(ACCESS_TOKEN)).thenReturn(ObjectCreator.aClientDTO());
        ReflectionTestUtils.setField(itemController, "clientValidatorInterface", clientValidatorInterfaceMock);

        OrionJobHandlerInterface orionJobHandlerInterfaceMock = mock(OrionJobHandler.class);
        ReflectionTestUtils.setField(itemController, "orionJobHandlerInterface", orionJobHandlerInterfaceMock);

        Object found = itemController.verification(integrationRequest, response, request, data);
        assertThat(found, instanceOf(VerificationResponseMessage.class));*/
    }

    @Test
    public void shouldReturnVerificationRequestObjectWhenVerificationResultsCalled() throws ItemNotFoundException {
      /*  ItemController itemController = new ItemController();

        String verificationRequestId = "987654321";
        MockHttpServletResponse response = new MockHttpServletResponse();
        VerificationRequest verificationRequest = ObjectCreator.aVerificationRequest();
        MockHttpServletRequest request = new MockHttpServletRequest();
        ProcessingRequestHandlerInterface processingRequestHandlerInterfaceMock = mock(ProcessingRequestHandler.class);
        when(processingRequestHandlerInterfaceMock.getVerificationRequestData(ACCESS_TOKEN, verificationRequestId)).thenReturn(verificationRequest);
        ReflectionTestUtils.setField(itemController, PROCESSING_REQUEST_HANDLER_INTERFACE, processingRequestHandlerInterfaceMock);

        ClientValidatorInterface clientValidatorInterfaceMock = mock(ClientValidator.class);
        when(clientValidatorInterfaceMock.checkClientValidity(ACCESS_TOKEN)).thenReturn(ObjectCreator.aClientDTO());
        ReflectionTestUtils.setField(itemController, "clientValidatorInterface", clientValidatorInterfaceMock);

        Object found = itemController.verificationResults(verificationRequestId, response, request);
        assertThat(found, instanceOf(VerificationRequest.class));*/
    }

    @Test
    public void shouldReturnProcessedResourcesObjectWhenProcessedResourcesCalled() throws Exception {
     /*   ItemController itemController = new ItemController();

        String verificationProcessId = "987654321";
        MockHttpServletResponse response = new MockHttpServletResponse();
        String id = "12345";
        MockHttpServletRequest request = new MockHttpServletRequest();
        ProcessingRequestHandlerInterface processingRequestHandlerInterfaceMock = mock(ProcessingRequestHandler.class);
        when(processingRequestHandlerInterfaceMock.getResourceData(ACCESS_TOKEN, verificationProcessId, id)).thenReturn(new BufferedImage(1, 1, 1));
        ReflectionTestUtils.setField(itemController, PROCESSING_REQUEST_HANDLER_INTERFACE, processingRequestHandlerInterfaceMock);

        ClientValidatorInterface clientValidatorInterfaceMock = mock(ClientValidator.class);
        when(clientValidatorInterfaceMock.checkClientValidity(ACCESS_TOKEN)).thenReturn(ObjectCreator.aClientDTO());
        ReflectionTestUtils.setField(itemController, "clientValidatorInterface", clientValidatorInterfaceMock);

        itemController.processedResources(verificationProcessId, id, response, request);*/
    }

}
