package com.fintech.orion.documentverification.strategy;

import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.DateDecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by MudithaJ on 12/26/2016.
 */
public class OperationDateComparator implements DataValidationStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationDateComparator.class);
    @Autowired
    private DateDecoder dateDecoder;

    @Override
    public ValidationResult doDataValidationOperation(String base, String compare, String templateCategory) {
        ValidationResult result = new ValidationResult(false, "");
        Date baseDate = null;
        Date compareDate = null;

        try {

            baseDate = dateDecoder.decodeDate(base, templateCategory);
            compareDate = dateDecoder.decodeDate(compare, templateCategory);
            if (baseDate.equals(compareDate)) {
                result.setStatus(true);
            }

        } catch (DateDecoderException e) {
            LOGGER.error("Error occurred while comparing following dates : base {} compare {}", base, compare, e);
        }


        return result;
    }

}
