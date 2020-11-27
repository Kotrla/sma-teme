package com.example.lab30oct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {



    TextView Tname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Tname=findViewById(R.id.name);
        String name = getIntent().getStringExtra("name");
        Tname.setText(name);
    }
}