package com.fintech.orion.helper;

import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;

import java.util.List;

public interface JsonValidatorInterface {

    boolean jsonValidate(List<VerificationProcess> verificationProcessList) throws ReflectiveOperationException;
}
