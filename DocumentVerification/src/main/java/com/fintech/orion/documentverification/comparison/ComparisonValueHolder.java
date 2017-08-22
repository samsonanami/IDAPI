package com.fintech.orion.documentverification.comparison;

/**
 * Created by sasitha on 7/11/17.
 *
 */
public class ComparisonValueHolder {

    private String id;
    private Object value;
    private String documentName;
    private String ocrExtractionField;
    private String preProcessedDataus;

    public ComparisonValueHolder(String id, Object value) {
        this.id = id;
        this.value = value;
    }

    public ComparisonValueHolder() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getOcrExtractionField() {
        return ocrExtractionField;
    }

    public void setOcrExtractionField(String ocrExtractionField) {
        this.ocrExtractionField = ocrExtractionField;
    }

    public String getPreProcessedDataus() {
        return preProcessedDataus;
    }

    public void setPreProcessedDataus(String preProcessedDataus) {
        this.preProcessedDataus = preProcessedDataus;
    }
}
