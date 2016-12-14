package com.fintech.orion.documentverification.common.mrz;



/**
 * Created by MudithaJ on 11/24/2016.
 */
public class PassportMRZDecodingStrategy implements MRZDecodingStrategy{

    @Override
    public MRZDecodeResults decode(String mrz) {
        MRZDecodeResults results = new MRZDecodeResults();
        PassportDecodeItems items = new PassportDecodeItems();

         mrz = mrz.replaceAll("\\s+", "");

        results.setSurname(decodeSurName(mrz));
        results.setGivenName(decodeGivenName(mrz));
        results.setPassPortNumber(decodePassportNumber(mrz));
        results.setSex(decodeSex(mrz));
        results.setDateofExpire(decodeDateOfExpire(mrz));
        results.setPlaceOfIssue(decodePlaceOfIssue(mrz));
        results.setDateOfBirth(decodeDateOfBirth(mrz));

        return results;
    }
    private String decodeSurName(String mrz) {
          String surName = "SurName1";
          int start = 5;
          int end   = 44;

        surName = mrz.substring(start,end);
        int fillerCharacterIndex = surName.indexOf("<");
        surName = surName.substring(0,fillerCharacterIndex);


        return surName.trim() ;
    }
    private String decodeGivenName(String mrz) {
         String givenName = "GivenName1";
        int start = 5;
        int end   = 44;

        givenName= mrz.substring(start,end);
        int fillerCharacterIndex = givenName.indexOf("<");
        givenName = givenName.substring(fillerCharacterIndex+2,givenName.length());
        String names[] = givenName.split("<");
        givenName = "";
        for(String name:names)
        {
            if(name.isEmpty())
            {
                break;
            }
            givenName = givenName +" "+ name;
        }


        return givenName.trim();
    }
    private String decodePassportNumber(String mrz) {
         String passPortNumber = "passportNumber1";
        int start = 44;
        int end   = 53;

        passPortNumber = mrz.substring(start,end);

        return passPortNumber.trim();
    }
    private String decodeSex(String mrz) {
        String sex = "sex1";
        int start = 64;
        int end   = 65;

        sex = mrz.substring(start,end);

        return sex.trim();
    }
    private String decodeDateOfBirth(String mrz) {

        String dateOfBirth ="dateofbirth1";

        int start = 57;
        int end   = 62;

          dateOfBirth = mrz.substring(start,end);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM", Locale.ENGLISH);
//        LocalDate dateObj = LocalDate.parse(dateOfBirth,formatter);
//        String month = dateObj.toString();
//
//        formatter = DateTimeFormatter.ofPattern("LL", Locale.ENGLISH);
//        dateObj = LocalDate.parse(dateOfBirth,formatter);
//        String date =  dateObj.toString();
//
//        formatter = DateTimeFormatter.ofPattern("YY", Locale.ENGLISH);
//        dateObj = LocalDate.parse(dateOfBirth,formatter);
//        String year =  dateObj.toString();


        return dateOfBirth;

    }
    private String decodeDateOfExpire(String mrz) {
            String dateOfExpire = "dateofexpite1";
        int start = 65;
        int end   = 71;

        dateOfExpire = mrz.substring(start,end);

        return dateOfExpire.trim();

    }

    private String decodePlaceOfIssue(String mrz) {
            String issuingAuthority = "issuingAuthority1";
        int start = 54;
        int end   = 57;

        issuingAuthority = mrz.substring(start,end);
        return issuingAuthority;
    }

    private String decodeCheckDigitPraseOne(String mrz,Range range)
    {
        String checkDigitPraseOne ;
        int start = range.getStart();
        int end   = range.getEnd();

        checkDigitPraseOne = mrz.substring(start,end);
        return  checkDigitPraseOne;
    }
    private String decodeCheckDigitPraseTwo(String mrz,Range range)
    {
        String checkDigitPraseTwo ;
        int start = range.getStart();
        int end   = range.getEnd();

        checkDigitPraseTwo = String.valueOf(mrz.charAt(start));
        return  checkDigitPraseTwo;
    }
    private String decodeCheckDigitPraseThree(String mrz,Range range)
    {
        String checkDigitPraseThree ;
        int start = range.getStart();
        int end   = range.getEnd();

        checkDigitPraseThree = String.valueOf(mrz.charAt(start));
        return  checkDigitPraseThree;
    }
    private String decodeCheckDigitPraseFour(String mrz,Range range)
    {
        String checkDigitPraseFour ;
        int start = range.getStart();
        int end   = range.getEnd();

        checkDigitPraseFour = String.valueOf(mrz.charAt(start));
        return  checkDigitPraseFour;
    }
    private String decodeCheckDigitPraseFive(String mrz,Range range)
    {
        String checkDigitPraseFive ;
        int start = range.getStart();
        int end   = range.getEnd();

        checkDigitPraseFive = String.valueOf(mrz.charAt(start));
        return  checkDigitPraseFive;
    }


}
