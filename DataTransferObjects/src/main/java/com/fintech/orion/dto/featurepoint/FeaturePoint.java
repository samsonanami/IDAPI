package com.fintech.orion.dto.featurepoint;

/**
 * Created by TharinduMP on 3/20/2017.
 */
public class FeaturePoint {

    private int startIndex;
    private int endIndex;
    private String name;

    public FeaturePoint(int startIndex, int endIndex, String name) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
