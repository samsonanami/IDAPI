package com.fintech.orion.dataabstraction.helper;

import java.util.UUID;

public class GenerateUUID {

    private GenerateUUID() {}

    public static String uuidNumber() {
        return UUID.randomUUID().toString();
    }
}
