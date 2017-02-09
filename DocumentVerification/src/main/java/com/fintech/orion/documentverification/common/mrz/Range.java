package com.fintech.orion.documentverification.common.mrz;

/**
 * This class with contains the properties of range.
 * Range is some portion of a MRZ.
 * Created by MudithaJ on 11/28/2016.
 */
public class Range {

    private int start;
    private int end;

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    private int lineNumber;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }


}
