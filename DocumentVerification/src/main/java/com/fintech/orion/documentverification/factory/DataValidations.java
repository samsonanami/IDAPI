package com.fintech.orion.documentverification.factory;

import com.fintech.orion.dataabstraction.repositories.ProcessingRequestRepositoryInterface;
import com.fintech.orion.documentverification.common.exception.CustomValidationException;
import com.fintech.orion.documentverification.custom.CustomValidation;
import com.fintech.orion.dto.configuration.VerificationConfiguration;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.api.DataValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sasitha on 2/7/17.
 *
 */
public class DataValidations implements DocumentVerification {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataValidation.class);
    @Autowired
    private ProcessingRequestRepositoryInterface processingRequestRepositoryInterface;

    @Autowired
    @Qualifier("dataValidations")
    private List dataValidationsList;

    @Override
    public List<Object> verifyExtractedDocumentResult(OcrResponse ocrResponse, Map<String, VerificationConfiguration> configurations) {


        List<Object> dataValidationsResultList = new ArrayList<>();
        LOGGER.debug("Starting data validations with ocr output {} ", ocrResponse);

        for (CustomValidation customValidation : getCustomValidationList()){
            try {
                DataValidation dataValidation = (DataValidation) customValidation.validate(null, ocrResponse);
                dataValidationsResultList.add(dataValidation);
            } catch (CustomValidationException e) {
                LOGGER.error("Unable to perform data validation {} ", customValidation, e);
            }
        }
        return dataValidationsResultList;
    }

    private List<CustomValidation> getCustomValidationList() {
        return dataValidationsList;
    }
}
