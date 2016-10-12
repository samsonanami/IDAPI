package com.fintech.orion.helper;

import com.fintech.orion.model.Configuration;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileUploadHandlerTest {

    @Test
    public void shouldUploadFileToServerWhenUploadCalled() {
        Configuration configuration = new Configuration("localhost", 22, "root", "zone@123");
        String testFileContents = "Some file contents";
        String uploadFileName = "uploadFile";
        String workingDir = "//opt//orion//data/test";

        final String filename = "image.jpg";
        final byte[] content = testFileContents.getBytes();
        MultipartFile multipartFile = new MockMultipartFile("content", filename, "text/plain", content);

        FileUploadHandlerInterface fileUploadHandlerInterface = new FileUploadHandler();
        ReflectionTestUtils.setField(fileUploadHandlerInterface, "configuration", configuration);
        ReflectionTestUtils.setField(fileUploadHandlerInterface, "workingDir", workingDir);

        FileSizeCheckerInterface fileSizeCheckerInterfaceMock = mock(FileSizeChecker.class);
        when(fileSizeCheckerInterfaceMock.checkSize(multipartFile)).thenReturn(true);
        ReflectionTestUtils.setField(fileUploadHandlerInterface, "fileSizeCheckerInterface", fileSizeCheckerInterfaceMock);

        fileUploadHandlerInterface.upload(multipartFile, uploadFileName);
    }

}
