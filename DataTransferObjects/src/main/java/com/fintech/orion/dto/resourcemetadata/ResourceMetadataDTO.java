package com.fintech.orion.dto.resourcemetadata;

import com.fintech.orion.dto.resource.ResourceDTO;

/**
 * ResourceMetadata dto
 */
public class ResourceMetadataDTO {
    private int resource;
    private String key;
    private ResourceDTO resourceDTO;
    private String value;

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ResourceDTO getResourceDTO() {
        return resourceDTO;
    }

    public void setResourceDTO(ResourceDTO resourceDTO) {
        this.resourceDTO = resourceDTO;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
