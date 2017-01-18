package com.fintech.orion.documentverification.common.checkdigit;


import com.fintech.orion.documentverification.common.exception.CheckDigitFormationException;
import com.fintech.orion.documentverification.common.mrz.MRZItemProperty;
import com.fintech.orion.documentverification.common.mrz.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by MudithaJ on 11/28/2016.
 */
@Component
public class PassportCheckDigitFormation {

    private int moduler;
    private int checkDigitAphabteStartValue;
    private int checkDigitAphabteENDValue;
    private int secondStringStartIndex;
    private int mrzFirstLineCharacterCount;

    @Autowired
    @Qualifier("passportMRZConfigureList")
    private HashMap<String, MRZItemProperty> mrzItemProperty;

    public PassportCheckDigitFormation() {
        this.setCheckDigitAphabteStartValue(6);
        this.setModuler(10);
        this.setSecondStringStartIndex(44);
    }

    public CheckDigitResults calculateCheckdigit(String mrz) throws CheckDigitFormationException {
        try {
            this.mrzFirstLineCharacterCount = 44;
            Range[] checkDigitRangeArray = this.getCheckDigitRangeParameters();
            String fixedMRX = this.getfixedMRZ(mrz);

            CheckDigitResults results = new CheckDigitResults();

            results.setCheckDigitPraseOne(getCheckdigitPraseOne(fixedMRX, checkDigitRangeArray[0]));
            results.setCheckDigitPraseTwo(getCheckdigitPraseTwo(fixedMRX, checkDigitRangeArray[1]));
            results.setCheckDigitPraseThree(getCheckdigitPraseThree(fixedMRX, checkDigitRangeArray[2]));
            results.setCheckDigitPraseFour(getCheckdigitPraseFour(fixedMRX, checkDigitRangeArray[3]));
            results.setCheckDigitPraseFive(getCheckdigitPraseFive(fixedMRX, checkDigitRangeArray));


            return results;
        } catch (NullPointerException e) {
            throw new CheckDigitFormationException("Not well formatted passport MRZ or not well set configuration properties", e);
        }
    }

