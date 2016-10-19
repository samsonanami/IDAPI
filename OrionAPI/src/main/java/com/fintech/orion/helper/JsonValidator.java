package com.fintech.orion.helper;

import com.fintech.orion.dataabstraction.models.verificationprocess.Resource;
import com.fintech.orion.dataabstraction.models.verificationprocess.VerificationProcess;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

public class JsonValidator implements JsonValidatorInterface {

    @Autowired
    private List<VerificationProcess> verificationProcessList;

    @Override
    public boolean jsonValidate(List<VerificationProcess> verificationProcessList) throws ReflectiveOperationException {
        for (VerificationProcess v : verificationProcessList) {
            VerificationProcess verificationProcess = getVerificationProcessForType(v.getVerificationProcessType());

            if (verificationProcess != null) {
                return compareObjects(verificationProcess, v);
            }
            return false;
        }
        return false;
    }

    private boolean compareObjects(VerificationProcess correctObject, VerificationProcess tempObject) throws ReflectiveOperationException {
        Field[] fields = Resource.class.getDeclaredFields();
        for (Field f : fields) {
            if ((BeanUtils.getProperty(correctObject.getResources().get(0), f.getName()) == null && BeanUtils.getProperty(tempObject.getResources().get(0), f.getName()) != null) ||
                    (BeanUtils.getProperty(correctObject.getResources().get(0), f.getName()) != null && BeanUtils.getProperty(tempObject.getResources().get(0), f.getName()) == null)) {
                return false;
            }
        }
        return true;
    }

    private VerificationProcess getVerificationProcessForType(String verificationProcessType) {
        VerificationProcess verificationProcess = null;
        for (VerificationProcess v : verificationProcessList) {
            if (v.getVerificationProcessType().equals(verificationProcessType)) {
                verificationProcess = v;
                break;
            }
        }
        return verificationProcess;
    }


}
