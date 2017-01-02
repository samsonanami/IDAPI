package com.fintech.orion.documentverification.strategy;

import com.fintech.orion.documentverification.common.date.DateDecoder;
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
public class OperationDateComparator extends DateDecoder implements DataValidationStrategy {

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

}
