package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ead_assignment.model.QueueCountsFuel;
import com.example.ead_assignment.model.QueueCountsVehicles;
import com.example.ead_assignment.model.StationFuelStatus;
import com.google.gson.Gson;

public class QueueInfo extends AppCompatActivity {

    private TextView  car, bike, tuk, van, lorry, bus, p95, p92, deisel, superdiesel;
    private QueueCountsVehicles queueCountsVehicles;
    private QueueCountsFuel queueCountsFuel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queue_info);

        Gson gson = new Gson();
        queueCountsVehicles = gson.fromJson(getIntent().getStringExtra("queueVehicles"), QueueCountsVehicles.class);
        queueCountsFuel = gson.fromJson(getIntent().getStringExtra("queueFuel"), QueueCountsFuel.class);

        car = findViewById(R.id.car);
        bike = findViewById(R.id.bike);
        tuk = findViewById(R.id.tuk);
        van = findViewById(R.id.van);
        lorry = findViewById(R.id.lorry);
        bus = findViewById(R.id.bus);
//        p92 = findViewById(R.id.p92_num);
//        p95 = findViewById(R.id.p95);
//        deisel = findViewById(R.id.deisel);
//        superdiesel = findViewById(R.id.superdiesel);


        car.setText(String.valueOf(queueCountsVehicles.getCar()));
        bike.setText(String.valueOf(queueCountsVehicles.getMotorCycle()));
        tuk.setText(String.valueOf(queueCountsVehicles.getThreeWheelers()));
        van.setText(String.valueOf(queueCountsVehicles.getVan()));
        bus.setText(String.valueOf(queueCountsVehicles.getBus()));
        lorry.setText(String.valueOf(queueCountsVehicles.getLorry()));
//        p92.setText(String.valueOf(queueCountsFuel.getPetrol_92_Octane()));
//        p95.setText(String.valueOf(queueCountsFuel.getPetrol_95_Octane()));
//        deisel.setText(String.valueOf(queueCountsFuel.getDiesel()));
//        superdiesel.setText(String.valueOf(queueCountsFuel.getSuper_Deisel()));




    }
}