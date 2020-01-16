package com.example.danamontask.Model;

public class User {

    int id;
    String username;
    String email;
    String role;
    String password;

    public User(int id, String username, String email,  String role, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public User(){



    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String password) {
        this.role = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
