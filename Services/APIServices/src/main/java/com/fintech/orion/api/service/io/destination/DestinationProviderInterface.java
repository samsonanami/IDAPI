package com.fintech.orion.api.service.io.destination;


import com.fintech.orion.api.service.exceptions.DestinationProviderException;
import com.fintech.orion.dto.persistence.Destination;

/**
 * Created by TharinduMP on 10/28/2016.
 * DestinationProviderInterface
 */
@FunctionalInterface
public interface DestinationProviderInterface {
    Destination provide(String fileName) throws DestinationProviderException;
}
