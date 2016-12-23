package com.fintech.orion.documentverification.common.Address;

import com.fintech.orion.documentverification.common.exception.AddressValidatingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MudithaJ on 12/16/2016.
 */
public class AddressDecoding {

    private AddressType addresstype;

    public AddressDecodeResults decode(String address) throws AddressValidatingException
    {

        ValidateAddress validater = new ValidateAddress();
        AddressDecodeResults results = new AddressDecodeResults();
        try {

            addresstype = validater.validate(address);
            if (addresstype.getType().equals("0")) {
                results.setAddressType("0");
            } else {
                results = devideToElement(addresstype, address);
            }

            return results;
        }
        catch(NullPointerException e)
        {
            throw new AddressValidatingException("Address validating failed number"+results.getAddressType(),e);
        }
    }
    public    AddressDecodeResults devideToElement(AddressType addressType,String address)
    {
        String regularExpression = addressType.getDecodeRegularExpression();
        regularExpression = addressType.getValidateRegularExpression();
        AddressDecodeResults results = new AddressDecodeResults();
        Pattern pattern = Pattern.compile(regularExpression);

        Matcher matcher =pattern.matcher(address);
          results.setNumber(matcher.group(0).trim());
        results.setFlatNumber(matcher.group(0).trim());
        results.setPostalCode(matcher.group(matcher.groupCount()-1).trim());
        results.setAddressType(addressType.getType().trim());

        return  results;
    }
}
