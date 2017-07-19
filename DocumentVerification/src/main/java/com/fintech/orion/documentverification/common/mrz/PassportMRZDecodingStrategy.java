package com.fintech.orion.documentverification.common.mrz;


import com.fintech.orion.documentverification.common.exception.MRZDecodingConfigurationException;
import com.fintech.orion.documentverification.common.exception.PassportMRZDecodeException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * This class will decode the Passport MRZ to elements.
 * Created by MudithaJ on 11/24/2016.
 */
@Component
public class PassportMRZDecodingStrategy implements MRZDecodingStrategy {


    @Resource(name="passportMRZConfigureList")
    private HashMap<String, MRZItemProperty> passportMRZConfigureList;

    private int mrzFirstLineCharacterCount;

    @Override
    public MRZDecodeResults decode(String mrz) throws PassportMRZDecodeException {
        MRZDecodeResults results = new MRZDecodeResults();
        this.mrzFirstLineCharacterCount = 44;
        String mrzToProcess = "";
        try {
            mrzToProcess = mrz.replaceAll("\\s+", "");
            mrzToProcess = this.fixCharacterMismatchinOCR(mrzToProcess);
            Range rangeSurName = convertConfigPropertiesToProcessableProperties("SurName");
            results.setSurname(decodeSurName(mrzToProcess, rangeSurName));

            Range rangeGivenName = convertConfigPropertiesToProcessableProperties("GivenNames");
            results.setGivenName(decodeGivenName(mrzToProcess, rangeGivenName));

            Range rangePassportNumber = convertConfigPropertiesToProcessableProperties("PassPortNumber");
            results.setPassPortNumber(decodePassportNumber(mrzToProcess, rangePassportNumber));

            Range sexRange = convertConfigPropertiesToProcessableProperties("Sex");
            results.setSex(decodeSex(mrzToProcess, sexRange));

            Range dateOfExpireRange = convertConfigPropertiesToProcessableProperties("ExpireDate");
            results.setDateofExpire(decodeDateOfExpire(mrzToProcess, dateOfExpireRange));

            Range placeOfIssueRange = convertConfigPropertiesToProcessableProperties("Nationality");
            results.setPlaceOfIssue(decodePlaceOfIssue(mrzToProcess, placeOfIssueRange));

            Range dateOfBirthRange = convertConfigPropertiesToProcessableProperties("DateOfBirth");
            results.setDateOfBirth(decodeDateOfBirth(mrzToProcess, dateOfBirthRange));

            Range checkDigitOneRange = convertConfigPropertiesToProcessableProperties("CheckDigitOne");
            results.setCheckDigitPhraseOne(decodeCheckDigitPraseOne(mrzToProcess, checkDigitOneRange));

            Range checkDigitTwoRange = convertConfigPropertiesToProcessableProperties("CheckDigitTwo");
            results.setCheckDigitPhraseTwo(decodeCheckDigitPraseTwo(mrzToProcess, checkDigitTwoRange));

            Range checkDigitThreeRange = convertConfigPropertiesToProcessableProperties("CheckDigitThree");
            results.setCheckDigitPhraseThree(decodeCheckDigitPraseThree(mrzToProcess, checkDigitThreeRange));

            Range checkDigitFourRange = convertConfigPropertiesToProcessableProperties("CheckDigitFour");
            results.setCheckDigitPhraseFour(decodeCheckDigitPraseFour(mrzToProcess, checkDigitFourRange));

            Range checkDigitFiveRange = convertConfigPropertiesToProcessableProperties("CheckDigitFive");
            results.setCheckDigitPhraseFive(decodeCheckDigitPraseFive(mrzToProcess, checkDigitFiveRange));


        } catch (MRZDecodingConfigurationException e) {
            throw new PassportMRZDecodeException("Unable to decode passport mrz : " + mrz, e);
        }
        return results;
    }

