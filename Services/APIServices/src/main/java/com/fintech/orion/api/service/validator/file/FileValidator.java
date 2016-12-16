package com.fintech.orion.api.service.validator.file;


import com.fintech.orion.api.service.exceptions.FileValidatorException;
import com.fintech.orion.dto.validation.file.ValidatorStatus;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.validations.CommonValidations;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by TharinduMP on 10/27/2016.
 * FileValidator
 * Refactor for (Phase 2): this classes should be broken by responsibilities and should take
 * content type as a variable of change
 */
public class FileValidator implements FileValidatorInterface {

    @Resource(name="supportedFilesAndSizes")
    private Map<String, Integer> supportedFilesAndSizes;

    @Override
    public ValidatorStatus validateFile(MultipartFile file) throws FileValidatorException {
        try {
            ValidatorStatus validatorStatus = new ValidatorStatus();
            validatorStatus.setPassed(true);

            //validate preconditions
            validateMultipart(file);

            //validate file extension
            validatorStatus = validateExtension(file, validatorStatus);

            if (!validatorStatus.isPassed()) {
                return validatorStatus;
            }

            //validate file size
            validatorStatus = validateSize(file, getFileExtension(file.getOriginalFilename()), validatorStatus);

            return validatorStatus;
        } catch (ValidatorException e) {
            throw new FileValidatorException(e);
        }
    }

    private void validateMultipart(MultipartFile file) throws ValidatorException {
        CommonValidations.notNull(file, "multipart file");
    }

    private boolean isExtensionSupported(String ext) {
        return supportedFilesAndSizes.containsKey(ext);
    }

    private ValidatorStatus validateExtension(MultipartFile file,ValidatorStatus validatorStatus) {
        String ext = getFileExtension(file.getOriginalFilename());

        if (ext.isEmpty()) {
            validatorStatus.setMessage("extension is missing in the provided file.");
            validatorStatus.setPassed(false);
            return validatorStatus;
        }

        if (!isExtensionSupported(ext)) {
            validatorStatus.setMessage("extension '" + ext + "' is not supported.");
            validatorStatus.setPassed(false);
            return validatorStatus;
        }
        return validatorStatus;
    }

    private ValidatorStatus validateSize(MultipartFile file, String ext, ValidatorStatus validatorStatus) {
        if (file.getSize() > supportedFilesAndSizes.get(ext)) {
            validatorStatus.setPassed(false);
            validatorStatus.setMessage("file size is beyond the supported. the limit for '" + ext + "' is " + supportedFilesAndSizes.get(ext).toString() + " bytes");
        }
        return validatorStatus;
    }

    private String getFileExtension(String fileName) {
        return FilenameUtils.getExtension(fileName.toLowerCase());
    }
}
