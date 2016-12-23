package com.fintech.orion.documentverification.common.mrz;

/**
 * Created by MudithaJ on 11/28/2016.
 */
public class Range {

    private int start;
    private int end;

    public int getLineNumber() {
        return LineNumber;
    }

    public void setLineNumber(int lineNumber) {
        LineNumber = lineNumber;
    }

    private int LineNumber;

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
