package com.fintech.orion.service.core.file;

import com.fintech.orion.exception.FileHandlerException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by sasitha on 12/22/16.
 */
public class FileHandlerServiceTest {

    private FileHandlerService fileHandlerService;
    private String testFileSavePath;

    @Before
    public void setup() throws Exception{
        fileHandlerService = new FileHandlerService();
        testFileSavePath = "./";
    }

    @Test
    public void should_save_file() throws Exception{
        MultipartFile multipartFile = new MockMultipartFile("data", "filename.txt",
                "text/plain", "some xml".getBytes());
        String fileName = fileHandlerService.persistFile(multipartFile, FileStorage.LOCAL, testFileSavePath);
        Assert.notNull(fileName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException_if_multipart_file_not_defined() throws Exception{
        String fileName = fileHandlerService.persistFile(null, FileStorage.LOCAL, testFileSavePath);
        Assert.isNull(fileName);
    }




}