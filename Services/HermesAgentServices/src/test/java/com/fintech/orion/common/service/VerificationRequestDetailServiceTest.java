package com.fintech.orion.common.service;

import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.entities.orion.Response;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import com.fintech.orion.dataabstraction.repositories.ResponseRepositoryInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by sasitha on 12/23/16.
 *
 */
public class VerificationRequestDetailServiceTest {

    @InjectMocks
    private VerificationRequestDetailService verificationRequestDetailService;

    @Mock
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;


    @Mock
    private ResponseRepositoryInterface responseRepositoryInterface;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_processing_request_when_the_correct_id_is_given()throws Exception{
        ProcessingRequest processingRequest = new ProcessingRequest();
        processingRequest.setProcessingRequestIdentificationCode("123456");
        Mockito.when(processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(
                "123456"
        )).thenReturn(processingRequest);
        ProcessingRequest p = verificationRequestDetailService.getProcessingRequest("123456");
        Assert.assertNotNull(p);
        Assert.assertEquals(p.getProcessingRequestIdentificationCode(), "123456");
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_throw_ItemNotFoundException_if_no_processing_request_found_for_the_given_id()throws Exception{
        Mockito.when(processingRequestRepositoryInterface.findProcessingRequestByProcessingRequestIdentificationCode(
                Matchers.anyString()
        )).thenReturn(null);
        ProcessingRequest p = verificationRequestDetailService.getProcessingRequest("1234");
    }

    @Test
    public void should_save_the_given_response()throws Exception{
        Response response = new Response();
        Process process = new Process();

        Mockito.when(responseRepositoryInterface.save(Matchers.any(Response.class))).thenReturn(response);

        verificationRequestDetailService.saveResponse("", "", process);
    }

}