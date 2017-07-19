package com.fintech.orion.dto.processtype;

/**
 * ProcessType dto
 */
public class ProcessTypeDTO {

    private int id;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProcessTypeDTO that = (ProcessTypeDTO) o;

        if (id != that.id) {
            return false;
        }
        return type.equals(that.type);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + type.hashCode();
        return result;
    }
}
