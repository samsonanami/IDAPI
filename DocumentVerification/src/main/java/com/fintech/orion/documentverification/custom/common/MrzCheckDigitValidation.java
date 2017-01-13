package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.checkdigit.CheckDigitResults;
import com.fintech.orion.documentverification.common.checkdigit.PassportCheckDigitFormation;
import com.fintech.orion.documentverification.common.exception.CheckDigitFormationException;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.common.exception.DrivingLicenseMRZDecodingException;
import com.fintech.orion.documentverification.common.exception.PassportMRZDecodeException;
import com.fintech.orion.documentverification.common.mrz.DrivingLicenseMZRDecodingStrategy;
import com.fintech.orion.documentverification.common.mrz.MRZDecodeResults;
import com.fintech.orion.documentverification.common.mrz.PassportMRZDecodingStrategy;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sasitha on 1/13/17.
 *
 */
public class MrzCheckDigitValidation extends ValidationHelper implements CustomValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(MrzCheckDigitValidation.class);
    private String ocrFieldBase;
    private int mrzLineCount;
    private List<String> resourceListToPerformValidation;

    private MrzLineBuilder mrzLineBuilder = new MrzLineBuilder();

    @Autowired
    private PassportMRZDecodingStrategy passportMRZDecodingStrategy;

    @Autowired
    private DrivingLicenseMZRDecodingStrategy drivingLicenseMRZDecodingStrategy;

    @Autowired
    private PassportCheckDigitFormation passportCheckDigitFormation;


    @Override
    public ValidationData validate(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {

        ValidationData validationData = new ValidationData();
        validationData.setValidationStatus(true);
        for (String resource : resourceListToPerformValidation){
            String completeMrzLine = mrzLineBuilder.buildSingleLineMRZ(ocrResponse, resource, ocrFieldBase, mrzLineCount);
            if (completeMrzLine != null && !completeMrzLine.isEmpty()){
                validationData = validateMrz(completeMrzLine, resource);
            }
            if (!validationData.getValidationStatus()){
                break;
            }
        }
        validationData.setId("MRZ Check digit validation");
        return validationData;
    }

    private ValidationData validateMrz(String fullMrzLine, String resourceName){
        MRZDecodeResults mrzDecodeResults = null;
        ValidationData validationData = new ValidationData();
        validationData.setValidationStatus(false);
        try {
            if ("passport".equalsIgnoreCase(resourceName)){
                mrzDecodeResults = passportMRZDecodingStrategy.decode(fullMrzLine);
            }
            CheckDigitResults checkDigitResults = passportCheckDigitFormation.calculateCheckdigit(fullMrzLine);
            if ( mrzDecodeResults != null && (checkDigitResults.getCheckdigitPraseOne().equals(mrzDecodeResults.getCheckDigitPhraseOne()) &&
                    checkDigitResults.getCheckdigitPraseTwo().equals(mrzDecodeResults.getCheckDigitPhraseTwo()) &&
                    checkDigitResults.getCheckdigitPraseThree().equals(mrzDecodeResults.getCheckDigitPhraseThree()) &&
                    checkDigitResults.getCheckdigitPraseFour().equals(mrzDecodeResults.getCheckDigitPhraseFour()) &&
                    checkDigitResults.getCheckdigitPraseFive().equals(mrzDecodeResults.getCheckDigitPhraseFive()))){
                validationData.setValue("Passed");
                validationData.setRemarks("All check digit validations passed");
                validationData.setValidationStatus(true);
            }else {
                validationData.setValue("Failed");
                validationData.setRemarks("One or more check digit validations failed");
            }
        } catch (PassportMRZDecodeException e) {
            LOGGER.debug("Error occurred while processing passport MRZ validation ", e);
        }catch (CheckDigitFormationException e) {
            LOGGER.debug("Error occurred while processing the Driving license MRZ validation ", e);
        }

        return validationData;
    }

    public void setOcrFieldBase(String ocrFieldBase) {
        this.ocrFieldBase = ocrFieldBase;
    }

    public void setMrzLineCount(int mrzLineCount) {
        this.mrzLineCount = mrzLineCount;
    }

    public void setResourceListToPerformValidation(List<String> resourceListToPerformValidation) {
        this.resourceListToPerformValidation = resourceListToPerformValidation;
    }
}
