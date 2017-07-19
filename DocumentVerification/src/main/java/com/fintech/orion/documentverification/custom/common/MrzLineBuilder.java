package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sasitha on 1/13/17.
 *
 */
public class MrzLineBuilder extends ValidationHelper {

    public String buildSingleLineMRZ(OcrResponse ocrResponse, String resourceName, String ocrFieldBase,
                                     int maxMrzLineCount, String preProcessNonePreProcessTag){
        String mrzLine = "";
        List<OcrFieldData> ocrFieldDataList = new ArrayList<>();
        for (int i =1 ; i <=maxMrzLineCount; i ++){
            OcrFieldData ocrFieldData = getFieldDataById(ocrFieldBase + String.valueOf(i), ocrResponse);
            ocrFieldDataList.add(ocrFieldData);
        }

        mrzLine= getSingleValueStringFromMultipleFields(resourceName, ocrFieldDataList, "", preProcessNonePreProcessTag);

        return mrzLine;
    }
}
