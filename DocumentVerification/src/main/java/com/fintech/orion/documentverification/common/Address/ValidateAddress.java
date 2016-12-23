package com.fintech.orion.documentverification.common.Address;

import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import com.fintech.orion.documentverification.common.mrz.MRZItemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MudithaJ on 12/16/2016.
 */
public class ValidateAddress {
    @Autowired
    @Qualifier("addressConfigureList")
    private HashMap<String,AddressType> AddressTypeProperty;
    public AddressType validate(String address) throws AddressValidatingException
    {
        AddressType validAddressType = new AddressType();
        validAddressType.setType("0");
        AddressType[] addressTypes;
        try {
            for(AddressType type:AddressTypeProperty.values())
            {
                if(this.checkAddressType(type,address))
                {
                    validAddressType = type;
                    break;
                }
            }
            return validAddressType;
        }

        catch (NullPointerException e)
        {
           throw new AddressValidatingException("Not well formatted address  or not well set configuration properties Exception. Address :"+address+"type:"+validAddressType.toString());

        }

    }

    private boolean checkAddressType(AddressType addressType,String address)
    {   String regularExpression;
        boolean isAddressValidType;
        regularExpression = addressType.getValidateRegularExpression();
        Pattern pattern = Pattern.compile(regularExpression);

        Matcher matcher =pattern.matcher(address);
        isAddressValidType = matcher.find();
        return  isAddressValidType;

    }

}


