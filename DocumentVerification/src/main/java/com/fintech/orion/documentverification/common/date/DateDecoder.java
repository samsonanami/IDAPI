package com.fintech.orion.documentverification.common.date;

import com.fintech.orion.documentverification.common.date.strategy.BasicDateDecodingStrategy;
import com.fintech.orion.documentverification.common.date.strategy.DateDecodingStrategy;
import com.fintech.orion.documentverification.common.exception.DateDecoderException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class deocdes the data in to elements Month,Year and Date
 * Created by sasitha on 12/29/16.
 *
 */
public class DateDecoder {

    @Autowired
    private List<DateTypeConfiguration> dateTypeConfigurationList;

    public Date decodeDate(String date) throws DateDecoderException {
        DateDecodingStrategy decodingStrategy = getDateDecodingStrategy(date);
        return decodingStrategy.decodeDate(date);
    }

    private DateDecodingStrategy getDateDecodingStrategy(String givenDate){
        DateDecodingStrategy strategy = new BasicDateDecodingStrategy();
        for (DateTypeConfiguration dateTypeConfiguration : dateTypeConfigurationList){
            Pattern pattern = Pattern.compile(dateTypeConfiguration.getDateTypeRegex());
            Matcher matcher = pattern.matcher(givenDate);
            if(matcher.matches()){
                strategy = dateTypeConfiguration.getStrategy();
            }
        }
        return strategy;
    }
}
