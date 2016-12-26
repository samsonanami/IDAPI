package com.fintech.orion.documentverification.strategy;

import com.fintech.orion.documentverification.common.Address.AddressDecodeResults;
import com.fintech.orion.documentverification.common.exception.DateComparatorException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MudithaJ on 12/26/2016.
 */
public class OperationDateComparator  implements DataValidationStrategy {

    @Override
    public ValidationResult doOperation(String base, String compare){
        ValidationResult
                result = new ValidationResult(false, "");
        Date baseDate = null;
        Date compareDate = null;

        try {
            baseDate = this.decodeDate(base);
             compareDate = this.decodeDate(compare);
            if (baseDate.equals(compareDate)){
                result.setStatus(true);
            }

        } catch (DateComparatorException e) {
            e.printStackTrace();
        }


        return result;
    }
    public Date decodeDate(String date) throws DateComparatorException{

        Date dateResult;
        String month;
        String dateOfTheMonth;
        String year;
        String stringDate;
        DecimalFormat formatter = new DecimalFormat("00");
        String regularExpression = "^(\\d{2})(\\w{3})\\/(\\w{3})(\\d{2})";
         try{
             date = date.replace(" ","");
            Pattern pattern = Pattern.compile(regularExpression);
            Matcher matcher = pattern.matcher(date);
             matcher.matches();
            if (matcher.matches()) {
                dateOfTheMonth = matcher.group(1).trim();
                month = formatter.format(this.getMonthNumber(matcher.group(2).trim()));
                year = this.getYear(matcher.group(4).trim());
                stringDate = dateOfTheMonth + month + year;
                DateFormat df = new SimpleDateFormat("ddmmyyyy");
                dateResult = df.parse(stringDate);

            } else {
                DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
                dateResult = df.parse(date);

            }


            return dateResult;
        }catch (ParseException e)
         {
             throw new DateComparatorException("Cannot parse given date"+date,e);
         }
    }

    public int getMonthNumber(String monthName)
    {
        DateTimeFormatter format  =  DateTimeFormat.forPattern("MMM");
        DateTime instance        = format.withLocale(Locale.ENGLISH).parseDateTime(monthName);

        return  instance.getMonthOfYear();
    }
    public String getYear(String yearDigit)
    {
        int yearNow;
        int yearDigitValue= Integer.parseInt(yearDigit);
        String fourDigitYear;
        String decadeDigit;
        int decadeDigitNow;

        DateFormat df = new SimpleDateFormat("yyyy");
        String formattedDate = df.format(Calendar.getInstance().getTime());
        yearNow = Integer.parseInt(formattedDate.substring(1,4));
        decadeDigitNow = Integer.parseInt(formattedDate.substring(0, 2));


         if(yearDigitValue >= yearNow)
         {
           decadeDigit = Integer.toString(decadeDigitNow-1);
         }else
         {
             decadeDigit=  Integer.toString(decadeDigitNow);

         }

         fourDigitYear = decadeDigit+ Integer.toString(yearDigitValue);


        return fourDigitYear;
    }
}
