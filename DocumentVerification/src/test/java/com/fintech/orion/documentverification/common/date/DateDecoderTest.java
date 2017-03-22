package com.fintech.orion.documentverification.common.date;

import com.fintech.orion.documentverification.common.date.strategy.DateDecodingStrategy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.BeanFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sasitha on 1/30/17.
 *
 */
public class DateDecoderTest {


    private String dateType2;
    private String templateCategory;

    @Before
    public void setup(){

        dateType2 = "25.07.1974";

        MockitoAnnotations.initMocks(this);
        templateCategory = "template";
    }

    @Test
    public void should_return_correct_date_for_correct_input_of_date_type_1()throws Exception{
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DateDecodingStrategy strategy = mock(DateDecodingStrategy.class);
        when(strategy.decodeDate(dateType2)).thenReturn(now);

        BeanFactory beanFactory = mock(BeanFactory.class);
        when(beanFactory.getBean(templateCategory, DateDecodingStrategy.class)).thenReturn(strategy);

        DateDecoder decoder = new DateDecoder();
        decoder.setBeanFactory(beanFactory);

        Date actual = decoder.decodeDate(dateType2, templateCategory);
        assertThat(actual, is(now));

    }
}