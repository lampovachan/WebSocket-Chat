package com.tkachuk.websocketproject.model;

public class LoggedUserModel {

    private String id;
    private String fullName;
    private String image;

    public LoggedUserModel(String id, String fullName, String image) {
        this.id = id;
        this.fullName = fullName;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getImage() {
        return image;
    }
}
