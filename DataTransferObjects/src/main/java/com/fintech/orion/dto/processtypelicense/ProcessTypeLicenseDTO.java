package com.fintech.orion.dto.processtypelicense;

import com.fintech.orion.dto.license.LicenseDTO;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;

/**
 * Created by ChathurangaRW on 10/17/2016.
 */
public class ProcessTypeLicenseDTO {

    private Integer id;
    private LicenseDTO licenseDTO;
    private ProcessTypeDTO processTypeDTO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LicenseDTO getLicenseDTO() {
        return licenseDTO;
    }

    public void setLicenseDTO(LicenseDTO licenseDTO) {
        this.licenseDTO = licenseDTO;
    }

    public ProcessTypeDTO getProcessTypeDTO() {
        return processTypeDTO;
    }

    public void setProcessTypeDTO(ProcessTypeDTO processTypeDTO) {
        this.processTypeDTO = processTypeDTO;
    }
}
