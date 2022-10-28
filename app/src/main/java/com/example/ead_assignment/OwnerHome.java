package com.example.ead_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OwnerHome extends AppCompatActivity {

    private TextView petrol92Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_home);


        petrol92Btn = findViewById(R.id.petrol92btn);

        //onclick listener to fuel buttons
        petrol92Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}