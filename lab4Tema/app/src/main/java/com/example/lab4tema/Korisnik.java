package com.example.lab4tema;

public class Korisnik {


    private  String username,password,email;
    private int age;

    public Korisnik(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Korisnik(String username, String password, String email, int age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {

        return age;
    }
}
