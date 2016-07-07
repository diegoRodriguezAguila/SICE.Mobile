package com.elfec.sice.model.security;

/**
 * Created by drodriguez on 07/07/2016.
 * Authentication credentials to send in order to get a token
 */
public class AuthCredentials {
    private String username;
    private String registrationToken;

    public AuthCredentials() {}

    public AuthCredentials(String username, String registrationToken) {
        this.username = username;
        this.registrationToken = registrationToken;
    }

    //region Getters Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }
    //endregion
}
