package com.example.test26_04.models;

import java.io.Serializable;

public class User implements Serializable {
    private String username, fullName, password, email, _id;

    public User(String username, String fullName, String password, String email, String _id) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this._id = _id;
    }

    public User(String username, String fullName, String password, String email) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = "";
        this.fullName = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
