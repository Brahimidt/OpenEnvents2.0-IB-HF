package com.example.openevents20;

import java.util.Date;

public class EventsRequest {

    int id;
    String name;
    int owner_id;
    Date date;
    String image;
    String location;
    String description;
    Date eventStart_date;
    Date eventEnd_date;
    int n_participators;
    String slug;
    String type;

    public EventsRequest(int id, String name, int owner_id, Date date, String image, String location, String description, Date eventStart_date, Date eventEnd_date, int n_participators, String slug, String type) {
        this.id = id;
        this.name = name;
        this.owner_id = owner_id;
        this.date = date;
        this.image = image;
        this.location = location;
        this.description = description;
        this.eventStart_date = eventStart_date;
        this.eventEnd_date = eventEnd_date;
        this.n_participators = n_participators;
        this.slug = slug;
        this.type = type;
    }
}
