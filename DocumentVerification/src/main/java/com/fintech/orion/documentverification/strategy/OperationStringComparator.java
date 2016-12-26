package com.fintech.orion.documentverification.strategy;

/**
 * Created by sasitha on 12/25/16.
 */
public class OperationStringComparator implements DataValidationStrategy {

    @Override
    public ValidationResult doOperation(String base, String compare) {
        ValidationResult result = new ValidationResult(false, "");
        if (base.equalsIgnoreCase(compare)){
            result.setStatus(true);
        }
        return result;
    }
}
