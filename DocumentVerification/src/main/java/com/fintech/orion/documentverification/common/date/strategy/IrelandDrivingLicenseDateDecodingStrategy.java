package com.fintech.orion.documentverification.common.date.strategy;

import com.fintech.orion.documentverification.common.exception.DateDecoderException;

import java.util.Date;

/**
 * Created by sasitha on 3/23/17.
 */
public class IrelandDrivingLicenseDateDecodingStrategy implements DateDecodingStrategy  {

    private static final String IRELAND_DL_MRZ_DATE_FORMAT = "^\\d{6}$";
    @Override
    public Date decodeDate(String date) throws DateDecoderException {
        Date decodedDate = new Date();
        DateFormatMatcher dateFormatMatcher = new DateFormatMatcher(IRELAND_DL_MRZ_DATE_FORMAT, date);
        if (dateFormatMatcher.match()){
            decodedDate = decodeIrelandDLMRZDate(date);
        }else{
            decodedDate = decodeIrelandDLVIZDate(date);
        }
        return decodedDate;
    }

    private Date decodeIrelandDLVIZDate(String date) throws DateDecoderException {
        DrivingLicenseDateDecoder vizDateDecoder = new DrivingLicenseDateDecoder(date, "dd.MM.yy");
        return vizDateDecoder.decode();
    }

    private Date decodeIrelandDLMRZDate(String date) throws DateDecoderException {
        DrivingLicenseDateDecoder mrzDateDecoder = new DrivingLicenseDateDecoder(date, "yyMMdd");
        return mrzDateDecoder.decode();
    }
}
