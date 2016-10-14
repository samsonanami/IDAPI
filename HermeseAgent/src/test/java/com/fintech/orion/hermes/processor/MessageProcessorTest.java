package com.fintech.orion.hermes.processor;

import com.fintech.orion.common.exceptions.MessageProcessorException;
import org.junit.Before;
import org.junit.Test;

import javax.jms.Message;

/**
 * Created by TharinduMP on 10/13/2016.
 * MessageProcessorTest
 * Limited Test coverage due to 3rd party library intervention.
 */
public class MessageProcessorTest {

    private MessageProcessor messageProcessor;

    @Before
    public void setUp() throws Exception {
        messageProcessor = new MessageProcessor();
    }

    @Test(expected = MessageProcessorException.class)
    public void shouldThrowExceptionWhenMessageIsNotCorrectType() throws MessageProcessorException {
        Message message = null;
        messageProcessor.processMessage(message);
    }
}