    private String getfixedMRZ(String mrz) {
        return mrz.replaceAll("\\s+", "");
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

    private Range[] getCheckDigitRangeParameters() {

        Range[] checkDigitRangeArray = new Range[4];

        checkDigitRangeArray[0] = this.convertConfigPropertiesToProcessableProperties("CheckDigitPraseOne");

        checkDigitRangeArray[1] = this.convertConfigPropertiesToProcessableProperties("CheckDigitPraseTwo");

        checkDigitRangeArray[2] = this.convertConfigPropertiesToProcessableProperties("CheckDigitPraseThree");

        checkDigitRangeArray[3] = this.convertConfigPropertiesToProcessableProperties("CheckDigitPraseFour");

        return checkDigitRangeArray;


    }

    public void setSecondStringStartIndex(int secondStringStartIndex) {
        this.secondStringStartIndex = secondStringStartIndex;
    }

    public void setModuler(int moduler) {
        this.moduler = moduler;
    }

    public void setCheckDigitAphabteStartValue(int checkDigitAphabteStartValue) {
        this.checkDigitAphabteStartValue = checkDigitAphabteStartValue;
    }

    public void setCheckDigitAphabteENDValue(int checkDigitAphabteENDValue) {
        this.checkDigitAphabteENDValue = checkDigitAphabteENDValue;
    }

    public int getCheckDigitAphabteENDValue() {
        return checkDigitAphabteENDValue;
    }

    public int getSecondStringStartIndex() {
        return secondStringStartIndex;
    }

    private int calculateCharacterDigitValue(char character) {
        int value;
        int subtituedValue;

        if (Character.isDigit(character)) {
            return Character.getNumericValue(character);
        } else if (character == '<') {
            return 0;
        } else {
            subtituedValue = (int) Character.toUpperCase('A') - this.checkDigitAphabteStartValue;

            value = (int) Character.toUpperCase(character) - subtituedValue;
            return value;
        }

    }

    private int calculateWeight(int position) {
        int positionWeightFactor;
        int value;

        positionWeightFactor = position % 3;

        switch (positionWeightFactor) {
            case 1:
                value = 7;
                break;
            case 2:
                value = 3;
                break;
            case 0:
                value = 1;
                break;
            default:
                value = 0;
                break;


        }

        return value;
    }

    private int calculateCheckDigit(String mrz, Range rangeObject) {

        String mrzPortion = mrz.substring(rangeObject.getStart(), rangeObject.getEnd());
        int index = 1;
        int checkDigit = 0;
        int checkDigitPerIndex = 0;

        for (char c : mrzPortion.toCharArray()) {
            checkDigitPerIndex = this.calculateCharacterDigitValue(c) * this.calculateWeight(index);
            checkDigit = checkDigit + checkDigitPerIndex;

            index++;
        }

        return checkDigit % this.moduler;
    }

    public String getCheckdigitPraseOne(String mrz, Range digitRange) {
        int checkDigitPrase;
        Range rangeForCheckdigitPrase = new Range();
        rangeForCheckdigitPrase.setStart(digitRange.getStart());
        rangeForCheckdigitPrase.setEnd(digitRange.getEnd());

        checkDigitPrase = this.calculateCheckDigit(mrz, rangeForCheckdigitPrase);
        return Integer.toString(checkDigitPrase);
    }

    public String getCheckdigitPraseTwo(String mrz, Range digitRange) {
        int checkDigitPrase;
        Range rangeForCheckdigitPrase = new Range();
        rangeForCheckdigitPrase.setStart(digitRange.getStart());
        rangeForCheckdigitPrase.setEnd(digitRange.getEnd());

        checkDigitPrase = this.calculateCheckDigit(mrz, rangeForCheckdigitPrase);
        return Integer.toString(checkDigitPrase);

    }

    public String getCheckdigitPraseThree(String mrz, Range digitRange) {
        int checkDigitPrase;
        Range rangeForCheckdigitPrase = new Range();
        rangeForCheckdigitPrase.setStart(digitRange.getStart());
        rangeForCheckdigitPrase.setEnd(digitRange.getEnd());

        checkDigitPrase = this.calculateCheckDigit(mrz, rangeForCheckdigitPrase);
        return Integer.toString(checkDigitPrase);
    }

    public String getCheckdigitPraseFour(String mrz, Range digitRange) {
        int checkDigitPrase;
        Range rangeForCheckdigitPrase = new Range();
        rangeForCheckdigitPrase.setStart(digitRange.getStart());
        rangeForCheckdigitPrase.setEnd(digitRange.getEnd());

        checkDigitPrase = this.calculateCheckDigit(mrz, rangeForCheckdigitPrase);
        return Integer.toString(checkDigitPrase);
    }

    public String getCheckdigitPraseFive(String mrz, Range[] range) {
        String checkDigitPraseFive;
        int checkDigitPrase;

        checkDigitPraseFive = mrz.substring(range[0].getStart(), range[0].getEnd())
                + getCheckdigitPraseOne(mrz, range[0])
                + mrz.substring(range[1].getStart(), range[1].getEnd())
                + getCheckdigitPraseTwo(mrz, range[1])
                + mrz.substring(range[2].getStart(), range[2].getEnd())
                + getCheckdigitPraseTwo(mrz, range[2])
                + mrz.substring(range[3].getStart(), range[3].getEnd())
                + getCheckdigitPraseTwo(mrz, range[3]);

        Range rangeForCheckdigitPrase = new Range();
        rangeForCheckdigitPrase.setStart(0);
        rangeForCheckdigitPrase.setEnd(checkDigitPraseFive.length() - 1);
        checkDigitPrase = this.calculateCheckDigit(checkDigitPraseFive, rangeForCheckdigitPrase);
        return Integer.toString(checkDigitPrase);
    }
}