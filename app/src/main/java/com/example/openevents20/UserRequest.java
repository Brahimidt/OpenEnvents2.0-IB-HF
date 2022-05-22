package com.example.openevents20;

public class UserRequest {

    int id;
    String name,last_name, email, image;

    public UserRequest(int id, String name, String last_name, String email, String image) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.image = image;
    }
}
