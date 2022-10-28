package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_assignment.model.Station;
import com.example.ead_assignment.utils.WebServiceClient;
import com.example.ead_assignment.utils.WebServiceInterface;
import com.google.gson.Gson;

import java.security.acl.Owner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//registering stations
public class RegisterStation extends AppCompatActivity {

    //creating variables
    private User owner;
    private TextView signupBtn;
    private EditText stationName, location;
    String name ;
    String loc ;

    User signedUser;

    Station signedStation;


    //oncreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_station);

        //getting element references by id

        //getting values passed by intent
        Gson gson = new Gson();
        owner = gson.fromJson(getIntent().getStringExtra("Owner"), User.class);

        Log.e("owner", String.valueOf(owner));

        stationName = findViewById(R.id.stationName);
        location = findViewById(R.id.location);
        signupBtn = findViewById(R.id.stationSignup);


        //onclick listener to signup btn
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupClicked();
            }
        });





    }


    //signupvaidation to check if all fields are filled
    public void signupClicked(){

        name = stationName.getText().toString();
        loc = location.getText().toString();

        if (name.matches("") || loc.matches("")) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (!name.matches("") || !loc.matches("")) {
            signupOwner();
            return;
        }
    }


    //if all fields are filled, proceed with signup for both owner and station
    public void signupOwner(){
        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<User> call = webService.signupUser(owner);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                signedUser = response.body();

                Log.e("res",signedUser.getId());

                signupStation(signedUser.getId());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });
    }


    //signing up station
    public void signupStation(String id){


        Station station = new Station(id,name,loc);

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<Station> call = webService.signupStation(station);

        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                signedStation = response.body();

                Log.e("res",signedStation.getStationOwnerId());

                Intent stationReg = new Intent(RegisterStation.this, OwnerHome.class);
                startActivity(stationReg);


            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }



        });



    }






}