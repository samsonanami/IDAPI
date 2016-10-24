package com.fintech.orion.hermesagentservices.transmission.payload.model.jenidresultstring;

/**
 * Created by TharinduMP on 10/21/2016.
 */
public class Body {
    private MrzData[] mrzData;
    private RoiData[] roiData;

    public MrzData[] getMrzData() {
        return mrzData;
    }

    public void setMrzData(MrzData[] mrzData) {
        this.mrzData = mrzData;
    }

    public RoiData[] getRoiData() {
        return roiData;
    }

    public void setRoiData(RoiData[] roiData) {
        this.roiData = roiData;
    }
}
