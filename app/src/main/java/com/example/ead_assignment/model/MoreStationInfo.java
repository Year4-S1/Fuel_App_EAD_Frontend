package com.example.ead_assignment.model;

public class MoreStationInfo
{
    //creating variables
    private  String name;
    private String address;
    private String availFuel;

    //constructor
    public MoreStationInfo(String name, String address, String availFuel) {
        this.name = name;
        this.address = address;
        this.availFuel = availFuel;
    }

    //getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvailFuel() {
        return availFuel;
    }

    public void setAvailFuel(String availFuel) {
        this.availFuel = availFuel;
    }


}
