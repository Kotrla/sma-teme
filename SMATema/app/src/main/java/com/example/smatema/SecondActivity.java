package com.example.smatema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initialize();
        getValue();

    }

    public void initialize(){
        text = (TextView)findViewById(R.id.text);
    }

    public void getValue(){
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        text.setText(message);
    }
}