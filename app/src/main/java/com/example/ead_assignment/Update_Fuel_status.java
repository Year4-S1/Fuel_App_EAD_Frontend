package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_assignment.model.Station;
import com.example.ead_assignment.model.StationFuelStatus;
import com.example.ead_assignment.utils.WebServiceClient;
import com.example.ead_assignment.utils.WebServiceInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Update_Fuel_status extends AppCompatActivity {

    RadioButton available, unavaialable;
    private EditText fuelAmount;
    private String fuel, action;
    private TextView addUpdateBtn,title;
    private SharedPreferences prefs ;
    private StationFuelStatus stationFuelStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_fuel_status);

        available =  findViewById(R.id.available);
        unavaialable = findViewById(R.id.unavailable);
        fuelAmount = findViewById(R.id.amount);
        addUpdateBtn = findViewById(R.id.addUpdateBtn);
        title = findViewById(R.id.addUpdateTitle);


        prefs = getSharedPreferences("FUELQ", Context.MODE_PRIVATE);



        if(getIntent().getStringExtra("Action").matches("Add")){
            addUpdateBtn.setText("Add");
            title.setText("Add Fuel");
        }
        else{
            addUpdateBtn.setText("Update");
            title.setText("Update Fuel");

        }


        addUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getStringExtra("Action").matches("Add")){
                    addFuel();
                }
                else{
                    updateFuel();
                }
            }
        });





    }

    public void addFuel(){

        String userValue = null;
        if (available.isChecked()) {
            userValue = available.getText().toString();
        } else if (unavaialable.isChecked()) {
            userValue = unavaialable.getText().toString();

        }
        StationFuelStatus info = new StationFuelStatus(prefs.getString("stationId",""),getIntent().getStringExtra("FuelName"),userValue,Integer.parseInt(fuelAmount.getText().toString()));

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<StationFuelStatus> call = webService.addFuel(info);

        call.enqueue(new Callback<StationFuelStatus>() {
            @Override
            public void onResponse(Call<StationFuelStatus> call, Response<StationFuelStatus> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                stationFuelStatus = response.body();

                Intent ownerhome = new Intent(Update_Fuel_status.this, OwnerHome.class);
                startActivity(ownerhome);

                Toast.makeText(getApplicationContext(),"Added Fuel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<StationFuelStatus> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });
    }


    public void updateFuel(){

        String userValue = null;
        int fuelamt = 0;
        if (available.isChecked()) {
            userValue = available.getText().toString();
            fuelamt = Integer.parseInt(fuelAmount.getText().toString());
        } else if (unavaialable.isChecked()) {
            userValue = unavaialable.getText().toString();
            fuelamt = 0;

        }

        StationFuelStatus info = new StationFuelStatus(userValue,fuelamt);


        System.out.println("fuelId " + getIntent().getStringExtra("fuelId"));

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<StationFuelStatus> call = webService.updateFuel(getIntent().getStringExtra("fuelId"),info);

        call.enqueue(new Callback<StationFuelStatus>() {
            @Override
            public void onResponse(Call<StationFuelStatus> call, Response<StationFuelStatus> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                stationFuelStatus = response.body();

                Intent ownerhome = new Intent(Update_Fuel_status.this, OwnerHome.class);
                startActivity(ownerhome);

                Toast.makeText(getApplicationContext(),"Added Fuel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<StationFuelStatus> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });

    }
}