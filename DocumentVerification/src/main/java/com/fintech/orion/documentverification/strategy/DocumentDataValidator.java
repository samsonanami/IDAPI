package com.fintech.orion.documentverification.strategy;


/**
 * Created by sasitha on 12/25/16.
 *
 */
public class DocumentDataValidator<E,I> {

    private DataValidationStrategy<E,I> strategy;

    public DocumentDataValidator(DataValidationStrategy<E,I> strategy) {
        this.strategy = strategy;
    }

    public ValidationResult executeStrategy(E base, I compare, String templateCategory) {
        return strategy.doDataValidationOperation(base, compare, templateCategory);
    }
}
