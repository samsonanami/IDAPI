package com.fintech.orion.documentverification.strategy;

/**
 * Created by sasitha on 12/25/16.
 */
public interface DataValidationStrategy<E,I> {

    ValidationResult doDataValidationOperation(E base, I compare, String templateCategory);
}
