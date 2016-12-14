package com.fintech.orion.documentverification.common.mrz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by MudithaJ on 12/14/2016.
 */
public class PassPortMRZValidateTest {

    private String mrz;
    private ValidatePassPortMRZ  validatePassPortMRZ;

    @Before
    public void setup(){

      validatePassPortMRZ = new ValidatePassPortMRZ();

        mrz = "P<GBRSORRELL<<PHILIP<MARK<<<<<<<<<<<<<<<<<<<7613359992GBR5901205M2211097<<<<<<<<<<<<<<O6";

    }
    @Test
     public void shouldReturnValidateTrueForValidMRZ()
    {
        Assert.assertEquals(validatePassPortMRZ.validate(mrz),true);
    }

    @Test
    public void shouldReturnValidateFalseForShortMRZ()
    {
        mrz =  "P<GBRSORRELL<<PHILIP<MARK<<<<<<<<<<<<<<<<<<<7613359992GBR5901205M2211097<<<<<<<<<<<<O6";
        Assert.assertEquals(validatePassPortMRZ.validate(mrz),false);
    }

    @Test
    public void shouldReturnValidateFalseForNonePassportMRZ()
    {
        mrz =  "D<GBRSORRELL<<PHILIP<MARK<<<<<<<<<<<<<<<<<<<7613359992GBR5901205M2211097<<<<<<<<<<<<O6";
        Assert.assertEquals(validatePassPortMRZ.validate(mrz),false);
    }
}
