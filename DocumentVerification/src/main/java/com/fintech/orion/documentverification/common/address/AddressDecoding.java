package com.fintech.orion.documentverification.common.address;

import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MudithaJ on 12/16/2016.
 */
public class AddressDecoding {

    @Autowired
    private ValidateAddress addressValidator;

    private AddressType addresstype;

    public AddressDecodeResults decode(String address) throws AddressValidatingException {

        AddressDecodeResults results = new AddressDecodeResults();
        try {

            addresstype = addressValidator.validate(address);
            if ("0".equalsIgnoreCase(addresstype.getType())) {
                results.setAddressType("0");
            } else {
                results = devideToElement(addresstype, address);
            }

            return results;
        } catch (AddressValidatingException e) {
            throw new AddressValidatingException("address validating failed number" + results.getAddressType(), e);
        }
    }

    public AddressDecodeResults devideToElement(AddressType addressType, String address) {
        String regularExpression = addressType.getDecodeRegularExpression();
        regularExpression = addressType.getDecodeRegularExpression();
        AddressDecodeResults results = new AddressDecodeResults();
        Pattern pattern = Pattern.compile(regularExpression);

        Matcher matcher = pattern.matcher(address);
        if (matcher.find()){
            results.setNumber(matcher.group(0).trim());
            results.setFlatNumber(matcher.group(0).trim());
            results.setPostalCode(matcher.group(matcher.groupCount() - 1).trim());
            results.setAddressType(addressType.getType().trim());
        }
        return results;
    }
}
