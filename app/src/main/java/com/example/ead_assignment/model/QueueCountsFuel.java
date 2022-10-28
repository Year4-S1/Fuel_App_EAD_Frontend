package com.example.ead_assignment.model;

public class QueueCountsFuel {
    //creating variables
    private int TotalQueueLength;
    private int Petrol_92_Octane;
    private int Petrol_95_Octane;
    private int Diesel;
    private int Super_Deisel;

    public QueueCountsFuel(int totalQueueLength, int petrol_92_Octane, int petrol_95_Octane, int diesel, int super_Deisel) {
        TotalQueueLength = totalQueueLength;
        Petrol_92_Octane = petrol_92_Octane;
        Petrol_95_Octane = petrol_95_Octane;
        Diesel = diesel;
        Super_Deisel = super_Deisel;
    }

    public int getTotalQueueLength() {
        return TotalQueueLength;
    }

    public void setTotalQueueLength(int totalQueueLength) {
        TotalQueueLength = totalQueueLength;
    }

    public int getPetrol_92_Octane() {
        return Petrol_92_Octane;
    }

    public void setPetrol_92_Octane(int petrol_92_Octane) {
        Petrol_92_Octane = petrol_92_Octane;
    }

    public int getPetrol_95_Octane() {
        return Petrol_95_Octane;
    }

    public void setPetrol_95_Octane(int petrol_95_Octane) {
        Petrol_95_Octane = petrol_95_Octane;
    }

    public int getDiesel() {
        return Diesel;
    }

    public void setDiesel(int diesel) {
        Diesel = diesel;
    }

    public int getSuper_Deisel() {
        return Super_Deisel;
    }

    public void setSuper_Deisel(int super_Deisel) {
        Super_Deisel = super_Deisel;
    }
}
