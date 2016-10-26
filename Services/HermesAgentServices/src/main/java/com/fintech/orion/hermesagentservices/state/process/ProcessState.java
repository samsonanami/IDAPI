package com.fintech.orion.hermesagentservices.state.process;

import com.fintech.orion.coreservices.ProcessServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.process.ProcessDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TharinduMP on 10/26/2016.
 * Class responsible for persisting process state throughout the workflow
 */
public class ProcessState implements ProcessStateInterface {

    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProcessState.class);

    @Autowired
    private ProcessServiceInterface processService;

    @Override
    public void updateFinalProcessState(ProcessDTO processDTO) {
        try {
            processService.update(processDTO);
        } catch (ItemNotFoundException e) {
            LOGGER.error("Process Id was not found in database to update final state of the Process.", e);
        }
    }
}
