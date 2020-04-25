package com.example.dash.model;

import java.util.Date;

public class PostUser {

    private String isAdmin;

    private String _id;

    private String username;

    private String name;

    private String surname;

    private String email;

    private String avatar;

    private Date createdAt;

    public String getIsAdmin() {
        return isAdmin;
    }

    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}