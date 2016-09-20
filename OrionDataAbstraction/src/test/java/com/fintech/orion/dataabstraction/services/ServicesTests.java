package com.fintech.orion.dataabstraction.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClientLicenseServiceTest.class,
        ClientServiceTest.class,
        LicenseServiceTest.class,
        ProcessingRequestServiceTest.class,
        ProcessResourceServiceTest.class,
        ProcessServiceTest.class,
        ProcessTypeLicenseServiceTest.class,
        ProcessTypeServiceTest.class,
        ResourceServiceTest.class,
        ResourceTypeServiceTest.class,
        ResponseServiceTest.class })
public class ServicesTests {
}
