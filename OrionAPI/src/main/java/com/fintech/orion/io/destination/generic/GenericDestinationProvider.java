package com.fintech.orion.io.destination.generic;

import com.fintech.orion.common.exceptions.DestinationProviderException;
import com.fintech.orion.dto.persistence.Destination;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.validations.CommonValidations;
import com.fintech.orion.io.destination.AbstractDestinationProvider;
import com.fintech.orion.io.destination.DestinationProviderInterface;

/**
 * Created by TharinduMP on 10/28/2016.
 * DestinationProvider
 */
public class GenericDestinationProvider extends AbstractDestinationProvider implements DestinationProviderInterface {

    @Override
    public Destination provide(String fileName) throws DestinationProviderException {
        try {
            CommonValidations.notBlank(fileName, "fileName");
            Destination destination = super.provide();
            destination.setFileName(fileName);
            return destination;
        } catch (ValidatorException e) {
            throw new DestinationProviderException(e);
        }
    }
}
