package com.fintech.orion.api.service.io.destination;

import com.fintech.orion.dto.persistence.Destination;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TharinduMP on 10/28/2016.
 */
public abstract class AbstractDestinationProvider {

    @Autowired
    private String workingDir;

    public Destination provide() {
        Destination destination = new Destination();
        destination.setDestinationPath(workingDir);
        return destination;
    }
}
