package com.fintech.orion.api.service.messenging;

import com.fintech.orion.common.exceptions.job.JobHandlerException;
import com.fintech.orion.messaging.job.JobCommonInterface;
import com.fintech.orion.messaging.job.JobHandlerInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.jms.Message;
import javax.jms.Session;

/**
 *
 * Created by sasitha on 11/29/16.
 */
public class MessageHandlerTest {

    @InjectMocks
    private MessageHandler messageHandler;

    @Mock
    private JobHandlerInterface jobHandlerInterface;

    @Mock
    private JobCommonInterface jobCommonInterface;

    private String LICENSE_KEY = "e398ad4a-25a1-4b39-99ec-1f6c9f8a3ba4";
    private String REQUEST_ID = "m998ad4a-34a1-4b39-99ec-1f6c9f8a3ba4";

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

}
