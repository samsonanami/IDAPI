package com.fintech.orion.coreservices;

import com.fintech.orion.common.AbstractService;
import com.fintech.orion.dataabstraction.entities.orion.ResourceMetadata;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.repositories.ResourceMetadataRepositoryInterface;
import com.fintech.orion.dto.resourcemetadata.ResourceMetadataDTO;
import com.fintech.orion.mapping.resourcemetadata.ResourceMetadataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ResourceMetadata service
 */
@Service
public class ResourceMetadataService extends AbstractService<ResourceMetadata, Integer> implements ResourceMetadataServiceInterface {

    @Autowired
    private ResourceMetadataRepositoryInterface resourceMetadataRepositoryInterface;

    @Autowired
    private ResourceMetadataMapper resourceMetadataMapper;

    @Override
    public List<ResourceMetadataDTO> getAllDTOs() {
        return resourceMetadataMapper.resourceMetadatasToResourceMetadataDTOs(resourceMetadataRepositoryInterface.getAll());
    }

    @Override
    public ResourceMetadataDTO findById(int id) throws ItemNotFoundException {
        return resourceMetadataMapper.resourceMetadataToResourceMetadataDTO(resourceMetadataRepositoryInterface.findById(id));
    }

    @Override
    public void saveOrUpdate(ResourceMetadataDTO resourceMetadataDTO) {
        resourceMetadataRepositoryInterface.saveOrUpdate(resourceMetadataMapper.resourceMetadataDTOToResourceMetadata(resourceMetadataDTO));
    }

    @Override
    public void delete(ResourceMetadataDTO resourceMetadataDTO) {
        resourceMetadataRepositoryInterface.delete(resourceMetadataMapper.resourceMetadataDTOToResourceMetadata(resourceMetadataDTO));
    }
}
