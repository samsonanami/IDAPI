package com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fintech.orion.hermesagentservices.transmission.payload.model.jenid.OutputData;

/**
 * Created by TharinduMP on 10/26/2016.
 */
public abstract class IgnoreOutput {

    @JsonIgnore
    OutputData outputData;
}
