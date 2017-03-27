package com.fintech.orion.documentverification.translator;

import com.fintech.orion.documentverification.translator.exception.TranslatorException;

/**
 * {@code OcrValueTranslator} interface impose a translator method to translate
 * given object in to a desired object
 * @param <E> Input object type
 * @param <I> Output object type
 */
public interface OcrValueTranslator<E, I> {
     I translate(E ocrValue, String templateName) throws TranslatorException;
}
