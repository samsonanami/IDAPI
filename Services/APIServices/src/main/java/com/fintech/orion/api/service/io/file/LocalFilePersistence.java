package com.fintech.orion.api.service.io.file;


import com.fintech.orion.api.service.exceptions.PersistenceException;
import com.fintech.orion.dto.persistence.Destination;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorFactoryInterface;
import com.fintech.orion.dto.validator.validations.CommonValidations;
import com.fintech.orion.api.service.io.PersistenceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by TharinduMP on 10/28/2016.
 * LocalFilePersistence
 */
public class LocalFilePersistence implements PersistenceInterface {

    @Autowired
    private ValidatorFactoryInterface validatorFactory;

    @Override
    public boolean persistTo(MultipartFile sourceFile, Destination destination) throws PersistenceException {
        try {
            //validate if the provided arguments are null
            CommonValidations.notNull(sourceFile, "sourceFile");

            //validate destination
            validatorFactory.getValidator("destinationValidator").validate(destination);

            File file = new File(destination.getDestinationPath() + destination.getFileName());
            sourceFile.transferTo(file);

            return true;

        } catch (ValidatorException | IOException e) {
            throw new PersistenceException(e);
        }
    }
}
