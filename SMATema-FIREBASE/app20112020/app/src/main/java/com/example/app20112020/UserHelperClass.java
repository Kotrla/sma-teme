package com.example.app20112020;

public class UserHelperClass {
    private String email;
    private String name;
    private String pw;


    public UserHelperClass(String email, String name, String pw) {
        this.email = email;
        this.name = name;
        this.pw = pw;
    }

    public UserHelperClass() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
