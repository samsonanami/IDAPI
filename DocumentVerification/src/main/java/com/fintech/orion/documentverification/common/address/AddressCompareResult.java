package com.fintech.orion.documentverification.common.address;

/**
 * Created by MudithaJ on 12/22/2016.
 */
public class AddressCompareResult {

    private String addressOne;
    private String addressTwo;
    private String addressOneType;
    private String addressTwoType;
    private boolean result;
    private String message;

    public AddressCompareResult() {

        this.addressOne = "";
        this.addressTwo = "";
        this.addressOneType = "";
        this.addressTwoType = "";
        this.result = false;
        this.message = "";
    }

    public String getaddressTwoType() {
        return addressTwoType;
    }

    public void setaddressTwoType(String addressTwoType) {
        this.addressTwoType = addressTwoType;
    }

    public String getAddressOneType() {
        return addressOneType;
    }

    public void setAddressOneType(String addressOneType) {
        this.addressOneType = addressOneType;
    }

    public String getAddressOne() {

        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
