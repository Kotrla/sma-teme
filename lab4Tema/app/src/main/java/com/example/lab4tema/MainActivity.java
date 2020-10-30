package com.example.lab4tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String username, password;
    private EditText editTextPW, editTextUS;
    private Button loginBtn;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    protected static List<Korisnik> arr = new ArrayList<Korisnik>();
    static int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        arrayInit();
        onClickers();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            editTextUS.setText(loginPreferences.getString("username", ""));
            saveLoginCheckBox.setChecked(true);
        }
    }

    public void onClickers() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (searchUser(editTextUS.getText().toString()) &&
                                                    searchPw(editTextPW.getText().toString())) {
                                                Toast.makeText(getApplicationContext(),
                                                        "Redirecting...", Toast.LENGTH_SHORT).show();

                                                ///autofill
                                                username = editTextUS.getText().toString();

                                                if (saveLoginCheckBox.isChecked()) {
                                                    loginPrefsEditor.putBoolean("saveLogin", true);
                                                    loginPrefsEditor.putString("username", username);
                                                    loginPrefsEditor.commit();
                                                } else {
                                                    loginPrefsEditor.clear();
                                                    loginPrefsEditor.commit();
                                                }

                                                change();///move to another activity

                                            } else {
                                                Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();


                                            }
                                        }
                                    }

        );
    }


    public void arrayInit(){
        arr.add(new Korisnik("admin","admin"));
        arr.add(new Korisnik("koti","koti123"));
        arr.add(new Korisnik("Milan","milivoje123","milivoje.kralj@gmail.com",20));
        arr.add(new Korisnik("Ceca","555333","majka.srpska@gmail.com",39));
    }


    public void change(){
        startActivity(new Intent(MainActivity.this, MainActivity2.class));
    }


    public boolean searchUser(String username ){
        boolean rez = false;

        for(int i = 0; i<arr.size(); i++){
            if(username.equals(arr.get(i).getUsername())){
                rez = true;
                index = i;
            }
        }

        return rez;
    }

    public boolean searchPw(String pw ){
        boolean rez = false;

        for(int i = 0; i<arr.size(); i++){
            if(pw.equals(arr.get(i).getPassword()))
                rez = true;
        }

        return rez;
    }

    public void initialize(){

        editTextUS= findViewById(R.id.editTextUS);
        editTextPW= findViewById(R.id.editTextPW);
        loginBtn= findViewById(R.id.loginButton);
        saveLoginCheckBox = findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
    }
}