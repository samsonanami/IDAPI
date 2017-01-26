package com.fintech.orion.documentverification.strategy;

import com.fintech.orion.documentverification.common.address.AddressCompare;
import com.fintech.orion.documentverification.common.address.AddressCompareResult;
import com.fintech.orion.documentverification.common.exception.AddressValidatingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sasitha on 12/26/16.
 */
public class OperationAddressComparator implements DataValidationStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationAddressComparator.class);
    @Autowired
    private AddressCompare addressCompare;

    @Override
    public ValidationResult doDataValidationOperation(String base, String compare) {
        ValidationResult validationResult = new ValidationResult(false, "Error Validating date");
        try {
            AddressCompareResult result = addressCompare.compare(base, compare);
            if (result.isResult()) {
                validationResult.setStatus(Boolean.valueOf(result.isResult()));
                validationResult.setRemarks(result.getMessage());
            }
        } catch (AddressValidatingException e) {
            LOGGER.warn("Error validating address1 {} and address2 {}", base, compare);
        }
        return validationResult;
    }
}
