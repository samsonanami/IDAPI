package com.fintech.orion.documentverification.common.date;

import com.fintech.orion.documentverification.common.date.strategy.BasicDateDecodingStrategy;
import com.fintech.orion.documentverification.common.date.strategy.DateDecodingStrategy;
import com.fintech.orion.documentverification.common.date.strategy.UKPassportDateDecodeStrategy;
import com.fintech.orion.documentverification.common.exception.DateDecoderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sasitha on 1/30/17.
 *
 */
public class DateDecoderTest {

    @InjectMocks
    private DateDecoder dateDecoder;

    private String dateType1;
    private String dateType2;
    private String templateCategory;

    private List<DateTypeConfiguration> dateTypeConfigurationListObj = new ArrayList<>();

    @Spy
    private List<DateTypeConfiguration> dateTypeConfigurationList =dateTypeConfigurationListObj;

    @Before
    public void setup(){

        dateType1 = "09 FEB /FEV 12";
        dateType2 = "25.07.1974";

        DateTypeConfiguration configuration1 = new DateTypeConfiguration();
        configuration1.setDateTypeRegex("^(\\d{2}) (\\w{3})(.*)(\\d{2})$");
        DateDecodingStrategy ukPassport = new UKPassportDateDecodeStrategy();
        configuration1.setStrategy(ukPassport);

        BasicDateDecodingStrategy simpleDateDecode = new BasicDateDecodingStrategy();
        simpleDateDecode.setDateFormat("dd.MM.yyyy");
        DateTypeConfiguration simpleConfig = new DateTypeConfiguration();
        simpleConfig.setDateTypeRegex("^(\\d{2}.(\\d{2}).(\\d{4}))$");
        simpleConfig.setStrategy(simpleDateDecode);

        dateTypeConfigurationListObj.add(configuration1);
        dateTypeConfigurationListObj.add(simpleConfig);

        MockitoAnnotations.initMocks(this);
        templateCategory = "TODO:";
    }

    @Test
    public void should_return_correct_date_for_correct_input_of_date_type_1()throws Exception{
        Date date = dateDecoder.decodeDate(dateType1, templateCategory);
        DateFormat df = new SimpleDateFormat("ddMMyyyy");
        Date expected = df.parse("09022012");
        Assert.assertEquals(expected, date);
    }

    @Test
    public void should_return_correct_date_for_correct_input_of_date_type_2()throws Exception{
        Date date = dateDecoder.decodeDate(dateType2, templateCategory);
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date expected = dateFormat.parse("25071974");
        Assert.assertEquals(expected, date);
    }

    @Test(expected = DateDecoderException.class)
    public void should_throw_DateDecoderException_if_invalid_date_is_given()throws Exception{
        Date date = dateDecoder.decodeDate("09/07/12", templateCategory);
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date expected = dateFormat.parse("09022012");
        Assert.assertEquals(expected, date);
    }

    @Test
    public void should_decode_date_type_1()throws Exception{
        Date date = dateDecoder.decodeDate("25 JUL /JUIL 74", templateCategory);
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date expected = dateFormat.parse("25071974");
        Assert.assertEquals(expected, date);
    }
}