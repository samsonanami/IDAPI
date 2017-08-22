package com.fintech.orion.documentverification.common.date.strategy;

import com.fintech.orion.documentverification.common.exception.DateDecoderException;

import java.util.Date;

/**
 * Created by sasitha on 8/17/17.
 */
public class UKDrivingLicenseTemplate3DateDecodingStrategy implements DateDecodingStrategy {

    private static final String UK_DL3_MRZ_DATE_FORMAT = "^\\d{6}$";

    @Override
    public Date decodeDate(String date) throws DateDecoderException {
        Date decodedDate = new Date();
        DateFormatMatcher dateFormatMatcher = new DateFormatMatcher(UK_DL3_MRZ_DATE_FORMAT, date);
        if (dateFormatMatcher.match()){
            decodedDate = decodeUKDLMRZDate(date);
        }else{
            decodedDate = decodeUKDLVIZDate(date);
        }
        return decodedDate;
    }

    private Date decodeUKDLVIZDate(String date) throws DateDecoderException {
        DrivingLicenseDateDecoder vizDateDecoder = new DrivingLicenseDateDecoder(date, "dd-MM-yy");
        return vizDateDecoder.decode();
    }

    private Date decodeUKDLMRZDate(String date) throws DateDecoderException {
        DrivingLicenseDateDecoder mrzDateDecoder = new DrivingLicenseDateDecoder(date, "yyMMdd");
        return mrzDateDecoder.decode();
    }
}
