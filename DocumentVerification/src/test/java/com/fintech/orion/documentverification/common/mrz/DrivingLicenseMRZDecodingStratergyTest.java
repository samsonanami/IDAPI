package com.fintech.orion.documentverification.common.mrz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by MudithaJ on 12/15/2016.
 */
public class DrivingLicenseMRZDecodingStratergyTest {

    private String mrz;
    private DrivingLicenseMZRDecodingStrategy strategy;
    private MRZDecodeResults expectedResult;

    @Before
    public void setup(){

        strategy = new DrivingLicenseMZRDecodingStrategy();
        expectedResult = new  MRZDecodeResults();
        mrz = "KULAR757254PS9RT";

    }
    @Test
    public void should_Return_Valid_First_Five_SurName_Characters()
    {
        expectedResult.setSurname("KULAR");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getSurname(),results.getSurname());
    }
    @Test
    public void should_Return_Valid_Decade_Digit_from_year()
    {
        expectedResult.setDecadeDigitOfBirthYear("7");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDecadeDigitOfBirthYear(),results.getDecadeDigitOfBirthYear());
    }
    @Test
    public void should_Return_Valid_month_of_birth()
    {
        expectedResult.setDateofBirthMonth("07");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDateofBirthMonth(),results.getDateofBirthMonth());
    }
    @Test
    public void should_Return_Date_Within_The_Month_Of_Birth()
    {
        expectedResult.setDateWithinTheBirthMonth("25");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDateWithinTheBirthMonth(),results.getDateWithinTheBirthMonth());
    }
    @Test
    public void should_Return_Valid_Year_Digit_From_The_Year_Of_Birth()
    {

        expectedResult.setDateofBirthYear("4");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getDateofBirthYear(),results.getDateofBirthYear());
    }
    @Test
    public void should_Return_Valid_The_First_Two_Initials_Of_The_First_Names()
    {

         expectedResult.setInitialsOfTheFirstName("PS");
        MRZDecodeResults results = strategy.decode(mrz);
        Assert.assertEquals(expectedResult.getInitialsOfTheFirstName(),results.getInitialsOfTheFirstName());
    }

}
