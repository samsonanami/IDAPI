package com.fintech.orion.dataabstraction.helper;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateTimestamp {

    private GenerateTimestamp() {}

    public static Timestamp timestamp() {
        Date date= new Date();
        return new Timestamp(date.getTime());
    }

    public static Date timestamp(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }
}
