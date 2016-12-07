package com.bixspace.livesculture.data;

import java.io.Serializable;

/**
 * Created by junior on 27/11/16.
 */

public class AccessToken implements Serializable {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
