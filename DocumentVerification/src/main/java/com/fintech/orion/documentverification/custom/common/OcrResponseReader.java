package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;

/**
 * Created by chamilk on 3/21/17.
 */
public class OcrResponseReader {

    private TemplateMap templateMap;

    public OcrResponseReader(TemplateMap templateMap) {
        this.templateMap = templateMap;
    }

    public OcrFieldValue getFieldValueById(String id, OcrFieldData fieldData) {
        OcrFieldValue fieldValue = new OcrFieldValue();
        for (OcrFieldValue f : fieldData.getValue()) {
            if (f.getId().equalsIgnoreCase(id)) {
                fieldValue = f;
            }
        }
        return fieldValue;
    }

    public OcrFieldData getFieldDataById(String id, OcrResponse ocrResponse) {
        OcrFieldData data = new OcrFieldData();
        for (OcrFieldData fieldData : ocrResponse.getData()) {
            if (fieldData.getId().equalsIgnoreCase(id)) {
                data = fieldData;
            }
        }
        return data;
    }

    /**
     * The method will get the document type
     * @param id a value similar to "drivingLicenseFront##surname"
     * @return
     */
    public String getTemplateCategory(String id, OcrResponse ocrResponse){
        String documentType = id.split("##")[0];
        OcrFieldData templateNameDocument = getFieldDataById("TemplateName", ocrResponse);
        OcrFieldValue templateName = getFieldValueById(documentType + "##TemplateName", templateNameDocument);
        return templateMap.getCategoryByTemplateName(templateName.getValue());
    }

    public String getTemplateCategory(String templateName){
        return templateMap.getCategoryByTemplateName(templateName);
    }
}
