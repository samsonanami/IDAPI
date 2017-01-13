package com.fintech.orion.documentverification.common.address;

import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MudithaJ on 12/16/2016.
 */
public class AddressValidateTest {
    private String addressTypeOne;
    private String addressTypeTwo;
    @InjectMocks
    private ValidateAddress validatedAddress;
    @Spy
    private HashMap<String, AddressType> AddressTypeProperty;

    @Before
    public void setup() {

        validatedAddress = new ValidateAddress();

        addressTypeOne = "42, Doncaster Road,Braithwell ROTHERHAM, S66 7BB";
        addressTypeTwo = "42 Doncaster Road Braithwell ROTHERHAM S66 7BB";
        MockitoAnnotations.initMocks(this);
        this.mockConfigurableProperties();
    }

    public void mockConfigurableProperties() {
        AddressHelper helper = new AddressHelper();

        List<AddressType> types = Arrays.asList(helper.mockAddressTypeOne(), helper.mockAddressTypeTwo());
        Mockito.when(AddressTypeProperty.values()).thenReturn(types);
    }

    @Test
    public void Should_return_Valid_AddressType_Class_For_Supported_format() throws AddressValidatingException {
        String address = "427BB";
        AddressType addressType = new AddressType();
        AddressType resultAddressType = validatedAddress.validate(address);
        Assert.assertEquals("0", resultAddressType.getType());
    }

    @Test
    public void Should_return_false_when_Address_is_Valid_TypeOne() throws AddressValidatingException {
        String address = "42, Doncaster Road,Braithwell ROTHERHAM ,S66 7BB";
        AddressType expectedAddressType = new AddressType();
        AddressType resultAddressType = validatedAddress.validate(address);
        Assert.assertEquals("1", resultAddressType.getType());
    }

    @Test
    public void Should_return_false_when_Address_is_Valid_TypeTwo() throws AddressValidatingException {
        String address = "Flat 10,Downing Court ,60 Gainsborough Road ,LONDON ,N12 8BN";
        AddressType expectedAddressType = new AddressType();
        AddressType resultAddressType = validatedAddress.validate(address);
        Assert.assertEquals("2", resultAddressType.getType());
    }

}