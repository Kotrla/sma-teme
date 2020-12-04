package com.example.app20112020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.*;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    EditText edEmReg, edPwReg, edNameReg;
    Button btnRegReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);






        edEmReg = findViewById(R.id.edEmReg);
        edPwReg = findViewById(R.id.edPwReg);
        edNameReg = findViewById(R.id.edNameReg);
        btnRegReg = findViewById(R.id.BtnRegReg);


        btnRegReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTheUser();
            }
        });
    }



    private void registerTheUser(){
        final String name = edNameReg.getText().toString().trim();
        final String email = edEmReg.getText().toString().trim();
        final  String password = edPwReg.getText().toString();

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();

                            //aici adaug un nume noului utilizator inregistrat
                            //codul e optional
                            FirebaseUser newUser = firebaseAuth.getCurrentUser();
                            UserProfileChangeRequest changeRequest =
                                    new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name).build();
                            newUser.updateProfile(changeRequest)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                rootNode = FirebaseDatabase.getInstance();
                                                reference = rootNode.getReference("users");
                                                UserHelperClass helperClass= new UserHelperClass(email, name, password);


                                                reference.child(name).setValue(helperClass);

                                                launchMainActivity();
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void launchMainActivity(){
        Intent intent =  new Intent(RegisterActivity.this, MainActivity.class);

        startActivity(intent);

    }
}