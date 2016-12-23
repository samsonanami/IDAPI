package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.PassPortMRZValidateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.HashMap;

/**
 * Created by MudithaJ on 12/14/2016.
 *
 */
public class PassPortMRZValidateTest {

    private String mrz;
    @InjectMocks
    private ValidatePassPortMRZ  validatePassPortMRZ;

    @Spy
    private HashMap<String,MRZItemProperty> mrzItemProperty;
    @Before
    public void setup(){

      validatePassPortMRZ = new ValidatePassPortMRZ();
        MockitoAnnotations.initMocks(this);
        mrz = "P<GBRSORRELL<<PHILIP<MARK<<<<<<<<<<<<<<<<<<<7613359992GBR5901205M2211097<<<<<<<<<<<<<<O6";

    }
    @Test
     public void shouldReturnValidateTrueForValidMRZ() throws PassPortMRZValidateException
    {    PassportMRZHelper helper = new PassportMRZHelper();

        Mockito.when(mrzItemProperty.get("MRZFirstLineLength")).thenReturn(helper.getMRZFirstLineLengthSystemProperty());
        Mockito.when(mrzItemProperty.get("MRZSecondLineLength")).thenReturn(helper.getMRZFirstLineLengthSystemProperty());
        Assert.assertEquals(validatePassPortMRZ.validate(mrz).getValidationResult(),"true");
    }

    @Test
    public void shouldReturnValidateFalseForShortMRZ()  throws PassPortMRZValidateException
    {    PassportMRZHelper helper = new PassportMRZHelper();
        mrz =  "P<GBRSORRELL<<PHILIP<MARK<<<<<<<<<<<<<<<<<<<7613359992GBR5901205M2211097<<<<<<<<<<<<O6";
        Mockito.when(mrzItemProperty.get("MRZFirstLineLength")).thenReturn(helper.getMRZFirstLineLengthSystemProperty());
        Mockito.when(mrzItemProperty.get("MRZSecondLineLength")).thenReturn(helper.getMRZFirstLineLengthSystemProperty());
        Assert.assertEquals(validatePassPortMRZ.validate(mrz).getValidationResult(),"false");
    }

    @Test
    public void shouldReturnValidateFalseForNonePassportMRZ()  throws PassPortMRZValidateException
    { PassportMRZHelper helper = new PassportMRZHelper();
        mrz =  "D<GBRSORRELL<<PHILIP<MARK<<<<<<<<<<<<<<<<<<<7613359992GBR5901205M2211097<<<<<<<<<<<<O6";
        Mockito.when(mrzItemProperty.get("MRZFirstLineLength")).thenReturn(helper.getMRZFirstLineLengthSystemProperty());
        Mockito.when(mrzItemProperty.get("MRZSecondLineLength")).thenReturn(helper.getMRZFirstLineLengthSystemProperty());
        Assert.assertEquals(validatePassPortMRZ.validate(mrz).getValidationResult(),"false");
    }
}
