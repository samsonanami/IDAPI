package com.fintech.orion.api.service.validator.file;


import com.fintech.orion.api.service.exceptions.FileValidatorException;
import com.fintech.orion.dto.validation.file.ValidatorStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by TharinduMP on 10/27/2016.
 * FileValidatorInterface
 */
@FunctionalInterface
public interface FileValidatorInterface {
    ValidatorStatus validateFile(MultipartFile file) throws FileValidatorException;
}
