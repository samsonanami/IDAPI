package com.fintech.orion.documentverification.common.mrz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by MudithaJ on 11/24/2016.
 */
public class PassportMRZDecodingStrategyTest {

    private PassportMRZDecodingStrategy strategy;
    private MRZDecodeResults expectedResult;
    private String mrz;

    @Before
    public void setup(){
        strategy = new PassportMRZDecodingStrategy();
        expectedResult = new MRZDecodeResults();
        mrz = "P<GBRSORRELL<<PHILIP<MARK<<<<<<<<<<<<<<<<<<<7613359992GBR5901205M2211097<<<<<<<<<<<<<<O6";

    }

    @Test
    public void should_return_valid_sur_name(){
        expectedResult.setSurname("SORRELL");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getSurname(), results.getSurname());
    }

    @Test
    public void should_return_valid_given_name(){
        expectedResult.setGivenName("PHILIP MARK");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getGivenName(), results.getGivenName());
    }

    @Test
    public void should_return_valid_passport_number(){
        expectedResult.setPassPortNumber("761335999");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getPassPortNumber(), results.getPassPortNumber());
    }


    @Test
    public void should_return_valid_sex(){
        expectedResult.setSex("M");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getSex(), results.getSex());
    }

    @Test
    public void should_return_valid_dateOfBirth(){
        expectedResult.setDateOfBirth("59012");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDateOfBirth(), results.getDateOfBirth());
    }

    @Test
    public void should_return_valid_dateOfExpire(){
        expectedResult.setDateofExpire("221109");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDateofExpire(), results.getDateofExpire());
    }


    @Test
    public void should_return_valid_place_of_issue(){
        expectedResult.setPlaceOfIssue("GBR");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getPlaceOfIssue(), results.getPlaceOfIssue());
    }

}
