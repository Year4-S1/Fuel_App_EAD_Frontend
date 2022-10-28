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

import com.example.ead_assignment.model.LoginModel;
import com.example.ead_assignment.utils.WebServiceClient;
import com.example.ead_assignment.utils.WebServiceInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Logout extends AppCompatActivity {

    private TextView logoutbtn;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);

        logoutbtn = findViewById(R.id.logoutbtn);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 logout();
            }
        });


    }

    public void logout(){

        SharedPreferences sharedPreferences =  getSharedPreferences("FUELQ", Context.MODE_PRIVATE);

        String id = sharedPreferences.getString("uid","" );

        System.out.println("Clickeeeeeeeeeeeeed "+ id);

        WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
        Call<User> call = webService.logout(id);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                user = response.body();

                if(response.code() == 200 ){
                    Intent logoutpage = new Intent(Logout.this, Login.class);
                    startActivity(logoutpage);
                }
                else{
                    
                }

                System.out.println(user.isLoginStatus());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });



    }

}