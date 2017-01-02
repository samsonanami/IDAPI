package com.fintech.orion.documentverification.strategy;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

/**
 * Created by MudithaJ on 12/26/2016.
 */
public class OperationDateComparatorTest {

    OperationDateComparator comparator;
    ValidationResult result;

    @Before
    public void setup()
    {
        comparator =  new OperationDateComparator();
        result = new ValidationResult(false,"");
    }
    @Test
    public void should_Return_True_for_similar_date_type_one() throws ParseException
    {
     result= comparator.doOperation("23 jan/ jan 89","23 jan/ jan 89");

       Assert.assertTrue(result.isStatus());
    }
    @Test
    public void should_Return_False_for_different_date_type_one() throws ParseException
    {
        result= comparator.doOperation("23 jan/ jan 89","24 jan/ jan 89");

        Assert.assertFalse(result.isStatus());
    }
    @Test
       public void should_Return_True_for_Similar_date_type_Two() throws ParseException
    {
        result= comparator.doOperation("23.01.1989","23.01.1989");

        Assert.assertTrue(result.isStatus());
    }
    @Test
    public void should_Return_False_for_Different_date_type_Two() throws ParseException
    {
        result= comparator.doOperation("23.02.1989","23.01.1989");

        Assert.assertFalse(result.isStatus());
    }
    @Test
    public void should_Return_True_for_Simillar_date_type_one_and_two() throws ParseException
    {
        result= comparator.doOperation("23 jan/ jan 89","23.01.1989");

        Assert.assertTrue(result.isStatus());
    }
    @Test
    public void should_Return_true_for_date_type_two_without_back_slash() throws ParseException
    {
        result= comparator.doOperation("23 jan jan 89","23.01.1989");

        Assert.assertTrue(result.isStatus());
    }
    @Test
    public void should_Return_False_for_malforemd_date() throws ParseException
    {
        result= comparator.doOperation("23 jan 89","23.01.1989");

        Assert.assertFalse(result.isStatus());
    }

}
