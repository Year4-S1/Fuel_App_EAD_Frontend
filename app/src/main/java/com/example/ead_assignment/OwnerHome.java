package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_assignment.model.QueueCountsFuel;
import com.example.ead_assignment.model.QueueCountsVehicles;
import com.example.ead_assignment.model.Station;
import com.example.ead_assignment.model.StationFuelStatus;
import com.example.ead_assignment.utils.WebServiceClient;
import com.example.ead_assignment.utils.WebServiceInterface;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerHome extends AppCompatActivity {

    private TextView petrol92Btn, petrol95Btn, deiselBtn, superdieselBtn,stationName, queueInfo;
    private StationFuelStatus stationFuelStatus;
    private SharedPreferences prefs ;
    private QueueCountsVehicles queueCountsVehicles;
    private QueueCountsFuel queueCountsFuel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_home);


        petrol92Btn = findViewById(R.id.petrol92btn);
        petrol95Btn = findViewById(R.id.petrol95btn);
        deiselBtn = findViewById(R.id.deiselBtn);
        superdieselBtn = findViewById(R.id.superdeiselBtn);
        queueInfo = findViewById(R.id.queueInfo);



        stationName = findViewById(R.id.stationName);


        prefs = getSharedPreferences("FUELQ", Context.MODE_PRIVATE);


        stationName.setText(prefs.getString("stationName",""));

        //onclick listener to fuel buttons
        onclickListeners();

    }


    public void onclickListeners(){
        petrol92Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFuelDetails("Petrol 92 Octane");
            }
        });

        petrol95Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFuelDetails("Petrol 95 Octane");
            }
        });

        deiselBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFuelDetails("Diesel");
            }
        });

        superdieselBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFuelDetails("Super Diesel");
            }
        });

        queueInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getQueueInfo();
            }
        });

    }

    public void getFuelDetails(String fuelType){


        System.out.println(prefs.getString("stationId",""));
        System.out.println(fuelType);

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<StationFuelStatus> call = webService.getFuelDetailsStation(prefs.getString("stationId",""),fuelType);

        call.enqueue(new Callback<StationFuelStatus>() {
            @Override
            public void onResponse(Call<StationFuelStatus> call, Response<StationFuelStatus> response) {
                Log.d("TAG", "onResponse: " + response.code());

//                assert response.body() != null : "Response Body Empty";

                System.out.println(response.body());

                if(response.body() == null){
                    Intent addFuel = new Intent(OwnerHome.this, SelectedFuelEmpty.class);
                    addFuel.putExtra("FuelName", fuelType);
                    startActivity(addFuel);
                }
                else{

                    stationFuelStatus = response.body();

                    Gson gson = new Gson();
                    String value = gson.toJson(stationFuelStatus);



                    Intent selectedFuel = new Intent(OwnerHome.this, SelectedFuel.class);
                    selectedFuel.putExtra("fueldetails", value);
                    startActivity(selectedFuel);
                }

//                stationFuelStatus = response.body();






                Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<StationFuelStatus> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });



    }

    public void getQueueInfo(){

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<QueueCountsVehicles> call = webService.getQueueByVehicle(prefs.getString("stationId",""));

        call.enqueue(new Callback<QueueCountsVehicles>() {
            @Override
            public void onResponse(Call<QueueCountsVehicles> call, Response<QueueCountsVehicles> response) {
                Log.d("TAG", "onResponse: " + response.code());


                    queueCountsVehicles = response.body();

                    Gson gson = new Gson();
                    String value = gson.toJson(queueCountsVehicles);



                    Intent queueinfo = new Intent(OwnerHome.this, QueueInfo.class);
                    queueinfo.putExtra("queueVehicles", value);
                    startActivity(queueinfo);


                    getQueueByFuel();

            }

            @Override
            public void onFailure(Call<QueueCountsVehicles> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });

    }

    public void getQueueByFuel(){

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<QueueCountsFuel> call = webService.getQueueByFuel(prefs.getString("stationId",""));

        call.enqueue(new Callback<QueueCountsFuel>() {
            @Override
            public void onResponse(Call<QueueCountsFuel> call, Response<QueueCountsFuel> response) {
                Log.d("TAG", "onResponse: " + response.code());


                queueCountsFuel = response.body();

                Gson gson = new Gson();
                String vehicle = gson.toJson(queueCountsVehicles);
                String fuel = gson.toJson(queueCountsFuel);



                Intent queueinfo = new Intent(OwnerHome.this, QueueInfo.class);
                queueinfo.putExtra("queueVehicles", vehicle);
                queueinfo.putExtra("queueFuel", fuel);
                startActivity(queueinfo);








            }

            @Override
            public void onFailure(Call<QueueCountsFuel> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });

    }

}