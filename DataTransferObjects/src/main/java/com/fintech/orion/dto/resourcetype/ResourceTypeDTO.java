package com.fintech.orion.dto.resourcetype;

import com.fintech.orion.dto.resource.ResourceDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class ResourceTypeDTO {

    private int id;
    private String type;
    private Set<ResourceDTO> resourceDTOs = new HashSet<ResourceDTO>(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<ResourceDTO> getResourceDTOs() {
        return resourceDTOs;
    }

    public void setResourceDTOs(Set<ResourceDTO> resourceDTOs) {
        this.resourceDTOs = resourceDTOs;
    }
}
