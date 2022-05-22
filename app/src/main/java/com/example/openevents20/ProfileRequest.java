package com.example.openevents20;

import java.io.Serializable;

public class ProfileRequest implements Serializable {

    int id;
    String name;
    String last_name;
    String email;
    String password;
    String image;

    public ProfileRequest(int id, String firstname, String lastname, String email, String pswd, String img) {
        this.id = id;
        this.name = firstname;
        this.last_name = lastname;
        this.email = email;
        this.password  = pswd;
        this.image = img;
    }

}
