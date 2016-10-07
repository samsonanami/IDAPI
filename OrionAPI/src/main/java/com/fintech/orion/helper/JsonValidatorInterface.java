package com.fintech.orion.helper;

import com.fintech.orion.dataabstraction.models.VerificationProcess;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface JsonValidatorInterface {

    boolean jsonValidate(List<VerificationProcess> verificationProcessList) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
