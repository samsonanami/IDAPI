package com.fintech.orion.documentverification.common.checkdigit;



import com.fintech.orion.documentverification.common.mrz.Range;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by MudithaJ on 11/28/2016.
 */
public class PassportCheckDigitFormation {

    private int moduler;
    private int  checkDigitAphabteStartValue;
    private int checkDigitAphabteENDValue;
    private int secondStringStartIndex;
    static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PassportCheckDigitFormation.class);

   // private static final Logger LOGGER = LoggerFactory.getLogger(AppConfigurationProvider.class);

    public PassportCheckDigitFormation() {
        this.setCheckDigitAphabteStartValue(6);
        this.setModuler(10);
        this.setSecondStringStartIndex(44);
    }
    private void getContext()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:" + "passport-mrz-decode.xml");
    }
    public CheckDigitResults calculateCheckdigit(String mrz)
    {

       // PassportDecodeItems[] items = (PassportDecodeItems[])checkDigitConfiguration.getBean("mrzConfigureList");
         //this.getContext();
        Range[] checkDigitRangeArray = this.getCheckDigitRangeParameters();
        mrz = this.getfixedMRZ(mrz);

        CheckDigitResults results = new CheckDigitResults();
        results.setCheckdigitPraseOne(getCheckdigitPraseOne(mrz,checkDigitRangeArray[0]));
      results.setCheckdigitPraseTwo(getCheckdigitPraseTwo(mrz,checkDigitRangeArray[1]));
        results.setCheckdigitPraseThree(getCheckdigitPraseThree(mrz,checkDigitRangeArray[2]));
        results.setCheckdigitPraseFour(getCheckdigitPraseFour(mrz,checkDigitRangeArray[3]));
     results.setCheckdigitPraseFive(getCheckdigitPraseFive(mrz,checkDigitRangeArray));


        return results;
    }

    private String getfixedMRZ(String mrz)
    {
        return   mrz.replaceAll("\\s+", "");
    }

    private Range[] getCheckDigitRangeParameters()
    {
        Range firstCheckDigitRange = new Range();
        Range secondCheckDigitRange = new Range();
        Range thirdCheckDigitRange = new Range();
        Range fourthCheckDigitRange = new Range();
        Range[] checkDigitRangeArray = new  Range[4];


        firstCheckDigitRange.setStart(1);
        firstCheckDigitRange.setEnd(9);
        checkDigitRangeArray[0]=firstCheckDigitRange;

        secondCheckDigitRange.setStart(14);
        secondCheckDigitRange.setEnd(19);
        checkDigitRangeArray[1] = secondCheckDigitRange;

        thirdCheckDigitRange.setStart(22);
        thirdCheckDigitRange.setEnd(27);
        checkDigitRangeArray[2]=thirdCheckDigitRange;

        fourthCheckDigitRange.setStart(29);
        fourthCheckDigitRange.setEnd(42);
        checkDigitRangeArray[3]=fourthCheckDigitRange;

        return checkDigitRangeArray;
    }
    public void setSecondStringStartIndex(int secondStringStartIndex) {
        this.secondStringStartIndex = secondStringStartIndex;
    }

    public void setModuler(int moduler) {
        this.moduler = moduler;
    }

    public void setCheckDigitAphabteStartValue(int checkDigitAphabteStartValue) {
        this.checkDigitAphabteStartValue = checkDigitAphabteStartValue;
    }

    public void setCheckDigitAphabteENDValue(int checkDigitAphabteENDValue) {
        this.checkDigitAphabteENDValue = checkDigitAphabteENDValue;
    }


    private int calculateCharacterDigitValue(char character) {
        int value;
        int subtituedValue;

        if(Character.isDigit(character)) {
            return Character.getNumericValue(character);
        }else if(character == '<')
        {
            return 0;
        }
        else {
            subtituedValue = (int) Character.toUpperCase('A') - this.checkDigitAphabteStartValue;

            value = (int) Character.toUpperCase(character) - subtituedValue;
            return value;
        }

    }

    private int calculateWeight(int position)
    {
        int positionWeightFactor;
        int value;

        positionWeightFactor = position%3;

        switch(positionWeightFactor) {
            case 1:
                value = 7; break;
            case 2:
                value = 3; break;
            case 0:
                value = 1; break;
            default:
                value = 0; break;


        }

        return value;
    }
    private int calculateCheckDigit(String mrz,Range rangeObject)
    {
        try {
            String mrzPortion = mrz.substring((rangeObject.getStart() + secondStringStartIndex) - 1, rangeObject.getEnd() + secondStringStartIndex);
            int index = 1;
            int checkDigit = 0;
            int checkDigitPerIndex = 0;

            for (char c : mrzPortion.toCharArray()) {
                checkDigitPerIndex = this.calculateCharacterDigitValue(c) * this.calculateWeight(index);
                checkDigit = checkDigit + checkDigitPerIndex;

                index++;
            }

            return checkDigit % this.moduler;
        }
        catch (IndexOutOfBoundsException e)
        {
            LOGGER.error("MRZ checkdigit calculation index out of bound {}");
            return 0;

        }
    }
    public String  getCheckdigitPraseOne(String mrz,Range digitRange)
    {
           int checkDigitPrase;
              Range rangeForCheckdigitPrase = new Range();
              rangeForCheckdigitPrase.setStart(digitRange.getStart());
              rangeForCheckdigitPrase.setEnd(digitRange.getEnd());

        checkDigitPrase = this.calculateCheckDigit(mrz,rangeForCheckdigitPrase);
        return Integer.toString(checkDigitPrase);
    }
    public String  getCheckdigitPraseTwo(String mrz,Range digitRange)
    {
        int checkDigitPrase;
        Range rangeForCheckdigitPrase = new Range();
        rangeForCheckdigitPrase.setStart(digitRange.getStart());
        rangeForCheckdigitPrase.setEnd(digitRange.getEnd());

        checkDigitPrase = this.calculateCheckDigit(mrz,rangeForCheckdigitPrase);
        return Integer.toString(checkDigitPrase);

    }
    public String  getCheckdigitPraseThree(String mrz,Range digitRange)
    {
        int checkDigitPrase;
        Range rangeForCheckdigitPrase = new Range();
        rangeForCheckdigitPrase.setStart(digitRange.getStart());
        rangeForCheckdigitPrase.setEnd(digitRange.getEnd());

        checkDigitPrase = this.calculateCheckDigit(mrz,rangeForCheckdigitPrase);
        return Integer.toString(checkDigitPrase);
    }
    public String  getCheckdigitPraseFour(String mrz,Range digitRange)
    {
        int checkDigitPrase;
        Range rangeForCheckdigitPrase = new Range();
        rangeForCheckdigitPrase.setStart(digitRange.getStart());
        rangeForCheckdigitPrase.setEnd(digitRange.getEnd());

        checkDigitPrase = this.calculateCheckDigit(mrz,rangeForCheckdigitPrase);
        return Integer.toString(checkDigitPrase);
    }
    public String  getCheckdigitPraseFive(String mrz,Range[] range)
    {
        int checkDigitPraseFirst;
        int checkDigitPraseTwo;
        int checkDigitPraseThree;
       int checkDigitPraseFinal;


        checkDigitPraseFirst = Integer.parseInt(this.getCheckdigitPraseOne(mrz,range[0]));
        checkDigitPraseTwo =Integer.parseInt(this.getCheckdigitPraseTwo(mrz, range[1]));
        checkDigitPraseThree = Integer.parseInt(this.getCheckdigitPraseThree(mrz, range[2]));
        checkDigitPraseFinal = (checkDigitPraseFirst + checkDigitPraseTwo + checkDigitPraseThree)% this.moduler;

        return Integer.toString(checkDigitPraseFinal);
    }
}