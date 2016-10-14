package com.fintech.orion.helper.objectcreators;

import com.fintech.orion.dataabstraction.entities.orion.Client;
import com.fintech.orion.dataabstraction.helper.GenerateTimestamp;

import java.util.Date;

public class ClientBuilder {

    public static final int DEFAULT_ID = 1;
    public static final String DEFAULT_EMAIL = "default.email@default.com";
    public static final String DEFAULT_AUTH_TOKEN = "123456789";
    public static final Date DEFAULT_REGISTERED_ON = GenerateTimestamp.timestamp();
    public static final String DEFAULT_USER_NAME = "zone";
    public static final String DEFAULT_PASSWORD = "password";
    public static final String DEFAULT_REFRESH_TOKEN = "987654321";
    public static final boolean DEFAULT_ENNABLED = true;

    private Integer id;
    private String email;
    private String authToken;
    private Date registeredOn;
    private String userName;
    private String password;
    private String refreshToken;
    private boolean ennabled;

    private ClientBuilder() {}

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

    public static ClientBuilder aDefaultClient() {
        return ClientBuilder.aDefaultClient().withAuthToken("1234-5678");
    }

    public ClientBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public ClientBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public ClientBuilder withAuthToken(String authToken){
        this.authToken = authToken;
        return this;
    }

    public ClientBuilder withRegisteredOn(Date registeredOn){
        this.registeredOn = registeredOn;
        return this;
    }

    public ClientBuilder withUserName(String userName){
        this.userName = userName;
        return this;
    }

    public ClientBuilder withPassword(String password){
        this.password = password;
        return this;
    }

    public ClientBuilder withRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
        return this;
    }

    public ClientBuilder withEnnabled(boolean ennabled){
        this.ennabled = ennabled;
        return this;
    }

    public ClientBuilder but() {
        return ClientBuilder
                .aClient()
                .withId(id)
                .withEmail(email)
                .withAuthToken(authToken)
                .withRegisteredOn(registeredOn);
    }

    public Client build() {
        return new Client(email, authToken, registeredOn, userName, password, refreshToken, ennabled);
    }
}
