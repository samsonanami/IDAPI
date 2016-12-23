package com.fintech.orion.documentverification.common.mrz;

/**
 * Created by MudithaJ on 12/1/2016.
 */
public class MRZItemProperty {

    private String itemName;
    private int startIndex;
    private int endIndex;
    private int mrzLineNumber;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getMrzLineNumber() {
        return mrzLineNumber;
    }

    public void setMrzLineNumber(int mrzLineNumber) {
        this.mrzLineNumber = mrzLineNumber;
    }
}