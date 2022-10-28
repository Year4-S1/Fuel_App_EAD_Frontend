package com.example.ead_assignment.model;

public class FuelStation {
    //creating variables
    private String Id;
    private String StationOwnerId;
    private String StationName;
    private String StationLocation;

    //constructor

    public FuelStation(String id, String stationOwnerId, String stationName, String stationLocation) {
        Id = id;
        StationOwnerId = stationOwnerId;
        StationName = stationName;
        StationLocation = stationLocation;
    }


    //getter and setter methods

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

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
