package com.fintech.orion.documentverification.custom.datavalidation;

import com.fintech.orion.documentverification.common.mrz.MRZDecodeResults;

/**
 * Created by sasitha on 3/23/17.
 *
 */
public class MrzDecodeDataExtraction {

    private static final String DL_RESOURCE_NAME = "drivingLicenseFront";
    private static final String PASSPORT_RESOURCE_NAME = "passport";

    public String getMRZValue(String documentName, String ocrExtractionFieldName, MRZDecodeResults mrzDecodeResults){
        String result = "";
        if (documentName.equals(DL_RESOURCE_NAME)){
            result = getDrivingLicenseMRZDecodeResults(ocrExtractionFieldName, mrzDecodeResults);
        }else if (documentName.equals(PASSPORT_RESOURCE_NAME)){
            result = getPassportMRZDecodeResults(ocrExtractionFieldName, mrzDecodeResults);
        }
        return result;
    }

    private String getPassportMRZDecodeResults(String ocrExtractionField, MRZDecodeResults decodeResults){
        String mrzValue = "";
        switch (ocrExtractionField){
            case "surname":
                mrzValue = decodeResults.getSurname();
                break;
            case "date_of_birth":
                mrzValue = decodeResults.getDateOfBirth();
                break;
            case "sex":
                mrzValue = decodeResults.getSex();
                break;
            case "given_names":
                mrzValue = decodeResults.getGivenName();
                break;
            case "passport_number":
                mrzValue = decodeResults.getPassPortNumber();
                break;
            case "date_of_expiry":
                mrzValue = decodeResults.getDateofExpire();
                break;
            default:
                mrzValue = "";
                break;
        }
        return mrzValue;
    }

    private String getDrivingLicenseMRZDecodeResults(String ocrExtractionField, MRZDecodeResults decodeResults ){
        String mrzValue;
        switch (ocrExtractionField){
            case "surname":
                mrzValue = decodeResults.getSurname();
                break;
            case "date_of_birth":
                mrzValue = decodeResults.getDecadeDigitOfBirthYear()+decodeResults.getDateofBirthYear()+
                        decodeResults.getDateofBirthMonth() + decodeResults.getDateWithinTheBirthMonth();
                break;
            case "sex":
                mrzValue = decodeResults.getSex();
                break;
            case "given_names":
                mrzValue = decodeResults.getGivenName();
                break;
            default:
                mrzValue = "";
                break;
        }
        return mrzValue;
    }
}
