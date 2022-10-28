package com.example.ead_assignment.model;

public class QueueCounts {
    //creating variables
    private int TotalQueueLength;
    private int Car;
    private int MotorCycle;
    private int ThreeWheelers;
    private int Van;
    private int Lorry;
    private int Bus;

    //constructor
    public QueueCounts(int totalQueueLength, int car, int motorCycle, int threeWheelers, int van, int lorry, int bus) {
        TotalQueueLength = totalQueueLength;
        Car = car;
        MotorCycle = motorCycle;
        ThreeWheelers = threeWheelers;
        Van = van;
        Lorry = lorry;
        Bus = bus;
    }


    //getter and setter methods
    public int getTotalQueueLength() {
        return TotalQueueLength;
    }

    public void setTotalQueueLength(int totalQueueLength) {
        TotalQueueLength = totalQueueLength;
    }

    public int getCar() {
        return Car;
    }

    public void setCar(int car) {
        Car = car;
    }

    public int getMotorCycle() {
        return MotorCycle;
    }

    public void setMotorCycle(int motorCycle) {
        MotorCycle = motorCycle;
    }

    public int getThreeWheelers() {
        return ThreeWheelers;
    }

    public void setThreeWheelers(int threeWheelers) {
        ThreeWheelers = threeWheelers;
    }

    public int getVan() {
        return Van;
    }

    public void setVan(int van) {
        Van = van;
    }

    public int getLorry() {
        return Lorry;
    }

    public void setLorry(int lorry) {
        Lorry = lorry;
    }

    public int getBus() {
        return Bus;
    }

    public void setBus(int bus) {
        Bus = bus;
    }
}
