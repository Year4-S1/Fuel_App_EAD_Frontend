package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ead_assignment.model.LoginModel;
import com.example.ead_assignment.model.Station;
import com.example.ead_assignment.utils.WebServiceClient;
import com.example.ead_assignment.utils.WebServiceInterface;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
//login screen activity

    //creating variables
    private TextView signupTxt;
    private TextView loginBtn;
    private EditText phnNum, password;
    private RequestQueue queue ;
    private JsonObjectRequest request;
    String loginUrl = "http://10.0.2.2:5000/api/user/login";
    private boolean status;
    private  User user =  new User();
    private  User signedUser =  new User();
    private Station station;

    public static  final String SHARED_PREF = "sharedPrefs";
    public static final String UID = "uid";



    //oncreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        signupTxt = findViewById(R.id.signuptxt);
        loginBtn = findViewById(R.id.loginBtn);
        phnNum = findViewById(R.id.phonenumLogin);
        password = findViewById(R.id.passwordLogin);
        queue = Volley.newRequestQueue(this);


        //Onclick listener to switch to th signup page
        signupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSignup();
            }
        });

//        http://10.0.2.2/8612/api/station/getall

        //Onclick listener for login button click
        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            loginValidations ();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }

    //opening signup when signup txt is clicked
    public void openSignup (){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    //checking if login firlds are filled before loging in
    public void loginValidations () throws JSONException {

        String phnNumStr = phnNum.getText().toString();
        String pwdStr = password.getText().toString();

        if (phnNumStr.matches("") || pwdStr.matches("")) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!phnNumStr.matches("") && !pwdStr.matches("")){

            //if all fields are filled process login
            LoginModel loginModel = new LoginModel(phnNumStr,pwdStr);

            WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
            Call<User> call = webService.login(loginModel);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.d("TAG", "onResponse: " + response.code());
                    assert response.body() != null : "Response Body Empty";
                    signedUser = response.body();

                    Log.e("ddd",signedUser.getId());

                    String uid = signedUser.getId();


                    SharedPreferences prefs = getSharedPreferences("FUELQ", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("uid", uid);
                    editor.apply();


                    //if user is Owner, redirect to owner dashboard, else redirect to customer hom
                    if(signedUser.getUserType().matches("Owner")){
                        getStation();

                    }
                    else{
                        Intent stationReg = new Intent(Login.this, Home.class);
                        startActivity(stationReg);
                    }



                    Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

                }


            });




        }



    }

    public void getStation (){

        SharedPreferences prefs = getSharedPreferences("FUELQ", Context.MODE_PRIVATE);

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<Station> call = webService.getStation(prefs.getString("uid",""));

        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                station = response.body();

                String stationid = station.getId();
                String stationName = station.getStationName();
                String stationLocation = station.getStationLocation();


                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("stationId", stationid);
                editor.putString("stationName", stationName);
                editor.putString("stationLocation", stationLocation);
                editor.apply();

                System.out.println(prefs.getString("stationName",""));

                Intent stationReg = new Intent(Login.this, OwnerHome.class);
                startActivity(stationReg);

                Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });

    }

}