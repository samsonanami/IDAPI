package com.fintech.orion.documentverification.common.date.strategy;

import com.fintech.orion.documentverification.common.exception.DateDecoderException;

import java.util.Date;

/**
 * Created by sasitha on 1/30/17.
 *
 */
public interface DateDecodingStrategy {

    Date decodeDate(String date) throws DateDecoderException;
}
