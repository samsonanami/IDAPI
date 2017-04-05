package com.fintech.orion.resource.support;

import com.fintech.orion.dto.resource.support.ResourceSupportDTO;

import java.util.List;
import java.util.Objects;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public class CommonResourceSupport implements ResourceSupport {

    private List<ResourceSupportDTO> resourceSupportDTOs;

    public CommonResourceSupport(List<ResourceSupportDTO> resourceSupportDTOs) {
        this.resourceSupportDTOs = resourceSupportDTOs;
    }

    @Override
    public boolean isSupportExtension(String extension) {
        for (ResourceSupportDTO resourceSupportDTO: resourceSupportDTOs) {
            if(Objects.equals(resourceSupportDTO.getFileExtension(), extension)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSupportFileSize(String extension, long fileSize) {
        for (ResourceSupportDTO resourceSupportDTO: resourceSupportDTOs) {
            if(Objects.equals(resourceSupportDTO.getFileExtension(), extension)) {
                return resourceSupportDTO.getFileSizeLimit() >= fileSize;
            }
        }
        return false;
    }

}
