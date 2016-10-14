package com.fintech.orion.helper.objectcreators;

public final class TestClients {

    private TestClients() {}

    public static ClientBuilder aDefaultClient() {
        return ClientBuilder.aClient()
                .withEmail("zone@zone24x7.com")
                .withAuthToken("1234-5678");
    }

}
