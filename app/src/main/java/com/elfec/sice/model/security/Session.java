package com.elfec.sice.model.security;

/**
 * Created by drodriguez on 07/07/2016.
 * Session
 */
public class Session {
    private static final String BEARER_TOKEN = "Bearer";
    private String username;
    private String authenticationToken;
    private AccessToken accessToken;

    public AccessToken getAccessToken(){
        if(accessToken == null)
            accessToken = new AccessToken(authenticationToken, BEARER_TOKEN);
        return accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
        this.accessToken = null;
    }
}
