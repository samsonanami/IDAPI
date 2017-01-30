package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.exception.DrivingLicenseMRZDecodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * This class decodes the Driving license number in to elements.
 * Created by MudithaJ on 12/15/2016.
 */
@Component
public class DrivingLicenseMZRDecodingStrategy implements MRZDecodingStrategy {
    @Autowired
    @Qualifier("drivingLicenseMRZConfigureList")
    private HashMap<String, MRZItemProperty> mrzItemProperty;
    int mrzFirstLineCharacterCount;

    public MRZDecodeResults decode(String mrz) throws DrivingLicenseMRZDecodingException {
        MRZDecodeResults results = new MRZDecodeResults();
        try {
            this.mrzFirstLineCharacterCount = 0;
            String mrzToProcess = mrz.replaceAll("\\s+", "");
            Range rangeSurName = this.convertConfigPropertiesToProcessableProperties("SurName");
            results.setSurname(this.decodeFirstnamesOfSurName(mrzToProcess, rangeSurName));

            Range rangeDecadeDigitOfBirthYear = this.convertConfigPropertiesToProcessableProperties("DecadeDigitOfBirthYear");
            results.setDecadeDigitOfBirthYear(this.decodeDecadeDigitFromYearOfBirth(mrzToProcess, rangeDecadeDigitOfBirthYear));

            Range rangeDateofBirthMonth = this.convertConfigPropertiesToProcessableProperties("DateofBirthMonth");
            results.setDateofBirthMonth(this.decodeDateofBirthMonth(mrzToProcess, rangeDateofBirthMonth));

            Range rangeDateWithinTheBirthMonth = this.convertConfigPropertiesToProcessableProperties("DateWithinTheBirthMonth");
            results.setDateWithinTheBirthMonth(this.decodeDateWithinTheBirthMonth(mrzToProcess, rangeDateWithinTheBirthMonth));

            Range rangeDateofBirthYear = this.convertConfigPropertiesToProcessableProperties("DateofBirthYear");
            results.setDateofBirthYear(this.decodeDateofBirthYear(mrzToProcess, rangeDateofBirthYear));

            Range rangeInitialsOfTheFirstName = this.convertConfigPropertiesToProcessableProperties("InitialsOfTheFirstName");
            results.setInitialsOfTheFirstName(this.decodeInitialsOfFirstName(mrzToProcess, rangeInitialsOfTheFirstName));

        } catch (NullPointerException e) {
            throw new DrivingLicenseMRZDecodingException("Not well formatted drivingLicense MRZ or not well set configuration properties Exception " + mrz, e);
        }
        return results;
    }

    private Range convertConfigPropertiesToProcessableProperties(String key) {
        MRZItemProperty property = this.getItemProperties(key);
        Range range = new Range();
        if (property.getMrzLineNumber() == 2) {
            range.setStart(property.getStartIndex() + this.mrzFirstLineCharacterCount - 1);
            range.setEnd(property.getEndIndex() + this.mrzFirstLineCharacterCount);
        } else {
            range.setStart(property.getStartIndex() - 1);
            range.setEnd(property.getEndIndex());
        }

        return range;
    }

    private MRZItemProperty getItemProperties(String key) {
        return mrzItemProperty.get(key);
    }

    private String decodeFirstnamesOfSurName(String mrz, Range range) {
        int start = range.getStart();
        int end = range.getEnd();
        String surname = mrz.substring(start, end);
        surname = surname.replaceAll("9", "");
        return surname;
    }

    private String decodeDecadeDigitFromYearOfBirth(String mrz, Range range) {

        int start = range.getStart();
        return String.valueOf(mrz.charAt(start));
    }

    private String decodeDateofBirthMonth(String mrz, Range range) {

        int start = range.getStart();
        int end = range.getEnd();
        DecimalFormat formatter = new DecimalFormat("00");
        String month = mrz.substring(start, end);
        if (Integer.parseInt(month) > 12) {
            month = formatter.format(Integer.parseInt(month) - 50);
        }
        return month;
    }

    private String decodeDateWithinTheBirthMonth(String mrz, Range range) {

        int start = range.getStart();
        int end = range.getEnd();
        return mrz.substring(start, end);
    }

    private String decodeDateofBirthYear(String mrz, Range range) {

        int start = range.getStart();
        return String.valueOf(mrz.charAt(start));
    }

    private String decodeInitialsOfFirstName(String mrz, Range range) {
        int start = range.getStart();
        int end = range.getEnd();
        return mrz.substring(start, end);
    }


}
