package com.fintech.orion.documentverification.translator.string;

import com.fintech.orion.documentverification.translator.OcrValueTranslator;
import com.fintech.orion.documentverification.translator.exception.TranslatorException;

/**
 * The {@code StringTranslator} class translate ocr value in to a
 * {@link java.lang.String String} object.
 */
public class StringTranslator implements OcrValueTranslator<String, String> {

    /**
     * Translate given string value in to a string
     * @param   ocrValue        ocr value need to be translated
     * @param   templateName    template name of the document this value was extracted
     * @return                  translated string
     * @throws TranslatorException
     */
    @Override
    public String translate(String ocrValue, String templateName) throws TranslatorException {
        return ocrValue;
    }
}
