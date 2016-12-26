package com.fintech.orion.documentverification.strategy;


/**
 * Created by sasitha on 12/25/16.
 *
 */
public class DocumentDataValidator {

    private DataValidationStrategy strategy;

    public DocumentDataValidator(DataValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public ValidationResult executeStrategy(String base, String compare){
        return strategy.doOperation(base, compare);
    }
}
