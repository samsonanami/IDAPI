package com.fintech.orion.hermesagentservices.transformer.mapper;

import com.fintech.orion.dto.hermese.model.oracle.response.OcrFieldValue;
import com.fintech.orion.dto.response.external.DataValues;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface OcrFieldValueMapper {

    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "value", target = "value"),
                    @Mapping(source = "confidence", target = "confidence")
            }
    )
    OcrFieldValue dataValuesToOcrFieldValue(DataValues dataValues);
}
