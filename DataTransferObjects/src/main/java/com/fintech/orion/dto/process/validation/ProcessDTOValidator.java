package com.fintech.orion.dto.process.validation;

import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorInterface;
import com.fintech.orion.dto.validator.ValidatorResult;
import com.fintech.orion.dto.validator.validations.CommonValidations;
import org.springframework.stereotype.Component;

/**
 * Created by TharinduMP on 10/25/2016.
 * ProcessDTOValidator
 */
@Component
public class ProcessDTOValidator implements ValidatorInterface {
    @Override
    public ValidatorResult validate(Object o) throws ValidatorException {
        if(o != null && o instanceof ProcessDTO) {
            ProcessDTO processDTO = (ProcessDTO) o;
            CommonValidations.notNull(processDTO.getId(),"processDTO.id");
            CommonValidations.notBlank(processDTO.getProcessIdentificationCode(),"processDTO.id");
            CommonValidations.notNull(processDTO.getProcessTypeDTO(),"processDTO.ProcessTypeDTO");
            CommonValidations.notNull(processDTO.getProcessTypeDTO().getId(),"processDTO.ProcessTypeDTO.id");
            CommonValidations.notNull(processDTO.getProcessingStatusDTO(),"processDTO.ProcessingStatusDTO");
            CommonValidations.notNull(processDTO.getProcessingStatusDTO().getId(),"processDTO.ProcessingStatusDTO.id");
        } else {
            throw new ValidatorException("Object is null or Object is not an instanceof ProcessDTO");
        }
        return new ValidatorResult();
    }
}
