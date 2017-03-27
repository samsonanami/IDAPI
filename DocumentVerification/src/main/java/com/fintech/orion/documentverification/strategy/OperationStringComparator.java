package com.fintech.orion.documentverification.strategy;

/**
 * Created by sasitha on 12/25/16.
 */
public class OperationStringComparator implements DataValidationStrategy<String, String> {

    @Override
    public ValidationResult doDataValidationOperation(String base, String compare, String templateCategory) {
        ValidationResult result = new ValidationResult(false, "");
        if (isInputNotNull(base, compare) && isInputEmpty(base, compare) && base.equalsIgnoreCase(compare)) {
            result.setStatus(true);
        }
        return result;
    }

    private boolean isInputEmpty(String base, String compare) {
        return !base.isEmpty() && !compare.isEmpty();
    }

    private boolean isInputNotNull(String base, String compare) {
        return base != null && compare != null;
    }

}
