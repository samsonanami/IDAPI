package com.fintech.orion.hermesagentservices.transformer.mapper;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldData;
import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.response.external.Data;
import com.fintech.orion.dto.response.external.DataValues;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface OcrFieldDataMapper {

    @Mappings({
            @Mapping(source = "id", target = "id")
    })
    OcrFieldData dataToOcrFeildData(Data data);

    List<OcrFieldValue> dataValuesToOcrFieldValues(List<DataValues> dataValues);

    OcrFieldValue dataValueToOcrFieldValue(DataValues dataValues);

}
