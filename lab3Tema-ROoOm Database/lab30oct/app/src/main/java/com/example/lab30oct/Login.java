package com.example.lab30oct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText userId, password;
    Button login;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        preferences=this.getSharedPreferences("login",this.MODE_PRIVATE);
        String uid=preferences.getString("uid", "");
        String pass=preferences.getString("pass", "");

        userId.setText(uid);
        password.setText(pass);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String userIdText = userId.getText().toString();
                final String passwordText = password.getText().toString();

                if(userIdText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty fields detected", Toast.LENGTH_SHORT).show();

                }else{
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(userIdText, passwordText);
                            if(userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Username or password not correct", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }else {
                                SharedPreferences.Editor ed=preferences.edit();

                                ed.putString("uid",userIdText);
                                ed.putString("pass",passwordText);

                                ed.commit();

                                String name = userEntity.name;
                                startActivity(new Intent(
                                        Login.this, HomeScreen.class)
                                        .putExtra("name",name)
                                );

                            }
                    }


                    }).start();

                }

            }


        });
    }
}