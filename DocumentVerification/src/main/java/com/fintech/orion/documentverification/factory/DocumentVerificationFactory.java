package com.fintech.orion.documentverification.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by sasitha on 12/25/16.
 *
 *
 */
@Service
public class DocumentVerificationFactory {

    @Autowired
    @Qualifier("dataComparator")
    private DataComparator dataComparator;

    @Autowired
    @Qualifier("idDocFullValidator")
    private IdentificationDocumentFullVerification idDocFullValidator;

    public DocumentVerification getDocumentVerification(DocumentVerificationType type){
        DocumentVerification documentVerification = null;
        switch (type){
            case DATA_COMPARISON:
                    documentVerification =  dataComparator;
                break;
            case ID_DOC_FULL_VERIFICATIONS:
                    documentVerification = idDocFullValidator;
                break;
        }
        return documentVerification;
    }
}
