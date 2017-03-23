package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.checkdigit.CheckDigitResults;
import com.fintech.orion.documentverification.common.checkdigit.PassportCheckDigitFormation;
import com.fintech.orion.documentverification.common.exception.CheckDigitFormationException;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.common.exception.PassportMRZDecodeException;
import com.fintech.orion.documentverification.common.mrz.MRZDecodeResults;
import com.fintech.orion.documentverification.common.mrz.PassportMRZDecodingStrategy;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sasitha on 1/13/17.
 *
 */
public class MrzCheckDigitValidation extends ValidationHelper implements CustomValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(MrzCheckDigitValidation.class);
    private String ocrFieldBase;
    private int mrzLineCount;
    private String resourceNameToCheckMRZ;

    private MrzLineBuilder mrzLineBuilder = new MrzLineBuilder();

    @Autowired
    private PassportMRZDecodingStrategy passportMRZDecodingStrategy;

    @Autowired
    private PassportCheckDigitFormation passportCheckDigitFormation;


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {

        ValidationData validationData = new ValidationData();
        String completeMrzLine = mrzLineBuilder.buildSingleLineMRZ(ocrResponse, resourceNameToCheckMRZ, ocrFieldBase, mrzLineCount);
        if (completeMrzLine != null && !completeMrzLine.isEmpty()){
            validationData = validateMrz(completeMrzLine, resourceNameToCheckMRZ);
        }
        validationData.setId("MRZ Check digit validation");
        return validationData;
    }

    private ValidationData validateMrz(String fullMrzLine, String resourceName){
        MRZDecodeResults mrzDecodeResults = null;
        ValidationData validationData = new ValidationData();
        validationData.setValidationStatus(false);

        try {
            mrzDecodeResults = passportMRZDecodingStrategy.decode(fullMrzLine);
            CheckDigitResults checkDigitResults = passportCheckDigitFormation.calculateCheckDigit(fullMrzLine);
            if ( mrzDecodeResults != null){
                validationData = compareCheckDigits(checkDigitResults, mrzDecodeResults);
            }else {
                validationData.setValue("Failed");
                validationData.setRemarks("Could not decode given MRZ");
            }
        } catch (PassportMRZDecodeException e) {
            validationData.setRemarks("Error occurred while decoding the given MRZ. This is most probably due to an" +
                    " invalid or un supported MRZ.");
            LOGGER.debug("Error occurred while processing passport MRZ validation ", e);
        }catch (CheckDigitFormationException e) {
            validationData.setRemarks("Error occurred while extracting data from the given MRZ. This is most probably" +
                    " due to invalid or supported MRZ");
            LOGGER.debug("Error occurred while processing the Driving license MRZ validation ", e);
        }
        validationData.setValue(fullMrzLine);
        return validationData;
    }

    private ValidationData compareCheckDigits(CheckDigitResults checkDigitResults, MRZDecodeResults mrzDecodeResults){
        ValidationData validationData = new ValidationData();
        validationData.setValidationStatus(false);
        if (!checkDigitResults.getCheckDigitPraseOne().equals(mrzDecodeResults.getCheckDigitPhraseOne())){
            validationData.setRemarks(getCheckDigitMissmatchErrorMessage("one",
                    checkDigitResults.getCheckDigitPraseOne(), mrzDecodeResults.getCheckDigitPhraseOne()));

        }else if (!checkDigitResults.getCheckDigitPraseTwo().equals(mrzDecodeResults.getCheckDigitPhraseTwo())){
            validationData.setRemarks(getCheckDigitMissmatchErrorMessage("two",
                    checkDigitResults.getCheckDigitPraseTwo(), mrzDecodeResults.getCheckDigitPhraseTwo()));

        }else if(!checkDigitResults.getCheckDigitPraseThree().equals(mrzDecodeResults.getCheckDigitPhraseThree())){
            validationData.setRemarks(getCheckDigitMissmatchErrorMessage("three",
                    checkDigitResults.getCheckDigitPraseThree(), mrzDecodeResults.getCheckDigitPhraseThree()));

        }else if(!validateFourthCheckDigit(checkDigitResults.getCheckDigitPraseFour(),mrzDecodeResults.getCheckDigitPhraseFour())){
            validationData.setRemarks(getCheckDigitMissmatchErrorMessage("four",
                    checkDigitResults.getCheckDigitPraseFour(), mrzDecodeResults.getCheckDigitPhraseFour()));

        }else if(!checkDigitResults.getCheckDigitPraseFive().equals(mrzDecodeResults.getCheckDigitPhraseFive())){
            validationData.setRemarks(getCheckDigitMissmatchErrorMessage("five",
                    checkDigitResults.getCheckDigitPraseFive(), mrzDecodeResults.getCheckDigitPhraseFive()));

        }else {
            validationData.setRemarks(getSuccessRemarksMessage());
            validationData.setValidationStatus(true);
        }
        return validationData;
    }

    private String getCheckDigitMissmatchErrorMessage(String checkDigitNumber, String extractedValue, String decodedValue){
        return "Check digit value "+ checkDigitNumber + " dose not match : calculated value > " + decodedValue +
                " : extracted value > " + extractedValue;
    }

    private boolean validateFourthCheckDigit(String actual, String calculated) {
        return actual.equals(calculated) || FourthCheckDigitFillerCharacter(actual) && FourthCheckDigitFillerCharacter(calculated);
    }

    private boolean FourthCheckDigitFillerCharacter(String value) {
        return "0".equals(value) || "<".equals(value);
    }



    public void setOcrFieldBase(String ocrFieldBase) {
        this.ocrFieldBase = ocrFieldBase;
    }

    public void setMrzLineCount(int mrzLineCount) {
        this.mrzLineCount = mrzLineCount;
    }

    public void setResourceNameToCheckMRZ(String resourceNameToCheckMRZ) {
        this.resourceNameToCheckMRZ = resourceNameToCheckMRZ;
    }
}
