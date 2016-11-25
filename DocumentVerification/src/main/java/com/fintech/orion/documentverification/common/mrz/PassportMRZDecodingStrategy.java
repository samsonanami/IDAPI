package com.fintech.orion.documentverification.common.mrz;

/**
 * Created by MudithaJ on 11/24/2016.
 */
public class PassportMRZDecodingStrategy implements MRZDecodingStrategy{

    @Override
    public MRZDecodeResults decode(String mrz) {
        MRZDecodeResults results = new MRZDecodeResults();

        results.setSurname(decodeSurName(mrz));
        results.setGivenName(decodeGivenName(mrz));
        results.setPassPortNumber(decodePassportNumber(mrz));
        results.setNationality(decodeNationality(mrz));
        results.setSex(decodeSex(mrz));
        results.setDateofExpire(decodeDateOfExpire(mrz));
        results.setIssuingAuthority(decodeIssuingAuthorithy(mrz));
        results.setPlaceOfBirth(decodePlaceOfBirth(mrz));
        results.setPlaceOfIssue(decodeDateOfIssue(mrz));
        results.setDateOfIssue(decodeDateOfIssue(mrz));

        return results;
    }
    private String decodeSurName(String mrz) {
          String surName = "SurName1";

        return surName ;
    }
    private String decodeGivenName(String mrz) {
         String givenName = "GivenName1";


        return givenName;
    }
    private String decodePassportNumber(String mrz) {
         String passPortNumber = "passportNumber1";

        return passPortNumber;
    }
    private String decodeNationality(String mrz) {
            String Nationality = "Natinality1";

        return Nationality;
    }
    private String decodeSex(String mrz) {
        String sex = "sex1";

        return sex;
    }
    private String decodeDateOfBirth(String mrz) {

        String dateOfBirth ="dateofbirth1";
        return dateOfBirth;
    }
    private String decodeDateOfExpire(String mrz) {
            String dateOfExpire = "dateofexpite1";

        return dateOfExpire;
    }

    private String decodeIssuingAuthorithy(String mrz) {
            String issuingAuthority = "issuingAuthority1";

        return issuingAuthority;
    }
    private String decodePlaceOfBirth(String mrz) {
            String placeOfBirth ="placeofBirth1";

        return placeOfBirth;
    }
    private String decodePlaceOfIssue(String mrz) {
            String placeofIssue = "placeofissue";

        return null;
    }
    private String decodeDateOfIssue(String mrz) {
            String dateOfIssue ="dateOfissue";

        return dateOfIssue;
    }
}
