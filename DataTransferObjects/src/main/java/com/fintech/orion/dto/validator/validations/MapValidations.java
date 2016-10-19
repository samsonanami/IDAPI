package com.fintech.orion.dto.validator.validations;

import com.fintech.orion.dto.validator.ValidatorException;

import java.util.Map;

/**
 * Created by TharinduMP on 10/18/2016.
 * MapValidations
 */
public class MapValidations {



    public static void doesContainKeyWithNonEmptyValue(final Map<String, String> map, String key) throws ValidatorException {
        //check null
        CommonValidations.notNull(map,map.getClass().getSimpleName());

        //check if key is null
        CommonValidations.notBlank(key,key.getClass().getSimpleName());

        //check key contains value
        doesContainKey(map,key);

        //check if empty
        CommonValidations.notBlank(map.get(key),"key value");
    }


    private static void doesContainKey(final Map<String, String> map, String key) throws ValidatorException {
       if(!map.containsKey(key)) {
           throw new ValidatorException("");
       }
    }
}
