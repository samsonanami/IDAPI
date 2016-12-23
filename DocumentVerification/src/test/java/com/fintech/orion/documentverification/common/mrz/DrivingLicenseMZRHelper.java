package com.fintech.orion.documentverification.common.mrz;

/**
 * Created by MudithaJ on 12/22/2016.
 */
public class DrivingLicenseMZRHelper {

    public MRZItemProperty getMRZSurNameSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("SurName");
        mrzItemProperty.setStartIndex(1);
        mrzItemProperty.setEndIndex(5);
        mrzItemProperty.setMrzLineNumber(1);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZDecadeDigitOfBirthYearSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("DecadeDigitOfBirthYear");
        mrzItemProperty.setStartIndex(6);
        mrzItemProperty.setEndIndex(6);
        mrzItemProperty.setMrzLineNumber(1);

        return mrzItemProperty;
    }
    public MRZItemProperty getDateofBirthMonthSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("DateofBirthMonth");
        mrzItemProperty.setStartIndex(7);
        mrzItemProperty.setEndIndex(8);
        mrzItemProperty.setMrzLineNumber(1);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZRDateWithinTheBirthMonthSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("DateWithinTheBirthMonth");
        mrzItemProperty.setStartIndex(9);
        mrzItemProperty.setEndIndex(10);
        mrzItemProperty.setMrzLineNumber(1);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZDateofBirthYearSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("DateofBirthYear");
        mrzItemProperty.setStartIndex(11);
        mrzItemProperty.setEndIndex(11);
        mrzItemProperty.setMrzLineNumber(1);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZInitialsOfTheFirstNameSystemProperty()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("InitialsOfTheFirstName");
        mrzItemProperty.setStartIndex(12);
        mrzItemProperty.setEndIndex(13);
        mrzItemProperty.setMrzLineNumber(1);

        return mrzItemProperty;
    }
    public MRZItemProperty getMRZRLenght()
    {

        MRZItemProperty mrzItemProperty = new MRZItemProperty();

        mrzItemProperty.setItemName("MZRLength");
        mrzItemProperty.setStartIndex(0);
        mrzItemProperty.setEndIndex(16);


        return mrzItemProperty;
    }
}
