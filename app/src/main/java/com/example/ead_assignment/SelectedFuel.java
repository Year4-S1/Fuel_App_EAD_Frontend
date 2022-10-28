package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ead_assignment.model.StationFuelStatus;
import com.google.gson.Gson;

public class SelectedFuel extends AppCompatActivity {

    StationFuelStatus fuelDetails;
    private TextView stationName, fuelType, status, litres, updateBtn;
    private SharedPreferences prefs ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_fuel);

        prefs = getSharedPreferences("FUELQ", Context.MODE_PRIVATE);


        Gson gson = new Gson();
        fuelDetails = gson.fromJson(getIntent().getStringExtra("fueldetails"), StationFuelStatus.class);

        stationName = findViewById(R.id.stationName);
        stationName.setText(prefs.getString("stationName",""));

        fuelType = findViewById(R.id.fuelType);
        fuelType.setText(fuelDetails.getFuelType());

        status = findViewById(R.id.status);
        status.setText(fuelDetails.getFuelAvailability());

        litres = findViewById(R.id.litres);
        litres.setText(String.valueOf(fuelDetails.getFuelAmount()));

        updateBtn = findViewById(R.id.updateFuel);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent addFuel = new Intent(SelectedFuel.this, Update_Fuel_status.class);
                addFuel.putExtra("FuelName", fuelDetails.getFuelType());
                addFuel.putExtra("Action", "Update");
                addFuel.putExtra("fuelId", fuelDetails.getId());
                startActivity(addFuel);
            }
        });


    }

}