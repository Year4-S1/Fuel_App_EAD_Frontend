package com.example.ead_assignment.model;

public class FuelStation {
    //creating variables
    private String StationOwnerId;
    private String StationName;
    private String StationLocation;

    //constructor
    public FuelStation() {

    }

    //getter and setter methods
    public String getStationOwnerId() {
        return StationOwnerId;
    }

    public void setStationOwnerId(String stationOwnerId) {
        StationOwnerId = stationOwnerId;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    public String getStationLocation() {
        return StationLocation;
    }

    public void setStationLocation(String stationLocation) {
        StationLocation = stationLocation;
    }
}
