package com.example.ead_assignment;

public class User {

    //creating variables
    private String Id;
    private String UserName;
    private String UserPhoneNo;
    private String UserPassword;
    private String UserType;
    private  boolean LoginStatus;


    //constructors
    public User() {

    }

    public User(String id, String userName, String userPhoneNo, String userPassword, String userType, boolean loginStatus) {
        Id = id;
        UserName = userName;
        UserPhoneNo = userPhoneNo;
        UserPassword = userPassword;
        UserType = userType;
        LoginStatus = loginStatus;
    }


    //getter and setter methods
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhoneNo() {
        return UserPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        UserPhoneNo = userPhoneNo;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public boolean isLoginStatus() {
        return LoginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        LoginStatus = loginStatus;
    }
}
