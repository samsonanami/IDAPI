package com.fintech.orion.documentverification.strategy;

/**
 * Created by sasitha on 12/25/16.
 */
public interface DataValidationStrategy {

    ValidationResult doDataValidationOperation(String base, String compare);
}
