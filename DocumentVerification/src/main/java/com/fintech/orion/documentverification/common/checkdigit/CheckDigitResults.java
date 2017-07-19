package com.fintech.orion.documentverification.common.checkdigit;

/**
 * This class contains the result of ALl five check digit in Passport MRZ
 * Created by MudithaJ on 11/30/2016.
 */
public class CheckDigitResults {

    private String checkDigitPraseOne;
    private String checkDigitPraseTwo;
    private String checkDigitPraseThree;
    private String checkDigitPraseFour;
    private String checkDigitPraseFive;

    public String getCheckDigitPraseOne() {
        return checkDigitPraseOne;
    }

    public void setCheckDigitPraseOne(String checkDigitPraseOne) {
        this.checkDigitPraseOne = checkDigitPraseOne;
    }

    public String getCheckDigitPraseTwo() {
        return checkDigitPraseTwo;
    }

    public void setCheckDigitPraseTwo(String checkDigitPraseTwo) {
        this.checkDigitPraseTwo = checkDigitPraseTwo;
    }

    public String getCheckDigitPraseThree() {
        return checkDigitPraseThree;
    }

    public void setCheckDigitPraseThree(String checkDigitPraseThree) {
        this.checkDigitPraseThree = checkDigitPraseThree;
    }

    public String getCheckDigitPraseFour() {
        return checkDigitPraseFour;
    }

    public void setCheckDigitPraseFour(String checkDigitPraseFour) {
        this.checkDigitPraseFour = checkDigitPraseFour;
    }

    public String getCheckDigitPraseFive() {
        return checkDigitPraseFive;
    }

    public void setCheckDigitPraseFive(String checkDigitPraseFive) {
        this.checkDigitPraseFive = checkDigitPraseFive;
    }
}