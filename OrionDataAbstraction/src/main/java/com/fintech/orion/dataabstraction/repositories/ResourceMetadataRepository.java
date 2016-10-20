package com.fintech.orion.dataabstraction.repositories;

import com.fintech.orion.dataabstraction.entities.common.AbstractDAO;
import com.fintech.orion.dataabstraction.entities.orion.ResourceMetadata;
import org.springframework.stereotype.Repository;

/**
 * ResourceMetadata entity repository
 */
@Repository
public class ResourceMetadataRepository extends AbstractDAO<ResourceMetadata, Integer> implements ResourceMetadataRepositoryInterface {

    protected ResourceMetadataRepository() {
        super(ResourceMetadata.class);
    }

}
