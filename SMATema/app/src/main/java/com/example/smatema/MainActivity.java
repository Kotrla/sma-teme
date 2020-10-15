package com.example.smatema;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private String inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        setOnclickListeners();
    }


    public void setOnclickListeners(){

                startButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        final AlertDialog.Builder newDialog = new AlertDialog.Builder(MainActivity.this);
                        newDialog.setTitle("Temaa");
                        newDialog.setMessage("Introdu ceva string");


                        final EditText textBox = new EditText(MainActivity.this);

                        newDialog.setView(textBox);


                        newDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                inputText = textBox.getText().toString();

                                if(inputText.matches("")){
                                    Toast.makeText(MainActivity.this, "Introdu ceva mai, nu e voie gol", Toast.LENGTH_LONG).show();

                                }else {
                                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                                    intent.putExtra("message", inputText);
                                    startActivity(intent);
                                }
                            }
                        });


                        newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Ati apasat butonul cancel", Toast.LENGTH_LONG).show();
                            }
                        });

                        newDialog.show();
                    }
        });
    }

    public void initialize() {
        startButton = findViewById(R.id.start_button);
    }
}


