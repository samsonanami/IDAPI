package com.fintech.orion.documentverification.factory;

/**
 * Created by sasitha on 12/25/16.
 *
 *
 */
public class DocumentVerificationFactory {

    public DocumentVerification getDocumentVerification(DocumentVerificationType type){
        DocumentVerification documentVerification = null;
        switch (type){
            case DATA_COMPARISON:
                    documentVerification =  new DataComparator();
                break;
        }
        return documentVerification;
    }
}
