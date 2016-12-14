package com.fintech.orion.documentverification.common.checkdigit;

import com.fintech.orion.documentverification.common.checkdigit.CheckDigitResults;
import com.fintech.orion.documentverification.common.checkdigit.PassportCheckDigitFormation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by MudithaJ on 11/29/2016.
 */
public class PassportCheckDigitFormationTest {

    private PassportCheckDigitFormation formation;
    private CheckDigitResults expectedResult;
    private String mrz;


    @Before
    public void setup(){
        formation = new PassportCheckDigitFormation();
        expectedResult = new CheckDigitResults();
        mrz = "P<GBRSORRELL<<PHILIP<MARK<<<<<<<<<<<<<<<<<<<7613359992GBR5901205M2211097<<<<<<<<<<<<<<O4";

    }
    @Test
    public void should_return_valid_Checkdigit_Prase_One(){
        CheckDigitResults results = formation.calculateCheckdigit(mrz);
        expectedResult.setCheckdigitPraseOne("2");
        Assert.assertEquals(expectedResult.getCheckdigitPraseOne(),results.getCheckdigitPraseOne());
    }

    @Test
    public void should_return_valid_Checkdigit_Prase_Two(){
        CheckDigitResults results = formation.calculateCheckdigit(mrz);
        expectedResult.setCheckdigitPraseTwo("5");
        Assert.assertEquals(expectedResult.getCheckdigitPraseTwo(),results.getCheckdigitPraseTwo());
    }

    @Test
    public void should_return_valid_Checkdigit_Prase_Three(){
        CheckDigitResults results = formation.calculateCheckdigit(mrz);
        expectedResult.setCheckdigitPraseThree("7");
        Assert.assertEquals(expectedResult.getCheckdigitPraseThree(),results.getCheckdigitPraseThree());
    }

    @Test
    public void should_return_valid_Checkdigit_Prase_Four(){
        CheckDigitResults results = formation.calculateCheckdigit(mrz);
        expectedResult.setCheckdigitPraseFour("0");
        Assert.assertEquals(expectedResult.getCheckdigitPraseFour(),results.getCheckdigitPraseFour());
    }

    @Test
    public void should_return_valid_Checkdigit_Prase_Five(){
        CheckDigitResults results = formation.calculateCheckdigit(mrz);
        expectedResult.setCheckdigitPraseFive("4");
        Assert.assertEquals(expectedResult.getCheckdigitPraseFive(),results.getCheckdigitPraseFive());
    }


}
