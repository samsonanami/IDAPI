package com.fintech.orion.io.destination;

import com.fintech.orion.common.exceptions.DestinationProviderException;
import com.fintech.orion.dto.persistence.Destination;

/**
 * Created by TharinduMP on 10/28/2016.
 * DestinationProviderInterface
 */
@FunctionalInterface
public interface DestinationProviderInterface {
    Destination provide(String fileName) throws DestinationProviderException;
}
