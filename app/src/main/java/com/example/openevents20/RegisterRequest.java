package com.example.openevents20;

import java.io.Serializable;

public class RegisterRequest implements Serializable {

    String name;
    String last_name;
    String email;
    String password;
    String image;

    public RegisterRequest(String firstname, String lastname, String email, String pswd, String img) {
        this.name = firstname;
        this.last_name = lastname;
        this.email = email;
        this.password  = pswd;
        this.image = img;
    }

}
