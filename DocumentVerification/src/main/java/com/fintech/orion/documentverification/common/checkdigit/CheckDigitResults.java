package com.fintech.orion.documentverification.common.checkdigit;

/**
 * This class contains the result of ALl five check digit in Passport MRZ
 * Created by MudithaJ on 11/30/2016.
 */
public class CheckDigitResults {

    private String CheckdigitPraseOne;
    private String CheckdigitPraseTwo;
    private String CheckdigitPraseThree;
    private String CheckdigitPraseFour;
    private String CheckdigitPraseFive;

    public String getCheckdigitPraseOne() {
        return CheckdigitPraseOne;
    }

    public void setCheckdigitPraseOne(String checkdigitPraseOne) {
        CheckdigitPraseOne = checkdigitPraseOne;
    }

    public String getCheckdigitPraseTwo() {
        return CheckdigitPraseTwo;
    }

    public void setCheckdigitPraseTwo(String checkdigitPraseTwo) {
        CheckdigitPraseTwo = checkdigitPraseTwo;
    }

    public String getCheckdigitPraseThree() {
        return CheckdigitPraseThree;
    }

    public void setCheckdigitPraseThree(String checkdigitPraseThree) {
        CheckdigitPraseThree = checkdigitPraseThree;
    }

    public String getCheckdigitPraseFour() {
        return CheckdigitPraseFour;
    }

    public void setCheckdigitPraseFour(String checkdigitPraseFour) {
        CheckdigitPraseFour = checkdigitPraseFour;
    }

    public String getCheckdigitPraseFive() {
        return CheckdigitPraseFive;
    }

    public void setCheckdigitPraseFive(String checkdigitPraseFive) {
        CheckdigitPraseFive = checkdigitPraseFive;
    }
}