    private MRZItemProperty getItemProperties(String key) throws MRZDecodingConfigurationException {
        MRZItemProperty property = passportMRZConfigureList.get(key);
        if (property == null) {
            throw new MRZDecodingConfigurationException("Could not found configuration property for key :" + key);
        }

        return property;
    }

    private Range convertConfigPropertiesToProcessableProperties(String key) throws MRZDecodingConfigurationException {
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

    private String fixCharacterMismatchinOCR(String mrz) {
        StringBuilder builder = new StringBuilder(mrz);

        if (mrz.charAt(42) == 'o') {
            builder.setCharAt(42, '0');
        }
        if (mrz.charAt(43) == 'o') {
            builder.setCharAt(43, '0');
        }

        return builder.toString();
    }

    private String decodeSurName(String mrz, Range range) {
        String surName;
        int start = range.getStart();
        int end = range.getEnd();

        surName = mrz.substring(start, end);
        int fillerCharacterIndex = surName.indexOf("<");
        surName = surName.substring(0, fillerCharacterIndex);


        return surName.trim();
    }

    private String decodeGivenName(String mrz, Range range) {
        String givenName;
        int start = range.getStart();
        int end = range.getEnd();

        givenName = mrz.substring(start, end);
        int fillerCharacterIndex = givenName.indexOf("<");
        givenName = givenName.substring(fillerCharacterIndex + 2, givenName.length());
        String[] names = givenName.split("<");
        givenName = "";
        for (String name : names) {
            if (name.isEmpty()) {
                break;
            }
            givenName = givenName + " " + name;
        }


        return givenName.trim();
    }

    private String decodePassportNumber(String mrz, Range range) {
        String passPortNumber;
        int start = range.getStart();
        int end = range.getEnd();
        passPortNumber = mrz.substring(start, end);

        return passPortNumber.trim();
    }

    private String decodeSex(String mrz, Range range) {
        String sex;
        int start = range.getStart();
        int end = range.getEnd();


        sex = mrz.substring(start, end);

        return sex.trim();
    }

    private String decodeDateOfBirth(String mrz, Range range) {

        String dateOfBirth;

        int start = range.getStart();
        int end = range.getEnd();


        dateOfBirth = mrz.substring(start, end);


        return dateOfBirth.trim();

    }

    private String decodeDateOfExpire(String mrz, Range range) {
        String dateOfExpire;
        int start = range.getStart();
        int end = range.getEnd();


        dateOfExpire = mrz.substring(start, end);

        return dateOfExpire.trim();

    }

    private String decodePlaceOfIssue(String mrz, Range range) {
        String issuingAuthority;

        int start = range.getStart();
        int end = range.getEnd();


        issuingAuthority = mrz.substring(start, end);
        return issuingAuthority;
    }

    private String decodeCheckDigitPraseOne(String mrz, Range range) {
        String checkDigitPraseOne;
        int start = range.getStart();
        int end = range.getEnd();

        checkDigitPraseOne = mrz.substring(start, end);
        return checkDigitPraseOne;
    }

    private String decodeCheckDigitPraseTwo(String mrz, Range range) {
        String checkDigitPraseTwo;
        int start = range.getStart();

        checkDigitPraseTwo = String.valueOf(mrz.charAt(start));
        return checkDigitPraseTwo;
    }

    private String decodeCheckDigitPraseThree(String mrz, Range range) {
        String checkDigitPraseThree;
        int start = range.getStart();

        checkDigitPraseThree = String.valueOf(mrz.charAt(start));
        return checkDigitPraseThree;
    }

    private String decodeCheckDigitPraseFour(String mrz, Range range) {
        String checkDigitPraseFour;
        int start = range.getStart();

        checkDigitPraseFour = String.valueOf(mrz.charAt(start));
        return checkDigitPraseFour;
    }

    private String decodeCheckDigitPraseFive(String mrz, Range range) {
        String checkDigitPraseFive;
        int start = range.getStart();

        checkDigitPraseFive = String.valueOf(mrz.charAt(start));
        return checkDigitPraseFive;
    }


}
