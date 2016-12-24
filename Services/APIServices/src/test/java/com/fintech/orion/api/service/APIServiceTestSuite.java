package com.fintech.orion.api.service;

import com.fintech.orion.api.service.client.ClientLicenseServiceTest;
import com.fintech.orion.api.service.io.destination.generic.GenericDestinationProviderTest;
import com.fintech.orion.api.service.io.file.LocalFilePersistenceTest;
import com.fintech.orion.api.service.request.ProcessingRequestServiceTest;
import com.fintech.orion.api.service.validator.ClientLicenseValidatorServiceTest;
import com.fintech.orion.api.service.validator.ClientValidatorTest;
import com.fintech.orion.api.service.validator.ProcessingRequestJsonFormatValidatorTest;
import com.fintech.orion.api.service.validator.file.FileValidatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by sasitha on 12/23/16.
 *
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        ClientLicenseServiceTest.class,
        GenericDestinationProviderTest.class,
        LocalFilePersistenceTest.class,
        ProcessingRequestServiceTest.class,
        FileValidatorTest.class,
        ClientLicenseValidatorServiceTest.class,
        ClientValidatorTest.class,
        ProcessingRequestJsonFormatValidatorTest.class
})
public class APIServiceTestSuite {
}
