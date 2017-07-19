package com.fintech.orion.documentverification.common.date.strategy;

import com.fintech.orion.documentverification.common.exception.DateDecoderException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sasitha on 3/23/17.
 */
public class UKDrivingLicenseDateDecodingStrategy implements DateDecodingStrategy  {

    private static final String UK_DL_MRZ_DATE_FORMAT = "^\\d{6}$";
    @Override
    public Date decodeDate(String date) throws DateDecoderException {
        Date decodedDate = new Date();
        Pattern pattern = Pattern.compile(UK_DL_MRZ_DATE_FORMAT);
        Matcher matcher = pattern.matcher(date);

        if (matcher.find()){
            decodedDate = decodeUKDLMRZDate(date);
        }else{
            decodedDate = decodeUKDLVIZDate(date);
        }
        return decodedDate;
    }

    private Date decodeUKDLVIZDate(String date) throws DateDecoderException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new DateDecoderException("Unable to decode the given date: " + date, e);
        }
    }

    private Date decodeUKDLMRZDate(String date) throws DateDecoderException {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new DateDecoderException("Unable to decode the given date: " + date, e);
        }
    }
}
