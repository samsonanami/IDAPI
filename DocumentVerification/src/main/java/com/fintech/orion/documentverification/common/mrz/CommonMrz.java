package com.fintech.orion.documentverification.common.mrz;

import com.fintech.orion.documentverification.common.mrz.exception.MrzException;
import com.fintech.orion.dto.featurepoint.FeaturePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TharinduMP on 3/17/2017.
 * The Base Mrz Class which gives access to MRZ features.
 */
@Component
public class CommonMrz implements Mrz {

    private Map<String, String> mrzFeatures;
    private String mrzText;
    private List<FeaturePoint> featurePoints;

    public CommonMrz(String mrzText, List<FeaturePoint> featurePoints) {
        mrzFeatures = new HashMap<>();
        this.mrzText = mrzText;
        this.featurePoints = featurePoints;
        validateMrz(mrzText);
        validateRanges(featurePoints);
        createFeatures();
    }

    private void createFeatures() {
        for (FeaturePoint featurePoint : featurePoints) {
            validateRange(featurePoint);
            mrzFeatures.put(featurePoint.getName(), mrzText.substring(featurePoint.getStartIndex(), featurePoint.getEndIndex()));
        }
    }

    /**
     * The method returns the feature value
     * @param featureName Feature Name like Name, Age etc. (Anything available within that MRZ spec)
     * @return feature value or null if non available
     */
    public String getFeature(String featureName) {
        return mrzFeatures.get(featureName);
    }

    private void validateMrz(String mrz) {
        if(mrz == null || mrz.isEmpty()) {
            throw new MrzException("Provided Mrz was null or Empty");
        }
    }

    private void validateRange(FeaturePoint featurePoint) {
        if(featurePoint.getName() == null || featurePoint.getName().isEmpty()) {
            throw new MrzException("One of the Provided Range Name was null or Empty");
        }
    }

    private void validateRanges(List<FeaturePoint> featurePoints) {
        if(featurePoints == null || featurePoints.isEmpty()) {
            throw new MrzException("Provided Ranges List was null or Empty");
        }
    }
}
