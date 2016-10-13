package com.fintech.orion.hermes.provider;

import com.fintech.orion.common.exceptions.ProcessProviderException;
import com.fintech.orion.dataabstraction.entities.orion.Process;

import java.util.List;

/**
 * Created by TharinduMP on 10/13/2016.
 */
public interface ProcessProviderInterface {
    List<Process> getProcesses(int processingRequestId) throws ProcessProviderException;
}
