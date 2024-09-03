package com.example.trackmyride.ui;

public class Helper2
{



    String name, email, username, password,ID;
    public Helper2(String name, String email, String username, String password,String id) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.ID=id;
    }
    public Helper2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID(){return ID;}

    public void setID(String ID){this.ID=ID;}
}
