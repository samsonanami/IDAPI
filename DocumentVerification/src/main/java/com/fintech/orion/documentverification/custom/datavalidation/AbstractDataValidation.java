package com.fintech.orion.documentverification.custom.datavalidation;

import com.fintech.orion.dataabstraction.entities.orion.ResourceName;
import com.fintech.orion.documentverification.common.configuration.DocumentMrzDecodingConfigurations;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.common.exception.MRZDecodingException;
import com.fintech.orion.documentverification.common.exception.MRZValidatingException;
import com.fintech.orion.documentverification.common.mrz.MRZDecodeResults;
import com.fintech.orion.documentverification.common.mrz.ValidateMRZ;
import com.fintech.orion.documentverification.common.mrz.ValidateMRZResult;
import com.fintech.orion.documentverification.custom.common.MrzLineBuilder;
import com.fintech.orion.documentverification.custom.common.ValidationHelper;
import com.fintech.orion.documentverification.strategy.DataValidationStrategy;
import com.fintech.orion.documentverification.strategy.DataValidationStrategyProvider;
import com.fintech.orion.documentverification.strategy.DocumentDataValidator;
import com.fintech.orion.documentverification.strategy.ValidationResult;
import com.fintech.orion.dto.configuration.DataValidationStrategyType;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.DataValidation;
import com.fintech.orion.dto.response.api.DataValidationValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sasitha on 2/7/17.
 */
public class AbstractDataValidation extends ValidationHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDataValidation.class);
    @Autowired
    @Qualifier("documentMrzDecodingConfigurations")
    private List documentMrzDecodingConfigurations;

    @Autowired
    private DataValidationStrategyProvider dataValidationStrategyProvider;

    private DataValidationStrategyType dataValidationStrategyType;

    public DataValidation ocrExtractionFieldVizMrzDataValidation(ResourceName resourceName, OcrResponse ocrResponse) throws CustomValidationException {
        DataValidation dataValidation = new DataValidation();
        dataValidation.setId(getOcrExtractionFieldName());
        List<DataValidationValue> dataValidationValueList = new ArrayList<>();

        for (String documentName : getResourceListFromOcrResponse(ocrResponse)){
            DataValidationValue dataValidationValue = new DataValidationValue();
            dataValidationValue.setDocumentName(documentName);
            dataValidationValue.setVizValue(getVizValue(getOcrExtractionFieldName(), documentName, ocrResponse));
            dataValidationValue.setMrzValue("");
            dataValidationValue.setRemarks(getFailedRemarksMessage());
            try {
                MRZDecodeResults mrzDecodeResults = decodeMrz(getOcrExtractionFieldName(), documentName, ocrResponse);
                dataValidationValue.setMrzValue(getMrzValueForOcrExtractionField(getOcrExtractionFieldName(), mrzDecodeResults));
            }catch (MRZValidatingException e){
                LOGGER.error("Error validating mrz line {}", e);
            } catch (MRZDecodingException e) {
                LOGGER.error("Error decoding the mrz line {}", e);
            }
            dataValidationValue.setRemarks(getFailedRemarksMessage());

            DataValidationStrategy strategy = dataValidationStrategyProvider.getValidationStrategy(dataValidationStrategyType);
            DocumentDataValidator validator =  new DocumentDataValidator(strategy);

            ValidationResult result = validator.executeStrategy(dataValidationValue.getMrzValue(),
                    dataValidationValue.getVizValue());
            if (result.isStatus()){
                dataValidationValue.setStatus(true);
                dataValidationValue.setRemarks(getSuccessRemarksMessage());
            }
            dataValidationValueList.add(dataValidationValue);
        }
        dataValidation.setValue(dataValidationValueList);
        return dataValidation;
    }

    public String getVizValue(String extractionFieldName, String documentName, OcrResponse ocrResponse){
        OcrFieldData fieldData = getFieldDataById(extractionFieldName, ocrResponse);
        OcrFieldValue fieldValue = getFieldValueById(documentName+"##"+extractionFieldName, fieldData);
        return fieldValue.getValue();
    }

    private MRZDecodeResults decodeMrz(String extractionFieldName, String documentName, OcrResponse ocrResponse) throws MRZDecodingException, MRZValidatingException {
        MRZDecodeResults decodeResults = new MRZDecodeResults();
        MrzLineBuilder mrzLineBuilder = new MrzLineBuilder();
        for (DocumentMrzDecodingConfigurations configuration : getDocumentMrzDecodingConfigurations()){
            if (configuration.getDocumentName().equalsIgnoreCase(documentName)){
                String singleMrzLine = mrzLineBuilder.buildSingleLineMRZ(ocrResponse, documentName,
                        configuration.getMrzOcrExtractionFieldBase(),
                        configuration.getMrzLineCount());
                ValidateMRZResult validateMRZResult = validateMrz(singleMrzLine, configuration.getMrzValidationStrategy());
                if ("true".equalsIgnoreCase(validateMRZResult.getValidationResult())){
                    decodeResults = configuration.getMrzDecodingStrategy().decode(singleMrzLine);
                }
            }
        }

        return decodeResults;
    }

    private ValidateMRZResult validateMrz(String mrz, ValidateMRZ mrzValidator) throws MRZValidatingException {
        return mrzValidator.validate(mrz);
    }

    private List<DocumentMrzDecodingConfigurations> getDocumentMrzDecodingConfigurations() {
        return documentMrzDecodingConfigurations;
    }

    public String getMrzValueForOcrExtractionField(String ocrExtractionField, MRZDecodeResults decodeResults){
        String mrzValue;
        switch (ocrExtractionField){
            case "surname":
                mrzValue = decodeResults.getSurname();
                break;
            case "date_of_birth":
                mrzValue = decodeResults.getDateOfBirth();
                break;
            case "sex":
                mrzValue = decodeResults.getSex();
                break;
            case "given_names":
                mrzValue = decodeResults.getGivenName();
                break;
            case "document_number":
                mrzValue = decodeResults.getPassPortNumber();
                break;
            default:
                mrzValue = "";
                break;
        }
        return mrzValue;
    }

    public void setDataValidationStrategyType(DataValidationStrategyType dataValidationStrategyType) {
        this.dataValidationStrategyType = dataValidationStrategyType;
    }

    public DataValidationStrategyType getDataValidationStrategyType() {
        return dataValidationStrategyType;
    }
}
