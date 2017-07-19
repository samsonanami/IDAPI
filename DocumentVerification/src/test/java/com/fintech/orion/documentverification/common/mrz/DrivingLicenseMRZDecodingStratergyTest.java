package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.DrivingLicenseMRZDecodingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.HashMap;

/**
 * Created by MudithaJ on 12/15/2016.
 */
public class DrivingLicenseMRZDecodingStratergyTest {

    private String mrz;
    @InjectMocks
    private DrivingLicenseMZRDecodingStrategy strategy;
    private MRZDecodeResults expectedResult;
    @Spy
    private HashMap<String, MRZItemProperty> mrzItemProperty;

    @Before
    public void setup() {


        strategy = new DrivingLicenseMZRDecodingStrategy();
        expectedResult = new MRZDecodeResults();
        MockitoAnnotations.initMocks(this);
        this.mockConfigueProperties();


        mrz = "KULAR757254PS9RT";

    }

    public void mockConfigueProperties() {
        DrivingLicenseMZRHelper helper = new DrivingLicenseMZRHelper();


        Mockito.when(mrzItemProperty.get("SurName")).thenReturn(helper.getMRZSurNameSystemProperty());
        Mockito.when(mrzItemProperty.get("DecadeDigitOfBirthYear")).thenReturn(helper.getMRZDecadeDigitOfBirthYearSystemProperty());
        Mockito.when(mrzItemProperty.get("DateofBirthMonth")).thenReturn(helper.getDateofBirthMonthSystemProperty());
        Mockito.when(mrzItemProperty.get("DateWithinTheBirthMonth")).thenReturn(helper.getMRZRDateWithinTheBirthMonthSystemProperty());
        Mockito.when(mrzItemProperty.get("DateofBirthYear")).thenReturn(helper.getMRZDateofBirthYearSystemProperty());
        Mockito.when(mrzItemProperty.get("InitialsOfTheFirstName")).thenReturn(helper.getMRZInitialsOfTheFirstNameSystemProperty());

    }

    @Test
    public void should_Return_Valid_First_Five_SurName_Characters() throws DrivingLicenseMRZDecodingException {
        expectedResult.setSurname("KULAR");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getSurname(), results.getSurname());
    }

    @Test
    public void should_Return_Valid_Decade_Digit_from_year() throws DrivingLicenseMRZDecodingException {
        expectedResult.setDecadeDigitOfBirthYear("7");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDecadeDigitOfBirthYear(), results.getDecadeDigitOfBirthYear());
    }

    @Test
    public void should_Return_Valid_month_of_birth() throws DrivingLicenseMRZDecodingException {
        expectedResult.setDateofBirthMonth("07");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDateofBirthMonth(), results.getDateofBirthMonth());
    }

    @Test
    public void should_Return_Date_Within_The_Month_Of_Birth() throws DrivingLicenseMRZDecodingException {
        expectedResult.setDateWithinTheBirthMonth("25");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDateWithinTheBirthMonth(), results.getDateWithinTheBirthMonth());
    }

    @Test
    public void should_Return_Valid_Year_Digit_From_The_Year_Of_Birth() throws DrivingLicenseMRZDecodingException {

        expectedResult.setDateofBirthYear("4");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDateofBirthYear(), results.getDateofBirthYear());
    }

    @Test
    public void should_Return_Valid_The_First_Two_Initials_Of_The_First_Names() throws DrivingLicenseMRZDecodingException {

        expectedResult.setInitialsOfTheFirstName("PS");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getInitialsOfTheFirstName(), results.getInitialsOfTheFirstName());
    }

    @Test
    public void should_Return_Valid_sex() throws DrivingLicenseMRZDecodingException {
        expectedResult.setSex("F");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getSex(), results.getSex());
    }


}
