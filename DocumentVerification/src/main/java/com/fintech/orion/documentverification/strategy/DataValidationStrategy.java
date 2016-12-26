package com.fintech.orion.documentverification.strategy;

/**
 * Created by sasitha on 12/25/16.
 */
public interface DataValidationStrategy {

    ValidationResult doOperation(String base, String compare);
}
