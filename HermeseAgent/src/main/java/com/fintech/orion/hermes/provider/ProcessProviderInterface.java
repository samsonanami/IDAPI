package com.fintech.orion.hermes.provider;

import com.fintech.orion.common.exceptions.ProcessProviderException;
import com.fintech.orion.dataabstraction.entities.orion.Process;
import com.fintech.orion.dto.process.ProcessDTO;

import java.util.List;

/**
 * Created by TharinduMP on 10/13/2016.
 * ProcessProviderInterface
 */
@FunctionalInterface
public interface ProcessProviderInterface {
    List<ProcessDTO> getProcesses(String identificationCode) throws ProcessProviderException;
}
