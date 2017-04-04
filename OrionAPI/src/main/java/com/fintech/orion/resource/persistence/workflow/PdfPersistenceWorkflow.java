package com.fintech.orion.resource.persistence.workflow;

import com.fintech.orion.resource.file.name.Filename;
import com.fintech.orion.resource.file.pdf.PdfFile;
import com.fintech.orion.resource.persistence.exception.PersistenceBusinessException;
import com.fintech.orion.resource.persistence.exception.PersistenceException;
import com.fintech.orion.resource.upload.UploadResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public class PdfPersistenceWorkflow extends AbstractPersistenceWorkflow implements PersistenceWorkflow {

    @Autowired
    private Filename uuidFilename;

    @Override
    public String execute(UploadResource uploadResource) throws PersistenceException {
        try {
            // Get Resource Bytes
            byte[] resourceBytes = getResourceBytes(uploadResource);

            // Convert PDF To JPG
            byte[] jpgBytes = createJpgFile(resourceBytes);

            // Persist PDF
            persist(resourceBytes, uuidFilename.getUniqueFileName("pdf"));

            // Persist JPG
            return persist(jpgBytes, uuidFilename.getUniqueFileName("jpg"));

        } catch (IOException e) {
            throw new PersistenceException("PDF Persistence Workflow Failed Failed", e);
        }
    }

    private byte[] getResourceBytes(UploadResource uploadResource) throws PersistenceException {
        try {
            return uploadResource.getResourceBytes();
        } catch (IOException e) {
            throw new PersistenceException("Getting Byte Array from Multipart File Failed", e);
        }
    }

    private byte[] createJpgFile(byte[] resourceBytes) throws PersistenceException {
        try (PdfFile pdfPdfFile = (PdfFile) beanFactory.getBean("pdfFile", resourceBytes)) {

            if (pdfPdfFile.getNumberOfPages() > 1) {
                throw new PersistenceBusinessException("Multi-Paged PDFs are not supported");
            }

            // conversion
            return pdfPdfFile.convertToJpeg();

        } catch (Exception e) {
            throw new PersistenceException("PDF File to Image Conversion Failed", e);
        }
    }

}
