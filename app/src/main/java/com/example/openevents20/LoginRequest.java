package com.example.openevents20;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    String email;
    String password;

    public LoginRequest(String email, String pswd) {
        this.email = email;
        this.password  = pswd;
    }

}
