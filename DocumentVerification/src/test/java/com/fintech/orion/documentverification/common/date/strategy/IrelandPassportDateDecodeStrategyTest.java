package com.fintech.orion.documentverification.common.date.strategy;

import com.fintech.orion.documentverification.common.exception.DateDecoderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sasitha on 1/30/17.
 *
 */
public class IrelandPassportDateDecodeStrategyTest {
    IrelandPassportDateDecodeStrategy irelandPassportDateDecodeStrategy = new IrelandPassportDateDecodeStrategy();
    DateFormat df = new SimpleDateFormat("ddMMyyyy");
    private String date;
    @Before
    public void setup(){
        date = "09 FEV /FEB 12";
    }


    @Test
    public void should_return_correct_date_if_correct_input_date_is_given()throws Exception{
        Date decodedDate = irelandPassportDateDecodeStrategy.decodeDate(date);
        Date expected = df.parse("09022012");
        Assert.assertEquals(expected, decodedDate);
    }

    @Test
    public void should_return_correct_date_if_expected_fields_in_the_input_string() throws Exception{
        date = "09 FEV&/ FEB 12";
        Date decodedDate = irelandPassportDateDecodeStrategy.decodeDate(date);
        Date expected = df.parse("09022012");
        Assert.assertEquals(expected, decodedDate);
    }

    @Test(expected = DateDecoderException.class)
    public void should_return_DateDecoderException_if_un_parsable_date_given()throws Exception{
        date = "09 BEB_/$%FEV 12";
        Date decodedDate = irelandPassportDateDecodeStrategy.decodeDate(date);
        Date expected = df.parse("09022012");
        Assert.assertEquals(expected, decodedDate);
    }
}