package com.example.ead_assignment.model;

public class LoginModel {
    //creating variables
    private String PhoneNo;
    private String Password;

    //constructor
    public LoginModel(String phoneNo, String password) {
        PhoneNo = phoneNo;
        Password = password;
    }

    //getter and setter methods
    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
