package com.fintech.orion.hermesagentservices.transformer.mapper;

import com.fintech.orion.dto.response.api.FieldData;
import com.fintech.orion.dto.response.external.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {ValueMapper.class, DataComparissonMapper.class})
public interface DataMapper {
    @Mappings({
            @Mapping(source = "id", target = "id")
    })
    Data fieldDataToData(FieldData fieldData);
}
