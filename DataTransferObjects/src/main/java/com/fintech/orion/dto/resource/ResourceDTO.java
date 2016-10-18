package com.fintech.orion.dto.resource;

import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.process.ProcessDTO;
import com.fintech.orion.dto.resourcetype.ResourceTypeDTO;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class ResourceDTO {

    private Integer id;
    private ClientDTO clientDTO;
    private ProcessDTO processDTO;
    private ResourceTypeDTO resourceTypeDTO;
    private String location;
    private String resourceIdentificationCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public ProcessDTO getProcessDTO() {
        return processDTO;
    }

    public void setProcessDTO(ProcessDTO processDTO) {
        this.processDTO = processDTO;
    }

    public ResourceTypeDTO getResourceTypeDTO() {
        return resourceTypeDTO;
    }

    public void setResourceTypeDTO(ResourceTypeDTO resourceTypeDTO) {
        this.resourceTypeDTO = resourceTypeDTO;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResourceIdentificationCode() {
        return resourceIdentificationCode;
    }

    public void setResourceIdentificationCode(String resourceIdentificationCode) {
        this.resourceIdentificationCode = resourceIdentificationCode;
    }
}
