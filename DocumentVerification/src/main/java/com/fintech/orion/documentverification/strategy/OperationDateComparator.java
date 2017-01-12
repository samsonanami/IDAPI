package com.fintech.orion.documentverification.strategy;

import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.DateComparatorException;

import java.util.Date;

/**
 * Created by MudithaJ on 12/26/2016.
 */
public class OperationDateComparator extends DateDecoder implements DataValidationStrategy {

    @Override
    public ValidationResult doOperation(String base, String compare) {
        ValidationResult
                result = new ValidationResult(false, "");
        Date baseDate = null;
        Date compareDate = null;

        try {
            baseDate = this.decodeDate(base);
            compareDate = this.decodeDate(compare);
            if (baseDate.equals(compareDate)) {
                result.setStatus(true);
            }

        } catch (DateComparatorException e) {
            e.printStackTrace();
        }


        return result;
    }

}
