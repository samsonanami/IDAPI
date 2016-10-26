package com.fintech.orion.hermesagentservices.state.process;

import com.fintech.orion.dto.process.ProcessDTO;

/**
 * Created by TharinduMP on 10/26/2016.
 */
@FunctionalInterface
public interface ProcessStateInterface {
    void updateFinalProcessState(ProcessDTO processDTO);
}
