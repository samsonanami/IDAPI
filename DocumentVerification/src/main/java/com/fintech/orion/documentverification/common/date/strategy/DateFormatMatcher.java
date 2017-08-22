package com.fintech.orion.documentverification.common.date.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sasitha on 8/17/17.
 */
public class DateFormatMatcher {

    private String dateStringFormat;
    private String date;

    public DateFormatMatcher(String dateStringFormat, String date) {
        this.dateStringFormat = dateStringFormat;
        this.date = date;
    }

    public boolean match(){
        Pattern pattern = Pattern.compile(dateStringFormat);
        Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }
}
