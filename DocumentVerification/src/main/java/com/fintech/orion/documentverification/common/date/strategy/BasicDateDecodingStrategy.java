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

    @Override
    public Date decodeDate(String date) throws DateDecoderException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return df.parse(date);
        } catch (ParseException e) {
            throw new DateDecoderException("Unable to parse the given date ", e);
        }
    }
}
