package com.fintech.orion.hermesagentservices.transformer.custom;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrResponse;
import com.fintech.orion.dto.response.external.Data;
import com.fintech.orion.dto.response.external.VerificationResponse;
import com.fintech.orion.hermesagentservices.transformer.ReVerificationDataTransformer;
import com.fintech.orion.hermesagentservices.transformer.mapper.OcrFieldDataMapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReVerificationOCRDataTransformer implements ReVerificationDataTransformer<OcrResponse> {


    @Override
    public OcrResponse transform(VerificationResponse verificationResponse) {
        OcrResponse ocrResponse = new OcrResponse();

        ocrResponse.setVerificationRequestId("");
        ocrResponse.setStatus(verificationResponse.getStatus());
        ocrResponse.setData(getDataList(verificationResponse.getData()));
        return ocrResponse;
    }

    private List<OcrFieldData> getDataList(List<Data> data) {

        OcrFieldDataMapper ocrFieldDataMapper = Mappers.getMapper(OcrFieldDataMapper.class);
        return data.stream()
                .map(ocrFieldDataMapper::dataToOcrFeildData)
                .collect(Collectors.toList());
    }
}
