package com.fintech.orion.hermesagentservices.transformer.mapper;

import com.fintech.orion.dto.response.api.FieldData;
import com.fintech.orion.dto.response.api.FieldDataValue;
import com.fintech.orion.dto.response.external.ProcessingFailure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {ValueMapper.class})
public interface ProcessingFailureMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "value", target = "value")
    })
    ProcessingFailure fieldDataToProcessingFailure(FieldDataValue fieldDataValue);
}
