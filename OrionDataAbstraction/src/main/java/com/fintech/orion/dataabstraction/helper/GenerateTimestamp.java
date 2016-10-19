package com.fintech.orion.dataabstraction.helper;

import java.sql.Timestamp;
import java.util.Date;

public class GenerateTimestamp {

    private GenerateTimestamp() {}

    public static Timestamp timestamp() {
        Date date= new Date();
        return new Timestamp(date.getTime());
    }
}
