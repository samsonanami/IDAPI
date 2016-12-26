package com.fintech.orion.documentverification.common.exception;

/**
 * Created by MudithaJ on 12/26/2016.
 */
public class DateComparatorException extends Exception{


    public DateComparatorException( String message,Exception e) {

        super(message,e);
    }

    public DateComparatorException(Exception e) {

        super(e);
    }
}
