package com.fintech.orion.documentverification.strategy;

import com.fintech.orion.dto.configuration.DataValidationStrategyType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sasitha on 12/25/16.
 *
 */
public class DataValidationStrategyProvider {

    @Autowired
    private OperationAddressComparator operationAddressComparator;

    @Autowired
    private OperationStringComparator operationStringComparator;

    @Autowired
    private OperationDateComparator operationDateComparator;

    public DataValidationStrategy getValidationStrategy(DataValidationStrategyType type){
        DataValidationStrategy strategy = null;
        switch (type){
            case STRING:
                strategy =  operationStringComparator;
                break;
            case DATE:
                strategy = operationDateComparator;
                break;
            case ADDRESS:
                strategy = operationAddressComparator;
        }
        return strategy;
    }
}
