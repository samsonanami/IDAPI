package com.fintech.orion.dto.processresource;

import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.resource.ResourceDTO;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class ProcessResourceDTO {

    private Integer id;
    private ProcessDTO processDTO;
    private ResourceDTO resourceDTO;
    private String resourceName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProcessDTO getProcessDTO() {
        return processDTO;
    }

    public void setProcessDTO(ProcessDTO processDTO) {
        this.processDTO = processDTO;
    }

    public ResourceDTO getResourceDTO() {
        return resourceDTO;
    }

    public void setResourceDTO(ResourceDTO resourceDTO) {
        this.resourceDTO = resourceDTO;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
