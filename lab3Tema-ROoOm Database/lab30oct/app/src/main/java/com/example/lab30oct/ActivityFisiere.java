package com.example.lab30oct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActivityFisiere extends AppCompatActivity {

    EditText editTextAfWrite, editTextAfRead;
    Button buttonAfWrite, buttonAfRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisiere);
        editTextAfWrite = findViewById(R.id.editTextAfWrite);
        editTextAfRead = findViewById(R.id.editTextAfRead);
        buttonAfWrite = findViewById(R.id.buttonAfWrite);
        buttonAfRead = findViewById(R.id.buttonAfRead);

        buttonAfWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strToWrite = editTextAfWrite.getText().toString();
                writeToFile(strToWrite, ActivityFisiere.this);


            }
        });

        buttonAfRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String read = readFromFile(ActivityFisiere.this);
                editTextAfRead.setText(read);
            }
        });


    }


    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

}