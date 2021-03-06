package com.fintech.orion.documentverification.strategy;


import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.DateDecoderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by MudithaJ on 12/26/2016.
 */
public class OperationDateComparatorTest {

    @InjectMocks
    private OperationDateComparator comparator;

    @Mock
    private DateDecoder dateDecoder;

    private ValidationResult result;
    private DateFormat df;
    private String templateCategory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        result = new ValidationResult(false, "");
        df = new SimpleDateFormat("MM/dd/yyyy");
        templateCategory = "TODO:";
    }

    @Test

    public  void should_Return_True_for_similar_date_type_one() throws ParseException, DateDecoderException {
        Mockito.when(dateDecoder.decodeDate("23 jan/ jan 89", templateCategory)).thenReturn(df.parse("01/23/1989"));

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        result = comparator.doDataValidationOperation(dateFormat.parse("23.01.1989"), dateFormat.parse("23.01.1989"), templateCategory);


        Assert.assertTrue(result.isStatus());
    }

    @Test
    public void should_Return_False_for_different_date_type_one() throws ParseException, DateDecoderException {
        Mockito.when(dateDecoder.decodeDate("23 jan/ jan 89", templateCategory)).thenReturn(df.parse("01/23/1989"));
        Mockito.when(dateDecoder.decodeDate("24 jan/ jan 89", templateCategory)).thenReturn(df.parse("01/24/1989"));
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        result = comparator.doDataValidationOperation(dateFormat.parse("23.01.1989"), dateFormat.parse("24.01.1989"), templateCategory);


        Assert.assertFalse(result.isStatus());
    }

    @Test

    public void should_Return_True_for_Similar_date_type_Two() throws ParseException, DateDecoderException {
        Mockito.when(dateDecoder.decodeDate("23.01.1989", templateCategory)).thenReturn(df.parse("01/23/1989"));
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        result = comparator.doDataValidationOperation(dateFormat.parse("23.01.1989"), dateFormat.parse("23.01.1989"), templateCategory);

        Assert.assertTrue(result.isStatus());
    }

    @Test

    public void should_Return_False_for_Different_date_type_Two() throws ParseException, DateDecoderException {
        Mockito.when(dateDecoder.decodeDate("23.02.1989", templateCategory)).thenReturn(df.parse("02/23/1989"));
        Mockito.when(dateDecoder.decodeDate("23.01.1989", templateCategory)).thenReturn(df.parse("01/23/1989"));
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        result = comparator.doDataValidationOperation(dateFormat.parse("23.02.1989"), dateFormat.parse("23.01.1989"), templateCategory);

        Assert.assertFalse(result.isStatus());
    }

    @Test

    public void should_Return_True_for_Simillar_date_type_one_and_two() throws ParseException, DateDecoderException {
        Mockito.when(dateDecoder.decodeDate("23 jan/ jan 89", templateCategory)).thenReturn(df.parse("01/23/1989"));
        Mockito.when(dateDecoder.decodeDate("23.01.1989", templateCategory)).thenReturn(df.parse("01/23/1989"));
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        result = comparator.doDataValidationOperation(dateFormat.parse("23.01.1989"), dateFormat.parse("23.01.1989"), templateCategory);

        Assert.assertTrue(result.isStatus());
    }

    @Test
    public void should_Return_true_for_date_type_two_without_back_slash() throws ParseException, DateDecoderException {
        Mockito.when(dateDecoder.decodeDate("23 jan jan 89", templateCategory)).thenReturn(df.parse("01/23/1989"));
        Mockito.when(dateDecoder.decodeDate("23.01.1989", templateCategory)).thenReturn(df.parse("01/23/1989"));
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        result = comparator.doDataValidationOperation(dateFormat.parse("23.01.1989"), dateFormat.parse("23.01.1989"), templateCategory);
        Assert.assertTrue(result.isStatus());
    }


}
