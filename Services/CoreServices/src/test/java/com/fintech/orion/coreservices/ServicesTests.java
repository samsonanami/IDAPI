package com.fintech.orion.coreservices;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Run service tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClientServiceTest.class,
        ProcessingRequestServiceTest.class,
        ProcessServiceTest.class,
        ProcessTypeServiceTest.class,
        ResourceServiceTest.class,
        ResourceTypeServiceTest.class })
public class ServicesTests {
}