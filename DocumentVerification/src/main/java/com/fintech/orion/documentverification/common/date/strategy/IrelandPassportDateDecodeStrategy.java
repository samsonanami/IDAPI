package com.fintech.orion.documentverification.common.date.strategy;

import com.fintech.orion.documentverification.common.exception.DateDecoderException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sasitha on 1/30/17.
 *
 */
public class IrelandPassportDateDecodeStrategy implements DateDecodingStrategy {

    private static final String DATE_REGEX = "^(\\d{2})";
    private static final String MONTH_REGEX = "(/.*\\w{3})";
    private static final String YEAR_REGEX = "(\\d{2})$";

    private static final String IRELAND_PASSPORT_MRZ_DATE_FORMAT = "^\\d{6}$";

    @Override
    public Date decodeDate(String date) throws DateDecoderException {
        if(date == null){
            throw new DateDecoderException("Cannot decode null date");
        }
        Date decodedDate = new Date();
        Pattern pattern = Pattern.compile(IRELAND_PASSPORT_MRZ_DATE_FORMAT);
        Matcher matcher = pattern.matcher(date);

        if (matcher.find()){
            decodedDate = decodeIrelandPassportMRZDate(date);
        }else{
            decodedDate = decodeIrelandPassporVIZDate(date);
        }
        return decodedDate;
    }
    private Date decodeIrelandPassporVIZDate(String date) throws DateDecoderException {
        try {
            String datePart = getFirstMatchingSectionFromString(DATE_REGEX, date);
            String monthPart = getFirstMatchingSectionFromString(MONTH_REGEX, date).replace("/","").trim();
            String yearPart = getFirstMatchingSectionFromString(YEAR_REGEX, date);
            String monthNumber = getMonthNumber(monthPart);
            String fullYear = getYear(yearPart);
            String fullDate = datePart+monthNumber+fullYear;
            DateFormat df = new SimpleDateFormat("ddMMyyyy");
            return df.parse(fullDate);
        } catch (ParseException e) {
            throw new DateDecoderException("Unable to decode the given date : " + date, e);
        }

    }

    private Date decodeIrelandPassportMRZDate(String date) throws DateDecoderException {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new DateDecoderException("Unable to decode the given date: " + date, e);
        }
    }
    private String getFirstMatchingSectionFromString(String regex, String fullString){
        String extractedPart = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullString);
        if (matcher.find()){
            extractedPart = matcher.group(1).trim();
        }
        return extractedPart;
    }
    private String getMonthNumber(String monthName) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputFormat.parse(monthName));
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM");
        return outputFormat.format(calendar.getTime());
    }

    private String getYear(String yearDigit) throws ParseException {
        DateFormat twoDigitYearFormat = new SimpleDateFormat("yy");
        Date date = null;
        date = twoDigitYearFormat.parse(yearDigit);
        DateFormat fourDigitYearFormat = new SimpleDateFormat("yyyy");
        return fourDigitYearFormat.format(date);
    }
}
