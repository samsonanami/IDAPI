package com.fintech.orion.hermesagentservices.transformer.mapper;

import com.fintech.orion.dto.response.api.FieldDataValue;
import com.fintech.orion.dto.response.external.DataValues;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ValueMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "value", target = "value"),
            @Mapping(source = "confidence", target = "confidence")
    })
    DataValues fieldDataValueToDataValues(FieldDataValue fieldDataValue);
}
