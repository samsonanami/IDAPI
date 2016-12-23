package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.CheckDigitFormationException;
import com.fintech.orion.documentverification.common.exception.DirivingLicenseMRZValidatingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.HashMap;

/**
 * Created by MudithaJ on 12/16/2016.
 */
public class DrivingLicenseMRZValidateTest {

    private String mrz;
    @InjectMocks
    private ValidateDrivingLicence validateDrivingLicenceMRZ;

    @InjectMocks
    private PassportMRZDecodingStrategy strategy;

    @Spy
    private HashMap<String,MRZItemProperty> mrzItemProperty;

    @Before
    public void setup(){

        validateDrivingLicenceMRZ = new ValidateDrivingLicence();
        MockitoAnnotations.initMocks(this);
        this.mockConfiguration();
        mrz = "KULAR757254PS9RT";

    }

    private void mockConfiguration()
    {
        DrivingLicenseMZRHelper helper = new DrivingLicenseMZRHelper();
        Mockito.when(mrzItemProperty.get("MZRLength")).thenReturn(helper.getMRZRLenght());
    }
    @Test
    public void shouldReturnValidateTrueForValidMRZ() throws DirivingLicenseMRZValidatingException
    {
        Assert.assertEquals(validateDrivingLicenceMRZ.validate(mrz).getValidationResult(),"true");
    }


    @Test
    public void shouldReturnValidateFalseForShortMRZ() throws DirivingLicenseMRZValidatingException
    {
        mrz =  "KULAR757254PS9R";
        Assert.assertEquals(validateDrivingLicenceMRZ.validate(mrz).getValidationResult(),"false");
    }

}

