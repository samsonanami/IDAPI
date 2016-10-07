package com.fintech.orion;

import com.fintech.orion.dataabstraction.helper.GenerateUUID;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class GenerateUUIDTest {

    @Test
    public void shouldReturnUniqueUUIDWhenGetUUIDCalled() {
        String s1 = GenerateUUID.uuidNumber();
        String s2 = GenerateUUID.uuidNumber();
        assertFalse(s1.equals(s2));
    }

}
