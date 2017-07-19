package com.fintech.orion.documentverification.comparison;

/**
 * Created by sasitha on 7/11/17.
 *
 */
public class ComparisonValueHolder {

    private String id;
    private Object value;

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
}
