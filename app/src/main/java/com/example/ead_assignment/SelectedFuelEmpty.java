package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectedFuelEmpty extends AppCompatActivity {

    private TextView addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_fuel_empty);

        addbtn = findViewById(R.id.addFuel);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String fuel = getIntent().getStringExtra("FuelName");


                Intent addFuel = new Intent(SelectedFuelEmpty.this, Update_Fuel_status.class);
                addFuel.putExtra("FuelName", fuel);
                addFuel.putExtra("Action", "Add");
                startActivity(addFuel);
            }
        });
    }
}