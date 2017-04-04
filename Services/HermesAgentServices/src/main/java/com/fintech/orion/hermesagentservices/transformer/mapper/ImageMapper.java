package com.fintech.orion.hermesagentservices.transformer.mapper;

import com.fintech.orion.dto.response.api.ImageDetail;
import com.fintech.orion.dto.response.external.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ImageMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "resourceName", target = "imageName")
    })
    Image imageDetailsToImage(ImageDetail imageDetail);
}
