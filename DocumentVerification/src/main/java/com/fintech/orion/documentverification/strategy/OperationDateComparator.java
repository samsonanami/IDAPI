package com.fintech.orion.documentverification.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by MudithaJ on 12/26/2016.
 *
 */
public class OperationDateComparator implements DataValidationStrategy<Date, Date> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationDateComparator.class);


    @Override
    public ValidationResult doDataValidationOperation(Date baseDate, Date compareDate, String templateCategory) {
        LOGGER.debug("Comparing dates : base {} compare {}", baseDate, compareDate);
        ValidationResult result = new ValidationResult(false, "");
        if (baseDate.equals(compareDate)) {
            result.setStatus(true);
        }
        return result;
    }

}
