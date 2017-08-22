package com.fintech.orion.documentverification.common.date.strategy;

import com.fintech.orion.documentverification.common.exception.DateDecoderException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sasitha on 8/17/17.
 *
 */
public class DrivingLicenseDateDecoder {

    private static final int TWO_DIGHT_YEAR_MAX_RANGE = 100;

    private String dateString;
    private String dateStringFormat;


    public DrivingLicenseDateDecoder(String dateString, String dateStringFormat) {
        this.dateString = dateString;
        this.dateStringFormat = dateStringFormat;
    }

    public Date decode() throws DateDecoderException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateStringFormat);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -TWO_DIGHT_YEAR_MAX_RANGE);
        dateFormat.set2DigitYearStart(cal.getTime());
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new DateDecoderException("Unable to decode the given date: " + dateString, e);
        }
    }
}
