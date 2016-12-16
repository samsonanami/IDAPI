package com.fintech.orion.documentverification.common.mrz;

import org.slf4j.Logger;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;

/**
 * Created by MudithaJ on 12/15/2016.
 */
public class DrivingLicenseMZRDecodingStrategy  implements MRZDecodingStrategy{

    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger( DrivingLicenseMZRDecodingStrategy.class);
    public  MRZDecodeResults decode(String mrz)
    {
        MRZDecodeResults results = new MRZDecodeResults();
        try {
            mrz = mrz.replaceAll("\\s+", "");
            results.setSurname(this.decodeFirstnamesOfSurName(mrz));
            results.setDecadeDigitOfBirthYear(this.decodeDecadeDigitFromYearOfBirth(mrz));
            results.setDateofBirthMonth(this.decodeDateofBirthMonth(mrz));
            results.setDateWithinTheBirthMonth(this.decodeDateWithinTheBirthMonth(mrz));
            results.setDateofBirthYear(this.decodeDateofBirthYear(mrz));
            results.setInitialsOfTheFirstName(this.decodeInitialsOfFirstName(mrz));

       }
        catch(NullPointerException e)
        {
            LOGGER.error("MRZ Decoding fail for -"+mrz);
            results = null;

        }
        return results;
    }

    private String decodeFirstnamesOfSurName(String mrz)
    {
        String surname = mrz.substring(0,5);
        surname= surname.replaceAll("9","");
        return surname;
    }
    private String decodeDecadeDigitFromYearOfBirth(String mrz)
    {
        String decadeDigit = String.valueOf(mrz.charAt(6-1));
        return decadeDigit ;
    }

    private String decodeDateofBirthMonth(String mrz)
    {
        DecimalFormat formatter = new DecimalFormat("00");
        String month = mrz.substring(7-1,8);
        if(Integer.parseInt(month) > 12)
        {
            month = formatter.format(Integer.parseInt(month)-50);
        }
        return month;
    }

    private String decodeDateWithinTheBirthMonth(String mrz)
    {
        String date = mrz.substring(9-1,10);

        return  date;
    }

    private String decodeDateofBirthYear(String mrz)
    {
        String year = String.valueOf(mrz.charAt(11 - 1));
        return year ;
    }
    private String decodeInitialsOfFirstName(String mrz)
    {
        String initials = mrz.substring(12-1,13);
        return  initials;
    }




}
