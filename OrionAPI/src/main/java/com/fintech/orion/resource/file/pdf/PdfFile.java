package com.fintech.orion.resource.file.pdf;

import java.io.IOException;

/**
 * Created by TharinduMP on 3/30/2017.
 */
public interface PdfFile extends AutoCloseable {

    int getNumberOfPages();

    byte[] convertToJpeg() throws IOException;

}
