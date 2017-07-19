package com.fintech.orion.documentverification.common.mrz.filter;

/**
 * Created by TharinduMP on 3/20/2017.
 */
public class FillerFilter implements Filter {

    @Override
    public String filter(String s) {
        return s.replace("<", "");
    }

}
