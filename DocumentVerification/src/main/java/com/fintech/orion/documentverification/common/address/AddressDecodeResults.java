package com.fintech.orion.documentverification.common.address;

/**
 *
 * This class contains the decoded element of a address.
 * Created by MudithaJ on 12/16/2016.
 */
public class AddressDecodeResults {

    private String number;
    private String flatNumber;
    private String street;
    private String city;
    private String postalCode;
    private String addressType;

    public AddressDecodeResults() {

        this.number = "";
        this.flatNumber = "";
        this.street = "";
        this.city = "";
        this.postalCode = "";
        this.addressType = "";
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
}
