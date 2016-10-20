package com.fintech.orion.mapping.resourcemetadata;

import com.fintech.orion.dataabstraction.entities.orion.ResourceMetadata;
import com.fintech.orion.dto.resourcemetadata.ResourceMetadataDTO;
import com.fintech.orion.mapping.resource.ResourceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * ResourceMetadata entity mappings
 */
@Mapper(uses = ResourceMapper.class, componentModel = "spring")
public interface ResourceMetadataMapper {

    @Mappings({
            @Mapping(target = "resource", source = "id.resource"),
            @Mapping(target = "key", source = "id.key"),
            @Mapping(target = "resourceDTO", source = "resource"),
            @Mapping(target = "value", source = "value")
    })
    ResourceMetadataDTO resourceMetadataToResourceMetadataDTO(ResourceMetadata resourceMetadata);

    @Mappings({
            @Mapping(target = "id", expression = "java( new com.fintech.orion.dataabstraction.entities.orion.ResourceMetadataId(resourceMetadataDTO.getResource(), resourceMetadataDTO.getKey()))"),
            @Mapping(target = "resource", source = "resourceDTO"),
            @Mapping(target = "value", source = "value")
    })
    ResourceMetadata resourceMetadataDTOToResourceMetadata(ResourceMetadataDTO resourceMetadataDTO);

    List<ResourceMetadataDTO> resourceMetadatasToResourceMetadataDTOs(List<ResourceMetadata> resourceMetadatas);

}
