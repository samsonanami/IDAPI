package com.fintech.orion.documentverification.common.mrz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by MudithaJ on 12/16/2016.
 */
public class DrivingLicenseMRZValidateTest {

    private String mrz;
    private ValidateDrivingLicence validateDrivingLicenceMRZ;

    @Before
    public void setup(){

        validateDrivingLicenceMRZ = new ValidateDrivingLicence();

        mrz = "KULAR757254PS9RT";

    }
    @Test
    public void shouldReturnValidateTrueForValidMRZ()
    {
        Assert.assertEquals(validateDrivingLicenceMRZ.validate(mrz), true);
    }

    @Test
    public void shouldReturnValidateFalseForShortMRZ()
    {
        mrz =  "KULAR757254PS9R";
        Assert.assertEquals(validateDrivingLicenceMRZ.validate(mrz),false);
    }

}

