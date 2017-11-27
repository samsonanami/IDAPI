package com.fintech.orion.dto.mrz;

/*
 * MRZ
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-11-09T10:12:11.427Z")
public class MRZ {
    private String line_01;
    private String line_02;

    public String getLine_01() {
        return line_01;
    }

    public void setLine_01(String line_01) {
        this.line_01 = line_01;
    }

    public String getLine_02() {
        return line_02;
    }

    public void setLine_02(String line_02) {
        this.line_02 = line_02;
    }

    @Override
    public String toString() {
        return "line_01: "+line_01
                +"\nline_02: "+line_02;
    }
}
