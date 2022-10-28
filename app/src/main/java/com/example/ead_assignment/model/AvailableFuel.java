package com.example.ead_assignment.model;

public class AvailableFuel {

    //creating variables
    private String Id;
    private String StationId;
    private String FuelType;
    private String FuelAvailability;
    private String FuelAmount;

    //constructor
    public AvailableFuel(String id, String stationId, String fuelType, String fuelAvailability, String fuelAmount) {
        Id = id;
        StationId = stationId;
        FuelType = fuelType;
        FuelAvailability = fuelAvailability;
        FuelAmount = fuelAmount;
    }

    //getter and setter methods
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStationId() {
        return StationId;
    }

    public void setStationId(String stationId) {
        StationId = stationId;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public String getFuelAvailability() {
        return FuelAvailability;
    }

    public void setFuelAvailability(String fuelAvailability) {
        FuelAvailability = fuelAvailability;
    }

    public String getFuelAmount() {
        return FuelAmount;
    }

    public void setFuelAmount(String fuelAmount) {
        FuelAmount = fuelAmount;
    }
}
