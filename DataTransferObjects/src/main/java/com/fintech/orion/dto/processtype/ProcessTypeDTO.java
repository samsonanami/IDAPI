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
        if ((o instanceof ProcessTypeDTO) && (((ProcessTypeDTO) o).getId() == this.id)
                && (((ProcessTypeDTO) o).getType() == this.type)) {
            return true;
        } else {
            return false;
        }
    }

}
