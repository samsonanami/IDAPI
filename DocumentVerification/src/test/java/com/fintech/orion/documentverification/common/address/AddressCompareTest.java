package com.fintech.orion.documentverification.common.address;

import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by MudithaJ on 12/23/2016.
 */
public class AddressCompareTest {

    private String addressOne;
    private String addressTwo;

    @InjectMocks
    private AddressCompare result;
    @Mock
    private AddressDecoding decoder;

    @Before
    public void setup() throws AddressValidatingException {

        addressOne = "42, Doncaster Road,Braithwell ROTHERHAM, S66 7BB";
        addressTwo = "Flat 10,Downing Court ,60 Gainsborough Road ,LONDON ,N12 8BN";
        ;
        MockitoAnnotations.initMocks(this);
        this.mockConfigurableProperties();
        AddressHelper helper = new AddressHelper();
        Mockito.when(decoder.decode(addressOne)).thenReturn(helper.getTypeOneCorrectAddress());
        Mockito.when(decoder.decode(addressTwo)).thenReturn(helper.getTypeTwoCorrectAddress());
    }

    public void mockConfigurableProperties() throws AddressValidatingException {

    }

    @Test
    public void Should_return_true_for_similar_type_one_two_address() throws AddressValidatingException {
        AddressHelper helper = new AddressHelper();

        Mockito.when(decoder.decode(addressOne)).thenReturn(helper.getTypeOneCorrectAddress());
        AddressCompareResult expectedResult = new AddressCompareResult();
        expectedResult.setResult(true);
        Assert.assertEquals(expectedResult.isResult(), result.compare(addressOne, addressOne).isResult());
    }

    @Test
    public void Should_return_true_for_similar_type_two_two_address() throws AddressValidatingException {
        AddressHelper helper = new AddressHelper();

        Mockito.when(decoder.decode(addressTwo)).thenReturn(helper.getTypeTwoCorrectAddress());
        AddressCompareResult expectedResult = new AddressCompareResult();
        expectedResult.setResult(true);
        Assert.assertEquals(expectedResult.isResult(), result.compare(addressTwo, addressTwo).isResult());
    }

    @Test
    public void Should_return_true_for_Different_type_two_two_address() throws AddressValidatingException {
        AddressHelper helper = new AddressHelper();

        Mockito.when(decoder.decode(addressOne)).thenReturn(helper.getTypeOneCorrectAddress());
        Mockito.when(decoder.decode(addressTwo)).thenReturn(helper.getTypeTwoCorrectAddress());
        AddressCompareResult expectedResult = new AddressCompareResult();
        expectedResult.setResult(false);
        Assert.assertEquals(expectedResult.isResult(), result.compare(addressOne, addressTwo).isResult());
    }


}
