package com.fintech.orion.helper;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class FileSizeCheckerTest {
    private static final String CONTENT = "content";
    private static final String TEXT_PLAIN = "text/plain";

    @Test
    public void shouldReturnTrueFor5MBJpegImageWhenCheckSizeCalled() throws IOException {
        FileSizeCheckerInterface fileSizeCheckerInterface = new FileSizeChecker();

        final String filename = "image.jpeg";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream fileInputStream = new FileInputStream(classLoader.getResource("image_test_lessthan_5mb.jpeg").getFile());
        MockMultipartFile imageMock = new MockMultipartFile(CONTENT, filename, TEXT_PLAIN, IOUtils.toByteArray(fileInputStream));
        boolean result = fileSizeCheckerInterface.checkSize(imageMock);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseForMoreThan5MBJpegImageWhenCheckSizeCalled() throws IOException {
        FileSizeCheckerInterface fileSizeCheckerInterface = new FileSizeChecker();

        final String filename = "image.jpeg";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream fileInputStream = new FileInputStream(classLoader.getResource("image_test_morethan_5mb.jpeg").getFile());
        MockMultipartFile imageMock = new MockMultipartFile(CONTENT, filename, TEXT_PLAIN, IOUtils.toByteArray(fileInputStream));
        boolean result = fileSizeCheckerInterface.checkSize(imageMock);
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueFor2MBMP4ImageWhenCheckSizeCalled() throws IOException {
        FileSizeCheckerInterface fileSizeCheckerInterface = new FileSizeChecker();

        final String filename = "video.mp4";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream fileInputStream = new FileInputStream(classLoader.getResource("video_test_lessthan_2mb.mp4").getFile());
        MockMultipartFile imageMock = new MockMultipartFile(CONTENT, filename, TEXT_PLAIN, IOUtils.toByteArray(fileInputStream));
        boolean result = fileSizeCheckerInterface.checkSize(imageMock);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseFor2MBMP4ImageWhenCheckSizeCalled() throws IOException {
        FileSizeCheckerInterface fileSizeCheckerInterface = new FileSizeChecker();

        final String filename = "video.mp4";
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream fileInputStream = new FileInputStream(classLoader.getResource("video_test_morethan_2mb.mp4").getFile());
        MockMultipartFile imageMock = new MockMultipartFile(CONTENT, filename, TEXT_PLAIN, IOUtils.toByteArray(fileInputStream));
        boolean result = fileSizeCheckerInterface.checkSize(imageMock);
        assertFalse(result);
    }

}
