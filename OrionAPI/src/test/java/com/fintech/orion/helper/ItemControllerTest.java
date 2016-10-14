package com.fintech.orion.helper;

import com.fintech.orion.coreservices.ResourceService;
import com.fintech.orion.coreservices.ResourceServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Resource;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.models.verificationprocess.ProcessingRequest;
import com.fintech.orion.dataabstraction.models.verificationresult.VerificationRequest;
import com.fintech.orion.model.ContentUploadResourceResult;
import com.fintech.orion.model.VerificationResponseMessage;
import com.fintech.orion.rest.ItemController;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public class ItemControllerTest {

    private static final String ACCESS_TOKEN = "12345";
    private static final String PROCESSING_REQUEST_HANDLER_INTERFACE = "processingRequestHandlerInterface";

    @Test
    public void shouldReturnResourceReferenceCodeWhenUploadContentCalled() throws ItemNotFoundException {
        ItemController itemController = new ItemController();

        final String filename = "image.jpg";
        final byte[] content = "image data".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("content", filename, "text/plain", content);
        MockHttpServletResponse response = new MockHttpServletResponse();
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
        when(resourceServiceInterfaceMock.save(newFilename, uuidNumber, contentType, ACCESS_TOKEN)).thenReturn(new Resource());
        ReflectionTestUtils.setField(itemController, "resourceServiceInterface", resourceServiceInterfaceMock);

        Object found = itemController.uploadContent(contentType, response, ACCESS_TOKEN, mockMultipartFile);
        assertThat(found, instanceOf(ContentUploadResourceResult.class));
    }

    @Test
    public void shouldReturnProcessingRequestIdWhenVerificationCalled() throws ItemNotFoundException, ReflectiveOperationException {
        ItemController itemController = new ItemController();

        boolean integrationRequest = true;
        MockHttpServletResponse response = new MockHttpServletResponse();
        ProcessingRequest data = ObjectCreator.aProcessingRequest();

        ProcessingRequestHandlerInterface processingRequestHandlerInterfaceMock = mock(ProcessingRequestHandler.class);
        when(processingRequestHandlerInterfaceMock.saveVerificationProcessData(ACCESS_TOKEN, data.getVerificationProcesses())).thenReturn("12341231234");
        ReflectionTestUtils.setField(itemController, PROCESSING_REQUEST_HANDLER_INTERFACE, processingRequestHandlerInterfaceMock);

        JsonValidatorInterface jsonValidatorInterfaceMock = mock(JsonValidator.class);
        when(jsonValidatorInterfaceMock.jsonValidate(data.getVerificationProcesses())).thenReturn(true);
        ReflectionTestUtils.setField(itemController, "jsonValidatorInterface", jsonValidatorInterfaceMock);

        Object found = itemController.verification(integrationRequest, response, ACCESS_TOKEN, data);
        assertThat(found, instanceOf(VerificationResponseMessage.class));
    }

    @Test
    public void shouldReturnVerificationRequestObjectWhenVerificationResultsCalled() throws ItemNotFoundException {
        ItemController itemController = new ItemController();

        String verificationRequestId = "987654321";
        MockHttpServletResponse response = new MockHttpServletResponse();
        VerificationRequest verificationRequest = ObjectCreator.aVerificationRequest();

        ProcessingRequestHandlerInterface processingRequestHandlerInterfaceMock = mock(ProcessingRequestHandler.class);
        when(processingRequestHandlerInterfaceMock.getVerificationRequestData(ACCESS_TOKEN, verificationRequestId)).thenReturn(verificationRequest);
        ReflectionTestUtils.setField(itemController, PROCESSING_REQUEST_HANDLER_INTERFACE, processingRequestHandlerInterfaceMock);

        Object found = itemController.verificationResults(verificationRequestId, response, ACCESS_TOKEN);
        assertThat(found, instanceOf(VerificationRequest.class));
    }

    @Test
    public void shouldReturnProcessedResourcesObjectWhenProcessedResourcesCalled() throws ItemNotFoundException, IOException {
        ItemController itemController = new ItemController();

        String verificationProcessId = "987654321";
        MockHttpServletResponse response = new MockHttpServletResponse();
        String id = "12345";

        Object object = new Object();

        ProcessingRequestHandlerInterface processingRequestHandlerInterfaceMock = mock(ProcessingRequestHandler.class);
        //when(processingRequestHandlerInterfaceMock.getResourceData(ACCESS_TOKEN, verificationProcessId, id)).thenReturn(object);
        ReflectionTestUtils.setField(itemController, PROCESSING_REQUEST_HANDLER_INTERFACE, processingRequestHandlerInterfaceMock);

        itemController.processedResources(verificationProcessId, id, response, ACCESS_TOKEN);
    }

}
