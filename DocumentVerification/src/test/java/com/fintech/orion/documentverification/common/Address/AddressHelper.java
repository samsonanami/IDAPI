package com.fintech.orion.documentverification.common.Address;

/**
 * Created by MudithaJ on 12/22/2016.
 */
public class AddressHelper {

    public AddressType mockAddressTypeOne()
    {
        AddressType type = new AddressType();

        type.setType("1");
        type.setValidateRegularExpression("^([^,]*,){3}[^,]*$");
        type.setDecodeRegularExpression("(.+?),(.+?),(.+?),([^,]*$)");

        return type;
    }

    public AddressType mockAddressTypeTwo()
    {
        AddressType type = new AddressType();

        type.setType("2");
        type.setValidateRegularExpression("^([^,]*,){4}[^,]*$");
        type.setDecodeRegularExpression("(.+?),(.+?),(.+?),(.+?),([^,]*$)");

        return type;
    }

    public AddressDecodeResults  getTypeOneCorrectAddress()
    {
        AddressDecodeResults result = new AddressDecodeResults();

        result.setAddressType("1");
        result.setNumber("42");
        result.setPostalCode("S66 7BB");
        return result;
    }

    public AddressDecodeResults  getTypeTwoCorrectAddress()
    {
        AddressDecodeResults result = new AddressDecodeResults();

        result.setAddressType("2");
        result.setNumber("Flat 10");
        result.setPostalCode("N12 8BN");
        return result;
    }
}
