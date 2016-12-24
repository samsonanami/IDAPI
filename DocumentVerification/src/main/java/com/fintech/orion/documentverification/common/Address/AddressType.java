package com.fintech.orion.documentverification.common.Address;

/**
 * Created by MudithaJ on 12/16/2016.
 */
public class AddressType {

    private String type;
    private String validateRegularExpression;
    private String description;
    private String decodeRegularExpression;

    public String getValidateRegularExpression() {
        return validateRegularExpression;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValidateRegularExpression(String validateRegularExpression) {
        this.validateRegularExpression = validateRegularExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDecodeRegularExpression() {
        return decodeRegularExpression;
    }

    public void setDecodeRegularExpression(String decodeRegularExpression) {
        this.decodeRegularExpression = decodeRegularExpression;
    }
}
