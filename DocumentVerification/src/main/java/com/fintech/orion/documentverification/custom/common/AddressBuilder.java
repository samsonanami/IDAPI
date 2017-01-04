package com.fintech.orion.documentverification.custom.common;

import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrFieldValue;
import com.fintech.orion.dto.hermese.model.Oracle.response.OcrResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sasitha on 1/3/17.
 *
 */
public class AddressBuilder extends ValidationHelper{

    private static final String LINE_SEPARATOR = ", ";

    public String buildSingleLineAddressFromOcrResponse(OcrResponse ocrResponse, String resourceName,
                                                        String ocrFieldBase, int maximumNumberOfAddressLines){
        String address = "";
        List<OcrFieldData> addressLineFieldDataList = new ArrayList<>();
        for (int i = 0; i <= maximumNumberOfAddressLines; i++) {
            OcrFieldData ocrFieldData = getFieldDataById(ocrFieldBase+String.valueOf(i), ocrResponse);
            addressLineFieldDataList.add(ocrFieldData);
        }

        for (OcrFieldData fieldData : addressLineFieldDataList){
            OcrFieldValue fieldValue = getFieldValueById(resourceName+"##"+fieldData.getId(), fieldData);
            String addressValue = fieldValue.getValue();
            if (addressValue != null && !addressValue.isEmpty()){
                addressValue = addressValue.trim();
                address = address+addressValue+ LINE_SEPARATOR;
            }

        }
        if (address.endsWith(LINE_SEPARATOR)){
            address = address.substring(0,address.length() - 2);
        }
        address = address.toUpperCase();
        Pattern patternAddressWithFlat = Pattern.compile("FLAT [\\w\\d]+");
        Pattern patternAddressWithoutFlat = Pattern.compile("[\\w\\d]+");

        Matcher matchesWithFlat = patternAddressWithFlat.matcher(address);
        Matcher matchesWithoutFlat = patternAddressWithoutFlat.matcher(address);
        if (matchesWithFlat.find()){
            address = address.replace(matchesWithFlat.group(0), matchesWithFlat.group(0)+",");
        }else if (!matchesWithFlat.find() && matchesWithoutFlat.find()){
            address = address.replace(matchesWithoutFlat.group(0), matchesWithoutFlat.group(0)+",");
        }
        return address;
    }
}
