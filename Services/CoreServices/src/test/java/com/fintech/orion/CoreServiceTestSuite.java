package com.fintech.orion;

import com.fintech.orion.mapping.client.ClientMapperTest;
import com.fintech.orion.service.core.file.FileHandlerServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by sasitha on 12/23/16.
 *
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ClientMapperTest.class,
        FileHandlerServiceTest.class
})
public class CoreServiceTestSuite {
}
