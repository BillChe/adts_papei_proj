package com.example.adts_papei_proj.data;

public class User {

    public String username, email;
    private boolean isB1completed;

    public User(){}

    public User(String username, String email, boolean isB1completed) {
        this.username = username;
        this.email = email;
        this.isB1completed = isB1completed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isB1completed() {
        return isB1completed;
    }

    public void setB1completed(boolean b1completed) {
        isB1completed = b1completed;
    }
}
