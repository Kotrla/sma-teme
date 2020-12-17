package com.example.lab30oct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {


    Button FISIERE;
    TextView Tname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Tname=findViewById(R.id.name);
        FISIERE=findViewById(R.id.fisiereButtonHomePage);



        String name = getIntent().getStringExtra("name");
        Tname.setText(name);


        FISIERE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, ActivityFisiere.class));


            }
        });
    }
}