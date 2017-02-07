package com.fintech.orion.documentverification.common.date;

import com.fintech.orion.documentverification.common.date.strategy.DateDecodingStrategy;

/**
 * Created by sasitha on 1/30/17.
 *
 */
public class DateTypeConfiguration {

    private String dateTypeRegex;
    private DateDecodingStrategy strategy;


    public String getDateTypeRegex() {
        return dateTypeRegex;
    }

    public void setDateTypeRegex(String dateTypeRegex) {
        this.dateTypeRegex = dateTypeRegex;
    }

    public DateDecodingStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(DateDecodingStrategy strategy) {
        this.strategy = strategy;
    }
}
