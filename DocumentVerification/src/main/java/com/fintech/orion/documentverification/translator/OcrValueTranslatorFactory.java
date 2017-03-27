package com.fintech.orion.documentverification.translator;

import com.fintech.orion.documentverification.translator.date.DateTranslator;
import com.fintech.orion.documentverification.translator.string.StringTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.util.Map;

/**
 * {@code OcrValueTranslatorFactory } provides the correct
 * {@link com.fintech.orion.documentverification.translator.OcrValueTranslator OcrValueTranslator}
 * value for the given ocr extraction field based on the external configurations provided to the
 * factory.
 */
public class OcrValueTranslatorFactory {

    @Autowired
    private StringTranslator stringTranslator;

    @Autowired
    private DateTranslator dateTranslator;

    @Resource
    @Qualifier(value = "ocrValueTranslatorConfigurationMap")
    private Map<String, OcrValueTranslatorConfiguration> ocrValueTranslatorConfigurationMap;

    /**
     * Return the matching
     * {@link com.fintech.orion.documentverification.translator.OcrValueTranslator OcrValueTranslator }
     * object for the given ocr extraction field based on the configurations
     * @param ocrExtractionField ocr extraction field need to be translated.
     * @return {@link com.fintech.orion.documentverification.translator.OcrValueTranslator OcrValueTranslator }
     */
    public OcrValueTranslator getOcrValueTranslator(String ocrExtractionField){
        Translator translator = getTranslator(ocrExtractionField);
        OcrValueTranslator ocrValueTranslator = stringTranslator;
        if (translator.equals(Translator.STRING)){
            ocrValueTranslator = stringTranslator;
        }else if (translator.equals(Translator.DATE)){
            ocrValueTranslator = dateTranslator;
        }
        return ocrValueTranslator;
    }

    private Translator getTranslator(String ocrExtractionFieldName){
        return this.ocrValueTranslatorConfigurationMap.get(ocrExtractionFieldName).getTranslator();
    }
}
