package com.fintech.orion.coreservices;

import com.fintech.orion.common.ServiceInterface;
import com.fintech.orion.dataabstraction.entities.orion.ResourceMetadata;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dto.resourcemetadata.ResourceMetadataDTO;

import java.util.List;

/**
 * ResourceMetadata service interface
 */
public interface ResourceMetadataServiceInterface extends ServiceInterface<ResourceMetadata, Integer> {

    List<ResourceMetadataDTO> getAllDTOs();

    ResourceMetadataDTO findById(int id) throws ItemNotFoundException;

    void saveOrUpdate(ResourceMetadataDTO resourceMetadataDTO);

    void delete(ResourceMetadataDTO resourceMetadataDTO);

}
