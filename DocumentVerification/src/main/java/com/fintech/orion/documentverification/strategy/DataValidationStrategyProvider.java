package com.fintech.orion.documentverification.strategy;

import com.fintech.orion.dto.configuration.DataValidationStrategyType;

/**
 * Created by sasitha on 12/25/16.
 *
 */
public class DataValidationStrategyProvider {

    public DataValidationStrategy getValidationStrategy(DataValidationStrategyType type){
        DataValidationStrategy strategy = null;
        switch (type){
            case STRING:
                strategy =  new OperationStringComparator();
                break;
        }
        return strategy;
    }
}
