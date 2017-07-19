package com.fintech.orion.dto.request.validation;

import com.fintech.orion.dto.request.RequestProcessDTO;
import com.fintech.orion.dto.validator.ValidatorException;
import com.fintech.orion.dto.validator.ValidatorInterface;
import com.fintech.orion.dto.validator.ValidatorResult;
import com.fintech.orion.dto.validator.validations.CommonValidations;
import org.springframework.stereotype.Component;

/**
 * Created by TharinduMP on 10/14/2016.
 * RequestProcessDTOValidator
 */
@Component
public class RequestProcessDTOValidator implements ValidatorInterface {

    @Override
    public ValidatorResult validate(Object o) throws ValidatorException {
        if(o != null && o instanceof RequestProcessDTO) {
            RequestProcessDTO requestProcessDTO = (RequestProcessDTO) o;
            CommonValidations.notNull(requestProcessDTO.getProcessId(),"processId");
            CommonValidations.notNull(requestProcessDTO.getProcessType(),"processType");
        } else {
            throw new ValidatorException("Object is null or Object is not an instanceof RequestProcessDTO");
        }
        return new ValidatorResult();
    }
}
