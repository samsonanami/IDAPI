package com.fintech.orion.hermes.provider;

import com.fintech.orion.common.exceptions.ProcessProviderException;
import com.fintech.orion.coreservices.ProcessingRequestServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dataabstraction.entities.orion.ProcessingRequest;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by TharinduMP on 10/13/2016.
 * ProcessProviderTest
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcessProviderTest {

    @Mock
    private ProcessingRequestServiceInterface processingRequestService;

    @InjectMocks
    private ProcessProvider processProvider;

    private ProcessingRequest processingRequest;

    @Before
    public void setUp() {
        Process process = new Process();
        Set<Process> processes = new HashSet<>();
        processes.add(process);
        processingRequest = new ProcessingRequest();
        processingRequest.setProcesses(processes);
    }

    @Test(expected = ProcessProviderException.class)
    public void shouldThrowExceptionWhenProcessingRequestIdIsNotFound() throws Exception {
        when(processingRequestService.findByIdIdentificationCode("1")).thenThrow(ItemNotFoundException.class);
        processProvider.getProcesses("1");
    }

    @Test
    public void shouldReturnValidProcessesGivenValidId() throws Exception {
        when(processingRequestService.findByIdIdentificationCode("1")).thenReturn(processingRequest);
        assertTrue(processProvider.getProcesses("1").size() == 1);
    }

    @Test(expected = ProcessProviderException.class)
    public void shouldThrowExceptionWhenIdentificationIsNull() throws Exception {
        processProvider.getProcesses(null);
    }

    @Test(expected = ProcessProviderException.class)
    public void shouldThrowExceptionWhenIdentificationIsWhiteSpace() throws Exception {
        processProvider.getProcesses("   ");
    }
}