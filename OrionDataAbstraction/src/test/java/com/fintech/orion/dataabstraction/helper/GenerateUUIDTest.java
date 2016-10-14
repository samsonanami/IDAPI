package com.fintech.orion.dataabstraction.helper;

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
