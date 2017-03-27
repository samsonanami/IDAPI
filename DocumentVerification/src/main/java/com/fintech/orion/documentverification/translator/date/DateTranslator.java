package com.fintech.orion.documentverification.translator.date;

import com.fintech.orion.documentverification.common.date.DateDecoder;
import com.fintech.orion.documentverification.common.exception.DateDecoderException;
import com.fintech.orion.documentverification.translator.OcrValueTranslator;
import com.fintech.orion.documentverification.translator.exception.TranslatorException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * The {@code DateTranslator} class translate a date given as a string in to a
 * {@link java.util.Date Date} class using the
 * {@link com.fintech.orion.documentverification.common.date.DateDecoder DateDecoder} implementation
 *
 */
public class DateTranslator implements OcrValueTranslator<String, Date> {

    @Autowired
    private DateDecoder dateDecoder;

    /**
     * Translate the string date in to a {@link java.util.Date Date} object
     * @param   date          date need to be translated
     * @param   templateName  template name of the document which the date is found
     * @return                translated {@link java.util.Date Date} object
     * @throws TranslatorException if the given date cannot be decoded with the given template name
     */
    @Override
    public Date translate(String date, String templateName) throws TranslatorException {
        try {
            return dateDecoder.decodeDate(date, templateName);
        } catch (DateDecoderException e) {
            throw new TranslatorException("Unable to translate given ocr value : " + date + " for template name : "
            +templateName, e);
        }
    }
}
