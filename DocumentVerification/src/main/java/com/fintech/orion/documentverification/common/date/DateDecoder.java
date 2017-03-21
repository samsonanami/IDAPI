package com.fintech.orion.documentverification.common.date;

import com.fintech.orion.documentverification.common.date.strategy.DateDecodingStrategy;
import com.fintech.orion.documentverification.common.exception.DateDecoderException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * This class deocdes the data in to elements Month,Year and Date
 * Created by sasitha on 12/29/16.
 *
 */
public class DateDecoder {

    @Autowired
    private BeanFactory beanFactory;

    public Date decodeDate(String date, String templateCategory) throws DateDecoderException {
        DateDecodingStrategy strategy = beanFactory.getBean(templateCategory, DateDecodingStrategy.class);
        return strategy.decodeDate(date);
    }
}