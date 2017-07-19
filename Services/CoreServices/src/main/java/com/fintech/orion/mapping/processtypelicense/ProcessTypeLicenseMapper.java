package com.fintech.orion.mapping.processtypelicense;

import com.fintech.orion.dataabstraction.entities.orion.ProcessTypeLicense;
import com.fintech.orion.dto.processtypelicense.ProcessTypeLicenseDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ProcessTypeLicense entity mappings
 */
@Mapper(componentModel = "spring")
public interface ProcessTypeLicenseMapper {

    ProcessTypeLicenseDTO processTypeLicenseToProcessTypeLicenseDTO(ProcessTypeLicense processTypeLicense);

    ProcessTypeLicense processTypeLicenseDTOToProcessTypeLicense(ProcessTypeLicenseDTO processTypeLicenseDTO);

    List<ProcessTypeLicenseDTO> processTypeLicensesToProcessTypeLicenseDTOs(List<ProcessTypeLicense> processTypeLicenses);
}
