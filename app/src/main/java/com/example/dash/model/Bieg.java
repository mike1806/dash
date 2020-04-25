package com.example.dash.model;

public class Bieg {

    private Integer _id;

    private String name;

    private String image;

    private String description;

    public Bieg(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public Integer get_id() {
        return _id;
    }
}


