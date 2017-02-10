package com.fintech.orion.documentverification.common.date.strategy;

import com.fintech.orion.documentverification.common.exception.DateDecoderException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sasitha on 1/30/17.
 *
 */
public class BasicDateDecodingStrategy implements DateDecodingStrategy{

    private String dateFormat;

    @Override
    public Date decodeDate(String date) throws DateDecoderException {
        if (dateFormat == null || dateFormat.isEmpty()){
            throw new DateDecoderException("No date format has been set ");
        }
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            throw new DateDecoderException("Unable to parse the given date ", e);
        }
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
