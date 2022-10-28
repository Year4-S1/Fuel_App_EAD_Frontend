package com.example.ead_assignment.model;

public class Station {

    //creating variables
    private String StationOwnerId;
    private String StationName;
    private String StationLocation;

    //constructor
    public Station(String stationOwnerId, String stationName, String stationLocation) {
        StationOwnerId = stationOwnerId;
        StationName = stationName;
        StationLocation = stationLocation;
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
