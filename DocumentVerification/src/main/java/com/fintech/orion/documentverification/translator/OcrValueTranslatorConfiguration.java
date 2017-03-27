package com.fintech.orion.documentverification.translator;

/**
 * {@code OcrValueTranslatorConfiguration } class holds the configuration for translating
 * a single ocr extraction field.
 */
public class OcrValueTranslatorConfiguration {

    private Translator translator;

    public Translator getTranslator() {
        return translator;
    }

    public void setTranslator(Translator translator) {
        this.translator = translator;
    }
}
