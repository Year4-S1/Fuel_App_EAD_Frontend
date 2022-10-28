package com.example.ead_assignment.model;

public class Queue {

    //creating variables
    private String Id;
    private String CustomerId;
    private String StationId;
    private String VehicleType;
    private String FuelType;
    private String Status;


    //constructor
    public Queue(String customerId, String stationId, String vehicleType, String fuelType, String status) {

        CustomerId = customerId;
        StationId = stationId;
        VehicleType = vehicleType;
        FuelType = fuelType;
        Status = status;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    //getter and setter methods
    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getStationId() {
        return StationId;
    }

    public void setStationId(String stationId) {
        StationId = stationId;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
