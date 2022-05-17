package com.example.openevents20;

import java.io.Serializable;

public class Token implements Serializable {

    String accessToken;

    public Token(String token) {
        this.accessToken = token;
    }
}
