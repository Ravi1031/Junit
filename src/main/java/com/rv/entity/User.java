package com.rv.entity;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;

    public User() {
    }

    public User(String id, String firstName, String lastName, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String id() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String firstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String lastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String gender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
