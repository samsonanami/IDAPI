package com.fintech.orion.coreservices;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClientServiceTest.class,
        ProcessingRequestServiceTest.class,
        ProcessResourceServiceTest.class,
        ProcessServiceTest.class,
        ProcessTypeServiceTest.class,
        ResourceServiceTest.class,
        ResourceTypeServiceTest.class })
public class ServicesTests {
}
