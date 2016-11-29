package com.fintech.orion.api.service.io;


import com.fintech.orion.api.service.exceptions.PersistenceException;
import com.fintech.orion.dto.persistence.Destination;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by TharinduMP on 10/28/2016.
 * PersistenceInterface
 */
@FunctionalInterface
public interface PersistenceInterface {

    boolean persistTo(MultipartFile sourceFile, Destination destination) throws PersistenceException;
}
