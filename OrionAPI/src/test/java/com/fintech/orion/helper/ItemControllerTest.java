package com.fintech.orion.helper;

import com.fintech.orion.coreservices.ResourceService;
import com.fintech.orion.coreservices.ResourceServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.model.ContentUploadResourceResult;
import com.fintech.orion.rest.ItemController;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {

    @Test
    public void shouldReturnResourceReferenceCodeWhenUploadFileCalled() throws ItemNotFoundException {
        ItemController itemController = new ItemController();

        final String filename = "image.jpg";
        final byte[] content = "image data".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("content", filename, "text/plain", content);
        MockHttpServletResponse response = new MockHttpServletResponse();
        String assessToken = "123456";
        String contentType = "image";

        FileExtensionValidatorInterface fileExtensionValidatorInterfaceMock = mock(FileExtensionValidator.class);
        when(fileExtensionValidatorInterfaceMock.validate("jpg")).thenReturn(true);
        ReflectionTestUtils.setField(itemController, "fileExtensionValidatorInterface", fileExtensionValidatorInterfaceMock);

        FileUploadHandlerInterface fileUploadHandlerInterfaceMock = mock(FileUploadHandler.class);
        when(fileUploadHandlerInterfaceMock.upload(mockMultipartFile, filename)).thenReturn(true);
        ReflectionTestUtils.setField(itemController, "fileSaveHandlerInterface", fileUploadHandlerInterfaceMock);

        ResourceServiceInterface resourceServiceInterfaceMock = mock(ResourceService.class);
        ReflectionTestUtils.setField(itemController, "resourceServiceInterface", resourceServiceInterfaceMock);

        Object resourceReferenceCode = itemController.uploadContent(contentType, response, assessToken, mockMultipartFile);
        assertThat(resourceReferenceCode, instanceOf(ContentUploadResourceResult.class));
    }

}
