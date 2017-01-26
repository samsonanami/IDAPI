package com.fintech.orion.documentverification.common.address;

import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This call validates address is validate or not.
 * Created by MudithaJ on 12/16/2016.
 */
public class ValidateAddress {
    @Autowired
    @Qualifier("addressConfigureList")
    private HashMap<String, AddressType> addressTypeProperty;

    public AddressType validate(String address) throws AddressValidatingException {
        AddressType validAddressType = new AddressType();
        validAddressType.setType("0");
        try {
            for (AddressType type : addressTypeProperty.values()) {
                if (this.checkAddressType(type, address)) {
                    validAddressType = type;
                    break;
                }
            }
            return validAddressType;
        } catch (NullPointerException e) {
            throw new AddressValidatingException("Not well formatted address or not well set configuration " +
                    "properties Exception. address :" + address + "type:" + validAddressType.toString(), e);

        }

    }

    private boolean checkAddressType(AddressType addressType, String address) {
        String regularExpression;
        boolean isAddressValidType;
        regularExpression = addressType.getValidateRegularExpression();
        Pattern pattern = Pattern.compile(regularExpression);

        Matcher matcher = pattern.matcher(address);
        isAddressValidType = matcher.find();
        return isAddressValidType;

    }

}


