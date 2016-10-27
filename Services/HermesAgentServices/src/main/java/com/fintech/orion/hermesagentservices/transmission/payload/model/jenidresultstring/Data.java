package com.fintech.orion.hermesagentservices.transmission.payload.model.jenidresultstring;

/**
 * Created by TharinduMP on 10/26/2016.
 */
public class Data {

    private MrzData[] mrzdata;
    private RoiData[] roidata;

    public MrzData[] getMrzdata() {
        return mrzdata;
    }

    public void setMrzdata(MrzData[] mrzdata) {
        this.mrzdata = mrzdata;
    }

    public RoiData[] getRoidata() {
        return roidata;
    }

    public void setRoidata(RoiData[] roidata) {
        this.roidata = roidata;
    }
}
