package com.fintech.orion.model;

public class Configuration {
    private String sftpHost;
    private int sftpPort;
    private String sftpUser;
    private String sftpPass;

    public Configuration(String sftpHost, int sftpPort, String sftpUser, String sftpPass) {
        this.sftpHost = sftpHost;
        this.sftpPort = sftpPort;
        this.sftpUser = sftpUser;
        this.sftpPass = sftpPass;
    }

    public String getSftpHost() {
        return sftpHost;
    }

    public void setSftpHost(String sftpHost) {
        this.sftpHost = sftpHost;
    }

    public int getSftpPort() {
        return sftpPort;
    }

    public void setSftpPort(int sftpPort) {
        this.sftpPort = sftpPort;
    }

    public String getSftpUser() {
        return sftpUser;
    }

    public void setSftpUser(String sftpUser) {
        this.sftpUser = sftpUser;
    }

    public String getSftpPass() {
        return sftpPass;
    }

    public void setSftpPass(String sftpPass) {
        this.sftpPass = sftpPass;
    }
}
