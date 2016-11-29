package com.fintech.orion.api.service.io.destination.generic;


import com.fintech.orion.api.service.exceptions.DestinationProviderException;
import com.fintech.orion.dto.persistence.Destination;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.validations.CommonValidations;
import com.fintech.orion.api.service.io.destination.AbstractDestinationProvider;
import com.fintech.orion.api.service.io.destination.DestinationProviderInterface;

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
