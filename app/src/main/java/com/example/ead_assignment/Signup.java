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

import com.example.ead_assignment.model.FuelStation;
import com.example.ead_assignment.utils.WebServiceClient;
import com.example.ead_assignment.utils.WebServiceInterface;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//signing up user
public class Signup extends AppCompatActivity {

    //creating
    private TextView loginTxt;
    private TextView signupBtn;
    private EditText phnNum, password, fullName;
    RadioButton customer, owner;
    RadioGroup userType;
    String phnNumStr ;
    String pwdStr ;
    String name;
    User signedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        //getting element references by id
        loginTxt = findViewById(R.id.logintxt);
        signupBtn = findViewById(R.id.signupBtn);
        fullName = findViewById(R.id.fullName);
        phnNum = findViewById(R.id.phnNumSignup);
        password = findViewById(R.id.pwordSignup);
        userType = findViewById(R.id.users);
        customer = findViewById(R.id.customer);
        owner = findViewById(R.id.owner);



        //onclick for login txt
        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openLogin();
            }
        });


        //onclick listener for singup btn
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupValidation();
            }
        });

    }


    //opening logging activity
    public void openLogin (){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


    //checking if all fields are filled
    public void signupValidation(){

        phnNumStr = phnNum.getText().toString();
        pwdStr = password.getText().toString();
        name = fullName.getText().toString();

        if (phnNumStr.matches("") || pwdStr.matches("") || name.matches("") || userType.getCheckedRadioButtonId() == -1 ) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (!phnNumStr.matches("") || !pwdStr.matches("") || !name.matches("") || userType.getCheckedRadioButtonId() != -1 ) {
            String userValue = null;
            if (customer.isChecked()) {
                userValue = customer.getText().toString();
            } else if (owner.isChecked()) {
                userValue = owner.getText().toString();
                
            }

            signedUser =  new User(null,name,phnNumStr,pwdStr,userValue,false);
            signupUser(signedUser);

        }


    }


    //signup user. if user is customer, complete signup and redirect to home page.
    //if user is owner, ridirect to station signup page
    public void signupUser(User user){

        String userType = user.getUserType();

        Gson gson = new Gson();
        String value = gson.toJson(user);

        if(userType.matches("Owner") ){
            Intent stationReg = new Intent(Signup.this, RegisterStation.class);
            stationReg.putExtra("Owner", value);
            startActivity(stationReg);
        }
        else{

            WebServiceInterface webService = WebServiceClient.getInstance().getWebService();
            Call<User> call = webService.signupUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                signedUser = response.body();

                Log.e("res",response.toString());

                Intent stationReg = new Intent(Signup.this, Home.class);
                startActivity(stationReg);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());

            }


        });

        }


    }

}