package com.fintech.orion.mapping.license;

import com.fintech.orion.dataabstraction.entities.orion.License;
import com.fintech.orion.dto.license.LicenseDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by ChathurangaRW on 10/18/2016.
 */
@Mapper(componentModel = "spring")
public interface LicenseMapper {

    LicenseDTO licenseToLicenseDTO(License license);

    License licenseDTOToLicense(LicenseDTO licenseDTO);

    List<LicenseDTO> licensesToLicenseDTOs(List<License> licenses);

}
