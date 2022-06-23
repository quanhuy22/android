package com.example.test26_04.models;

import java.io.Serializable;

public class LoginBody implements Serializable {
    private String message;
    private User user;

    public LoginBody(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
