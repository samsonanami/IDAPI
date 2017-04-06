package com.fintech.orion.hermesagentservices.transformer.mapper;



import com.fintech.orion.dto.response.api.ValidationData;
import com.fintech.orion.dto.response.external.CustomValidation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface CustomValidationMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "value", target = "value"),
            @Mapping(source = "ocrConfidence", target = "confidence"),
            @Mapping(source = "remarks", target = "remarks"),
            @Mapping(source = "validationStatus", target = "status"),
            @Mapping(source = "criticalValidation", target = "criticalValidation")
    })
    CustomValidation validationDataToCustomValidation(ValidationData validationData);
}
