package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.PassportMRZDecodeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.HashMap;

/**
 * Created by MudithaJ on 11/24/2016.
 */
public class PassportMRZDecodingStrategyTest {

    @InjectMocks
    private PassportMRZDecodingStrategy strategy;
    private MRZDecodeResults expectedResult;
    private String mrz;



    @Spy
    private HashMap<String,MRZItemProperty> mrzItemProperty;

    @Before
    public void setup(){
        strategy = new PassportMRZDecodingStrategy();
        expectedResult = new MRZDecodeResults();
        MockitoAnnotations.initMocks(this);
        mrz = "P<GBRSORRELL<<PHILIP<MARK<<<<<<<<<<<<<<<<<<<7613359992GBR5901205M2211097<<<<<<<<<<<<<<06";
        this.mockConfigProperties();
    }

    public void mockConfigProperties()
    {
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("SurName")).thenReturn(helper.getMRZSurNameSystemProperty());
        Mockito.when(mrzItemProperty.get("GivenNames")).thenReturn(helper.getMRZGivenNameSystemProperty());
        Mockito.when(mrzItemProperty.get("PassPortNumber")).thenReturn(helper.getMRZPassportNumberSystemProperty());
        Mockito.when(mrzItemProperty.get("Sex")).thenReturn(helper.getMRZSexSystemProperty());
        Mockito.when(mrzItemProperty.get("DateOfBirth")).thenReturn(helper.getMRZDateOfBirthSystemProperty());
        Mockito.when(mrzItemProperty.get("ExpireDate")).thenReturn(helper.getMRZDateofExpireSystemProperty());
        Mockito.when(mrzItemProperty.get("Nationality")).thenReturn(helper.getMRZPlaceOfissueSystemProperty());
        Mockito.when(mrzItemProperty.get("CheckDigitOne")).thenReturn(helper.getMRZCheckDigitPraseOneSystemProperty());
        Mockito.when(mrzItemProperty.get("CheckDigitOne")).thenReturn(helper.getMRZCheckDigitPraseOneSystemProperty());
        Mockito.when(mrzItemProperty.get("CheckDigitTwo")).thenReturn(helper.getMRZCheckDigitPraseTwoSystemProperty());
        Mockito.when(mrzItemProperty.get("CheckDigitThree")).thenReturn(helper.getMRZCheckDigitPraseThreeSystemProperty());
        Mockito.when(mrzItemProperty.get("CheckDigitFour")).thenReturn(helper.getMRZCheckDigitPraseFourSystemProperty());
        Mockito.when(mrzItemProperty.get("CheckDigitFive")).thenReturn(helper.getMRZCheckDigitPraseFiveSystemProperty());

    }

    @Test
    public void should_return_valid_sur_name() throws PassportMRZDecodeException{
        expectedResult.setSurname("SORRELL");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("SurName")).thenReturn(helper.getMRZSurNameSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getSurname(), results.getSurname());
    }
//
    @Test
    public void should_return_valid_given_name()throws PassportMRZDecodeException{
        expectedResult.setGivenName("PHILIP MARK");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("GivenNames")).thenReturn(helper.getMRZGivenNameSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getGivenName(), results.getGivenName());
    }

    @Test
    public void should_return_valid_passport_number() throws PassportMRZDecodeException{
        expectedResult.setPassPortNumber("761335999");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("PassPortNumber")).thenReturn(helper.getMRZPassportNumberSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getPassPortNumber(), results.getPassPortNumber());
    }


    @Test
    public void should_return_valid_sex() throws PassportMRZDecodeException{
        expectedResult.setSex("M");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("Sex")).thenReturn(helper.getMRZSexSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getSex(), results.getSex());
    }

    @Test
    public void should_return_valid_dateOfBirth() throws PassportMRZDecodeException{
        expectedResult.setDateOfBirth("590120");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("DateOfBirth")).thenReturn(helper.getMRZDateOfBirthSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDateOfBirth(), results.getDateOfBirth());
    }

    @Test
    public void should_return_valid_dateOfExpire() throws PassportMRZDecodeException{
        expectedResult.setDateofExpire("221109");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("ExpireDate")).thenReturn(helper.getMRZDateofExpireSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDateofExpire(), results.getDateofExpire());
    }


    @Test
    public void should_return_valid_place_of_issue()throws PassportMRZDecodeException {
        expectedResult.setPlaceOfIssue("GBR");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("Nationality")).thenReturn(helper.getMRZPlaceOfissueSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);

        Assert.assertEquals(expectedResult.getPlaceOfIssue(), results.getPlaceOfIssue());
    }

    @Test
    public void should_return_valid_checkDigitPraseOne() throws PassportMRZDecodeException{
        expectedResult.setCheckDigitPhraseOne("2");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("CheckDigitOne")).thenReturn(helper.getMRZCheckDigitPraseOneSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getCheckDigitPhraseOne(), results.getCheckDigitPhraseOne());
    }
    @Test
    public void should_return_valid_checkDigitPraseTwo() throws PassportMRZDecodeException{
        expectedResult.setCheckDigitPhraseTwo("5");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("CheckDigitTwo")).thenReturn(helper.getMRZCheckDigitPraseTwoSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getCheckDigitPhraseTwo(), results.getCheckDigitPhraseTwo());
    }
    @Test
    public void should_return_valid_checkDigitPraseThree() throws PassportMRZDecodeException{
        expectedResult.setCheckDigitPhraseThree("7");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("CheckDigitThree")).thenReturn(helper.getMRZCheckDigitPraseThreeSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getCheckDigitPhraseThree(), results.getCheckDigitPhraseThree());
    }
    @Test
    public void should_return_valid_checkDigitPraseFour() throws PassportMRZDecodeException{
        expectedResult.setCheckDigitPhraseFour("0");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("CheckDigitFour")).thenReturn(helper.getMRZCheckDigitPraseFourSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getCheckDigitPhraseFour(), results.getCheckDigitPhraseFour());
    }
    @Test
    public void should_return_valid_dateOfFive() throws PassportMRZDecodeException{
        expectedResult.setCheckDigitPhraseFive("6");
        PassportMRZHelper helper = new PassportMRZHelper();
        Mockito.when(mrzItemProperty.get("CheckDigitFive")).thenReturn(helper.getMRZCheckDigitPraseFiveSystemProperty());
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getCheckDigitPhraseFive(), results.getCheckDigitPhraseFive());
    }


}
