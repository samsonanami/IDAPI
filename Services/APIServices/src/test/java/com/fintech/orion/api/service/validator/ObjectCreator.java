package com.fintech.orion.api.service.validator;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.helper.GenerateTimestamp;
import com.fintech.orion.dto.client.ClientDTO;
import com.fintech.orion.dto.license.LicenseDTO;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.dto.processtypelicense.ProcessTypeLicenseDTO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Object Creator for validation
 */
public class ObjectCreator {

    private ObjectCreator() {}

    private static final String ID_VERIFICATION = "idVerification";

    public static List<ProcessTypeLicenseDTO> processTypeLicenseDTOs() throws ParseException {
        LicenseDTO licenseDTO1 = createLicenseDTO(1, GenerateTimestamp.timestamp("2016-10-10"), GenerateTimestamp.timestamp("2016-12-10"), 100, 1000);

        ProcessTypeDTO processTypeDTO1 = createProcessTypeDTO(1, ID_VERIFICATION);
        ProcessTypeDTO processTypeDTO2 = createProcessTypeDTO(2, "test");

        ProcessTypeLicenseDTO processTypeLicenseDTO1 = createProcessTypeLicenseDTO(1, licenseDTO1, processTypeDTO1);
        ProcessTypeLicenseDTO processTypeLicenseDTO2 = createProcessTypeLicenseDTO(2, licenseDTO1, processTypeDTO2);

        List<ProcessTypeLicenseDTO> processTypeLicenseDTOs = new ArrayList<>();
        processTypeLicenseDTOs.add(processTypeLicenseDTO1);
        processTypeLicenseDTOs.add(processTypeLicenseDTO2);

        return processTypeLicenseDTOs;
    }

    public static List<ProcessTypeDTO> processTypeDTOs1(){
        List<ProcessTypeDTO> processTypeDTOs = new ArrayList<>();

        ProcessTypeDTO processTypeDTO1 = createProcessTypeDTO(1, ID_VERIFICATION);

        processTypeDTOs.add(processTypeDTO1);
        return processTypeDTOs;
    }

    public static List<ProcessTypeDTO> processTypeDTOs2(){
        List<ProcessTypeDTO> processTypeDTOs = new ArrayList<>();

        ProcessTypeDTO processTypeDTO1 = createProcessTypeDTO(1, ID_VERIFICATION);
        ProcessTypeDTO processTypeDTO2 = createProcessTypeDTO(2, "test");
        ProcessTypeDTO processTypeDTO3 = createProcessTypeDTO(3, "fail");

        processTypeDTOs.add(processTypeDTO1);
        processTypeDTOs.add(processTypeDTO2);
        processTypeDTOs.add(processTypeDTO3);
        return processTypeDTOs;
    }

    private static ProcessTypeLicenseDTO createProcessTypeLicenseDTO(int id, LicenseDTO licenseDTO, ProcessTypeDTO processTypeDTO) {
        ProcessTypeLicenseDTO processTypeLicenseDTO = new ProcessTypeLicenseDTO();
        processTypeLicenseDTO.setId(id);
        processTypeLicenseDTO.setLicenseDTO(licenseDTO);
        processTypeLicenseDTO.setProcessTypeDTO(processTypeDTO);
        return processTypeLicenseDTO;
    }

    private static LicenseDTO createLicenseDTO(int id, Date startDate, Date endDate, int currentRequestCount, int maximumRequestCount) {
        LicenseDTO licenseDTO = new LicenseDTO();
        licenseDTO.setId(id);
        licenseDTO.setStartDate(startDate);
        licenseDTO.setEndDate(endDate);
        licenseDTO.setCurrentRequestCount(currentRequestCount);
        licenseDTO.setMaximumRequestCount(maximumRequestCount);
        return licenseDTO;
    }

    private static ProcessTypeDTO createProcessTypeDTO(int id, String type) {
        ProcessTypeDTO processTypeDTO = new ProcessTypeDTO();
        processTypeDTO.setId(id);
        processTypeDTO.setType(type);
        return processTypeDTO;
    }

    public static Client aClient() { return new Client(); }

    public static ClientDTO aClientDTO(){
        return new ClientDTO();
    }

}
