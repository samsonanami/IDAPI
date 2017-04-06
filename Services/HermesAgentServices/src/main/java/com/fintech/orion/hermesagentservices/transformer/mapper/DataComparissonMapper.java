package com.fintech.orion.hermesagentservices.transformer.mapper;

import com.fintech.orion.dto.response.api.FieldDataComparision;
import com.fintech.orion.dto.response.external.DataComparision;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface DataComparissonMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "value", target = "status")
    })
    DataComparision fieldDataComparisionToDataComparison(FieldDataComparision fieldDataComparision);
}
