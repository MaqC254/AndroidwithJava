package com.example.linkedin_mock;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String email;
    private String gender;
    private String shortBio;
    private String skills;
    private String phoneNumber;
    private String imagePath;

    // Add getters and setters
    public User(){

    };

    public User(String username, String email, String gender, String shortBio, String skills, String phoneNumber, String imagePath) {
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.shortBio = shortBio;
        this.skills = skills;
        this.phoneNumber = phoneNumber;
        this.imagePath = imagePath;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getShortBio() {
        return shortBio;
    }

    public String getSkills() {
        return skills;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getImagePath(){
        return imagePath;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
}

