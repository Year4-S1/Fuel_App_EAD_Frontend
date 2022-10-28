package com.example.ead_assignment.model;

public class StationFuelStatus {
    private String Id;
    private String StationId;
    private String FuelType;
    private String FuelAvailability;
    private int FuelAmount;

    public StationFuelStatus(String id, String stationId, String fuelType, String fuelAvailability, int fuelAmount) {
        Id = id;
        StationId = stationId;
        FuelType = fuelType;
        FuelAvailability = fuelAvailability;
        FuelAmount = fuelAmount;
    }


    public StationFuelStatus(String stationId, String fuelType, String fuelAvailability, int fuelAmount) {
        StationId = stationId;
        FuelType = fuelType;
        FuelAvailability = fuelAvailability;
        FuelAmount = fuelAmount;
    }

    public StationFuelStatus(String fuelAvailability, int fuelAmount) {
        FuelAvailability = fuelAvailability;
        FuelAmount = fuelAmount;
    }

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

    public int getFuelAmount() {
        return FuelAmount;
    }

    public void setFuelAmount(int fuelAmount) {
        FuelAmount = fuelAmount;
    }
}
