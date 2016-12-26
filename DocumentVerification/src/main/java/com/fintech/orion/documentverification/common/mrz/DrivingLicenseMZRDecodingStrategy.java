package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.DrivingLicenseMRZDecodingException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Created by MudithaJ on 12/15/2016.
 */
@Component
public class DrivingLicenseMZRDecodingStrategy  implements MRZDecodingStrategy{
    @Autowired
    @Qualifier("drivingLicenseMRZConfigureList")
    private HashMap<String,MRZItemProperty> mrzItemProperty;
    int mrzFirstLineCharacterCount;
    public  MRZDecodeResults decode(String mrz) throws DrivingLicenseMRZDecodingException
    {
        MRZDecodeResults results = new MRZDecodeResults();
        try {
            this.mrzFirstLineCharacterCount = 0;
            mrz = mrz.replaceAll("\\s+", "");
            Range rangeSurName = this.convertConfigPropertiesToProcessableProperties("SurName");
            results.setSurname(this.decodeFirstnamesOfSurName(mrz,rangeSurName));

            Range rangeDecadeDigitOfBirthYear = this.convertConfigPropertiesToProcessableProperties("DecadeDigitOfBirthYear");
            results.setDecadeDigitOfBirthYear(this.decodeDecadeDigitFromYearOfBirth(mrz,rangeDecadeDigitOfBirthYear));

            Range rangeDateofBirthMonth = this.convertConfigPropertiesToProcessableProperties("DateofBirthMonth");
            results.setDateofBirthMonth(this.decodeDateofBirthMonth(mrz, rangeDateofBirthMonth));

            Range rangeDateWithinTheBirthMonth = this.convertConfigPropertiesToProcessableProperties("DateWithinTheBirthMonth");
            results.setDateWithinTheBirthMonth(this.decodeDateWithinTheBirthMonth(mrz,rangeDateWithinTheBirthMonth));

            Range rangeDateofBirthYear = this.convertConfigPropertiesToProcessableProperties("DateofBirthYear");
            results.setDateofBirthYear(this.decodeDateofBirthYear(mrz,rangeDateofBirthYear));

            Range rangeInitialsOfTheFirstName = this.convertConfigPropertiesToProcessableProperties("InitialsOfTheFirstName");
            results.setInitialsOfTheFirstName(this.decodeInitialsOfFirstName(mrz,rangeInitialsOfTheFirstName));

       }
        catch(NullPointerException e)
        {
           throw new DrivingLicenseMRZDecodingException("Not well formatted drivingLicense MRZ or not well set configuration properties Exception " +mrz,e);
        }
        return results;
    }
    private Range convertConfigPropertiesToProcessableProperties(String key)
    {
        MRZItemProperty property = this.getItemProperties(key);
        Range range = new Range();
        if(property.getMrzLineNumber() == 2 )
        {
            range.setStart(property.getStartIndex() +this.mrzFirstLineCharacterCount-1);
            range.setEnd(property.getEndIndex()+this.mrzFirstLineCharacterCount);
        }else
        {
            range.setStart(property.getStartIndex()-1);
            range.setEnd(property.getEndIndex());
        }

        return  range;
    }
    private MRZItemProperty getItemProperties(String key)
    {
        MRZItemProperty property  = mrzItemProperty.get(key);

        return property;
    }
    private String decodeFirstnamesOfSurName(String mrz,Range range)
    {
        int start =range.getStart();
        int end = range.getEnd();
        String surname = mrz.substring(start,end);
        surname= surname.replaceAll("9","");
        return surname;
    }
    private String decodeDecadeDigitFromYearOfBirth(String mrz,Range range)
    {

        int start =range.getStart();
        int end = range.getEnd();
        String decadeDigit = String.valueOf(mrz.charAt(start));
        return decadeDigit ;
    }

    private String decodeDateofBirthMonth(String mrz,Range range)
    {

        int start =range.getStart();
        int end = range.getEnd();
        DecimalFormat formatter = new DecimalFormat("00");
        String month = mrz.substring(start,end);
        if(Integer.parseInt(month) > 12)
        {
            month = formatter.format(Integer.parseInt(month)-50);
        }
        return month;
    }

    private String decodeDateWithinTheBirthMonth(String mrz,Range range)
    {

        int start =range.getStart();
        int end = range.getEnd();
        String date = mrz.substring(start,end);

        return  date;
    }

    private String decodeDateofBirthYear(String mrz,Range range)
    {

        int start =range.getStart();
        int end = range.getEnd();
        String year = String.valueOf(mrz.charAt(start));
        return year ;
    }
    private String decodeInitialsOfFirstName(String mrz,Range range)
    {
        int start =range.getStart();
        int end = range.getEnd();
        String initials = mrz.substring(start,end);
        return  initials;
    }




}
