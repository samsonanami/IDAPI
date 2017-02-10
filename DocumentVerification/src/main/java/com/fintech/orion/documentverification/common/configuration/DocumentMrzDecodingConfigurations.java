package com.fintech.orion.documentverification.common.configuration;

import com.fintech.orion.documentverification.common.mrz.MRZDecodingStrategy;
import com.fintech.orion.documentverification.common.mrz.ValidateMRZ;

/**
 * Created by sasitha on 2/7/17.
 * 
 */
public class DocumentMrzDecodingConfigurations {

    private String documentName;
    private String mrzOcrExtractionFieldBase;
    private int mrzLineCount;
    private MRZDecodingStrategy mrzDecodingStrategy;
    private ValidateMRZ mrzValidationStrategy;


    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getMrzOcrExtractionFieldBase() {
        return mrzOcrExtractionFieldBase;
    }

    public void setMrzOcrExtractionFieldBase(String mrzOcrExtractionFieldBase) {
        this.mrzOcrExtractionFieldBase = mrzOcrExtractionFieldBase;
    }

    public int getMrzLineCount() {
        return mrzLineCount;
    }

    public void setMrzLineCount(int mrzLineCount) {
        this.mrzLineCount = mrzLineCount;
    }

    public MRZDecodingStrategy getMrzDecodingStrategy() {
        return mrzDecodingStrategy;
    }

    public void setMrzDecodingStrategy(MRZDecodingStrategy mrzDecodingStrategy) {
        this.mrzDecodingStrategy = mrzDecodingStrategy;
    }

    public ValidateMRZ getMrzValidationStrategy() {
        return mrzValidationStrategy;
    }

    public void setMrzValidationStrategy(ValidateMRZ mrzValidationStrategy) {
        this.mrzValidationStrategy = mrzValidationStrategy;
    }
}
