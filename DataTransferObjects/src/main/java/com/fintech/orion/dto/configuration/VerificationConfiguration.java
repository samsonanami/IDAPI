package com.fintech.orion.dto.configuration;

/**
 * Created by sasitha on 12/22/16.
 */
public class VerificationConfiguration {

    private DataValidationStrategyType sameValueComparisonStrategyAcrossMultipleResources;

    public DataValidationStrategyType getSameValueComparisonStrategyAcrossMultipleResources() {
        return sameValueComparisonStrategyAcrossMultipleResources;
    }

    public void setSameValueComparisonStrategyAcrossMultipleResources(DataValidationStrategyType sameValueComparisonStrategyAcrossMultipleResources) {
        this.sameValueComparisonStrategyAcrossMultipleResources = sameValueComparisonStrategyAcrossMultipleResources;
    }
}
