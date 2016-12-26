package com.fintech.orion.documentverification.common.mrz;

import java.util.HashMap;

/**
 * Created by MudithaJ on 12/21/2016.
 */
public class PassportMRZHelper {



    private HashMap<String,MRZItemProperty> mrzItemProperty;

    public PassportMRZHelper() {

       // mrzItemProperty.put("SurName",getMRZSurNameSystemProperty());
    }

    public MRZItemProperty getMRZSurNameSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("SurName");
        mrzItemProperty.setStartIndex(6);
        mrzItemProperty.setEndIndex(44);
        mrzItemProperty.setMrzLineNumber(1);

        return mrzItemProperty;
    }
    public HashMap<String, MRZItemProperty> getMrzItemProperty() {
        return mrzItemProperty;
    }

    public void setMrzItemProperty(HashMap<String, MRZItemProperty> mrzItemProperty) {
        this.mrzItemProperty = mrzItemProperty;
    }
    public MRZItemProperty getMRZGivenNameSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("GivenNames");
        mrzItemProperty.setStartIndex(6);
        mrzItemProperty.setEndIndex(44);
        mrzItemProperty.setMrzLineNumber(1);

        return mrzItemProperty;
    }

    public MRZItemProperty getMRZPassportNumberSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("PassPortNumber");
        mrzItemProperty.setStartIndex(1);
        mrzItemProperty.setEndIndex(9);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZSexSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("Sex");
        mrzItemProperty.setStartIndex(21);
        mrzItemProperty.setEndIndex(21);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZDateofExpireSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("ExpireDate");
        mrzItemProperty.setStartIndex(22);
        mrzItemProperty.setEndIndex(27);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }

    public MRZItemProperty getMRZPlaceOfissueSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("Nationality");
        mrzItemProperty.setStartIndex(11);
        mrzItemProperty.setEndIndex(13);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }

    public MRZItemProperty getMRZDateOfBirthSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("DateOfBirth");
        mrzItemProperty.setStartIndex(14);
        mrzItemProperty.setEndIndex(19);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }

    public MRZItemProperty getMRZCheckDigitPraseOneSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("CheckDigitOne");
        mrzItemProperty.setStartIndex(10);
        mrzItemProperty.setEndIndex(10);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }

    public MRZItemProperty getMRZCheckDigitPraseTwoSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("CheckDigitTwo");
        mrzItemProperty.setStartIndex(20);
        mrzItemProperty.setEndIndex(20);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }

    public MRZItemProperty getMRZCheckDigitPraseThreeSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("CheckDigitThree");
        mrzItemProperty.setStartIndex(28);
        mrzItemProperty.setEndIndex(28);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }

    public MRZItemProperty getMRZCheckDigitPraseFourSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("CheckDigitFour");
        mrzItemProperty.setStartIndex(43);
        mrzItemProperty.setEndIndex(43);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZCheckDigitPraseFiveSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("CheckDigitFive");
        mrzItemProperty.setStartIndex(44);
        mrzItemProperty.setEndIndex(44);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZFirstLineLengthSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("MRZFirstLineLength");
        mrzItemProperty.setStartIndex(0);
        mrzItemProperty.setEndIndex(44);


        return mrzItemProperty;
    }
    public MRZItemProperty getMRZSecondLineLengthSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("MRZSecondLineLength");
                mrzItemProperty.setStartIndex(0);
        mrzItemProperty.setEndIndex(44);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZCalculateCheckDigitPraseOneSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("CheckDigitPraseOne");
        mrzItemProperty.setStartIndex(1);
        mrzItemProperty.setEndIndex(9);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZCalculateCheckDigitPraseTwoSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("CheckDigitPraseTwo");
        mrzItemProperty.setStartIndex(14);
        mrzItemProperty.setEndIndex(19);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZCalculateCheckDigitPraseThreeSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("CheckDigitPraseThre");
        mrzItemProperty.setStartIndex(22);
        mrzItemProperty.setEndIndex(27);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZCalculateCheckDigitPraseFourSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("CheckDigitPraseFour");
        mrzItemProperty.setStartIndex(29);
        mrzItemProperty.setEndIndex(42);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZCalculateCheckDigitPraseFiveSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("CheckDigitPraseFive");
        mrzItemProperty.setStartIndex(1);
        mrzItemProperty.setEndIndex(9);
        mrzItemProperty.setMrzLineNumber(2);

        return mrzItemProperty;
    }
}
