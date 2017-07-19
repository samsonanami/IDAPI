package com.fintech.orion.documentverification.common.checkdigit;

import com.fintech.orion.documentverification.common.exception.CheckDigitFormationException;
import com.fintech.orion.documentverification.common.mrz.MRZItemProperty;
import com.fintech.orion.documentverification.common.mrz.PassportMRZHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.HashMap;


/**
 * Created by MudithaJ on 11/29/2016.
 */
public class PassportCheckDigitFormationTest {
    @InjectMocks
    private PassportCheckDigitFormation formation;
    private CheckDigitResults expectedResult;
    private String mrz;

    @Spy
    private HashMap<String, MRZItemProperty> mrzItemProperty;

    @Before
    public void setup() {

        formation = new PassportCheckDigitFormation();
        expectedResult = new CheckDigitResults();
       // mrz = "P<GBRSORRELL<<PHILIP<MARK<<<<<<<<<<<<<<<<<<<7613359992GBR5901205M2211097<<<<<<<<<<<<<<O4";
        //mrz = "P<GBRKULARATNE<<PARINDA<SANJAYA<<<<<<<<<<<<<5127790629GBR7407251M2211226<<<<<<<<<<<<<<O4";
        mrz = "P<GBRKULARATNE<<SUREKHA<PRAHARSHINI<<<<<<<<<5127835573GBR7412235F2211226<<<<<<<<<<<<<<06";
        MockitoAnnotations.initMocks(this);
        this.mockConfigProperties();

    }

    public void mockConfigProperties() {
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("CheckDigitPraseOne")).thenReturn(helper.getMRZCalculateCheckDigitPraseOneSystemProperty());
        Mockito.when(mrzItemProperty.get("CheckDigitPraseTwo")).thenReturn(helper.getMRZCalculateCheckDigitPraseTwoSystemProperty());
        Mockito.when(mrzItemProperty.get("CheckDigitPraseThree")).thenReturn(helper.getMRZCalculateCheckDigitPraseThreeSystemProperty());
        Mockito.when(mrzItemProperty.get("CheckDigitPraseFour")).thenReturn(helper.getMRZCalculateCheckDigitPraseFourSystemProperty());
        Mockito.when(mrzItemProperty.get("CheckDigitPraseFive")).thenReturn(helper.getMRZCalculateCheckDigitPraseFiveSystemProperty());
    }

    @Test
    public void should_return_valid_Checkdigit_Prase_One() throws CheckDigitFormationException {
        CheckDigitResults results = formation.calculateCheckDigit(mrz);
        expectedResult.setCheckDigitPraseOne("3");
        Assert.assertEquals(expectedResult.getCheckDigitPraseOne(),results.getCheckDigitPraseOne());
    }

    @Test
    public void should_return_valid_Checkdigit_Prase_Two() throws CheckDigitFormationException {
        CheckDigitResults results = formation.calculateCheckDigit(mrz);
        expectedResult.setCheckDigitPraseTwo("5");
        Assert.assertEquals(expectedResult.getCheckDigitPraseTwo(), results.getCheckDigitPraseTwo());
    }

    @Test
    public void should_return_valid_Checkdigit_Prase_Three() throws CheckDigitFormationException {
        CheckDigitResults results = formation.calculateCheckDigit(mrz);
        expectedResult.setCheckDigitPraseThree("6");
        Assert.assertEquals(expectedResult.getCheckDigitPraseThree(),results.getCheckDigitPraseThree());
    }

    @Test
    public void should_return_valid_Checkdigit_Prase_Four() throws CheckDigitFormationException {
        CheckDigitResults results = formation.calculateCheckDigit(mrz);
        expectedResult.setCheckDigitPraseFour("0");
        Assert.assertEquals(expectedResult.getCheckDigitPraseFour(), results.getCheckDigitPraseFour());
    }

    @Test
    public void should_return_valid_Checkdigit_Prase_Five() throws CheckDigitFormationException {
        CheckDigitResults results = formation.calculateCheckDigit(mrz);
        expectedResult.setCheckDigitPraseFive("6");
        Assert.assertEquals(expectedResult.getCheckDigitPraseFive(),results.getCheckDigitPraseFive());
    }


}