package com.fintech.orion.resource.file.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by TharinduMP on 3/30/2017.
 */
public class CommonPdfFile implements PdfFile {

    private byte[] bytes;
    private PDDocument pdDocument;

    public CommonPdfFile(byte[] bytes) throws IOException {
        this.bytes = bytes;
        pdDocument = PDDocument.load(bytes);
    }

    @Override
    public int getNumberOfPages() {
        return pdDocument.getNumberOfPages();
    }

    @Override
    public byte[] convertToJpeg() throws IOException {
        PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300);
        return createImageByteArrayOutput(bufferedImage);
    }


    private byte[] createImageByteArrayOutput(BufferedImage bufferedImage) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
    }

    @Override
    public void close() throws Exception {
        pdDocument.close();
    }
}
