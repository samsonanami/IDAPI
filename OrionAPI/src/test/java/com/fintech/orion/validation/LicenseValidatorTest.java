package com.fintech.orion.validation;

import com.fintech.orion.coreservices.ProcessTypeLicenseService;
import com.fintech.orion.coreservices.ProcessTypeLicenseServiceInterface;
import com.fintech.orion.dataabstraction.exceptions.ItemNotFoundException;
import com.fintech.orion.dataabstraction.exceptions.LicenseNotValidException;
import com.fintech.orion.dto.processtype.ProcessTypeDTO;
import com.fintech.orion.dto.processtypelicense.ProcessTypeLicenseDTO;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * LicenseValidator test class
 */
public class LicenseValidatorTest {

    @Test
    public void shouldValidWhenCheckLicenseForProcessTypesCalled() throws ParseException, ItemNotFoundException, LicenseNotValidException {
        LicenseValidatorInterface licenseValidatorInterface = new LicenseValidator();

        List<ProcessTypeLicenseDTO> processTypeLicenseDTOs = ObjectCreator.processTypeLicenseDTOs();

        ProcessTypeLicenseServiceInterface processTypeLicenseServiceInterfaceMock = mock(ProcessTypeLicenseService.class);
        when(processTypeLicenseServiceInterfaceMock.getAllForLicenseId(1)).thenReturn(processTypeLicenseDTOs);
        ReflectionTestUtils.setField(licenseValidatorInterface, "processTypeLicenseServiceInterface", processTypeLicenseServiceInterfaceMock);

        List<ProcessTypeDTO> processTypeDTOs = ObjectCreator.processTypeDTOs1();
        licenseValidatorInterface.checkLicenseForProcessTypes(processTypeDTOs, 1);
    }

    @Test(expected = LicenseNotValidException.class)
    public void shouldReturnExceptionForProcessTypesCalled() throws ParseException, ItemNotFoundException, LicenseNotValidException {
        LicenseValidatorInterface licenseValidatorInterface = new LicenseValidator();

        List<ProcessTypeLicenseDTO> processTypeLicenseDTOs = ObjectCreator.processTypeLicenseDTOs();

        ProcessTypeLicenseServiceInterface processTypeLicenseServiceInterfaceMock = mock(ProcessTypeLicenseService.class);
        when(processTypeLicenseServiceInterfaceMock.getAllForLicenseId(1)).thenReturn(processTypeLicenseDTOs);
        ReflectionTestUtils.setField(licenseValidatorInterface, "processTypeLicenseServiceInterface", processTypeLicenseServiceInterfaceMock);

        List<ProcessTypeDTO> processTypeDTOs = ObjectCreator.processTypeDTOs2();
        licenseValidatorInterface.checkLicenseForProcessTypes(processTypeDTOs, 1);
    }

    @Test(expected = LicenseNotValidException.class)
    public void shouldReturnExceptionWhenLicenseNotFoundForProcessTypesCalled() throws ParseException, ItemNotFoundException, LicenseNotValidException {
        LicenseValidatorInterface licenseValidatorInterface = new LicenseValidator();

        List<ProcessTypeLicenseDTO> processTypeLicenseDTOs = ObjectCreator.processTypeLicenseDTOs();

        ProcessTypeLicenseServiceInterface processTypeLicenseServiceInterfaceMock = mock(ProcessTypeLicenseService.class);
        when(processTypeLicenseServiceInterfaceMock.getAllForLicenseId(2)).thenThrow(ItemNotFoundException.class);
        ReflectionTestUtils.setField(licenseValidatorInterface, "processTypeLicenseServiceInterface", processTypeLicenseServiceInterfaceMock);

        List<ProcessTypeDTO> processTypeDTOs = ObjectCreator.processTypeDTOs2();
        licenseValidatorInterface.checkLicenseForProcessTypes(processTypeDTOs, 2);
    }

}
