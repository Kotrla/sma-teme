package com.example.app20112020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_1_post_login extends AppCompatActivity {

    private EditText edPostName, edPostAnimal;
    private Button BtnPostGo, BtnPostImage;
    private ListView postListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_1_post_login);
        edPostName = findViewById(R.id.edPostName);
        edPostAnimal = findViewById(R.id.edPostAnimal);
        BtnPostGo = findViewById(R.id.BtnPostGo);
        BtnPostImage = findViewById(R.id.BtnPostImage);
        postListView = findViewById(R.id.postListView);

        BtnPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_1_post_login.this,MainActivity2.class);
                startActivity(intent);
            }
        });


        BtnPostGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edPostName.getText().toString();
                String animal = edPostAnimal.getText().toString();

                edPostName.setText("");
                edPostAnimal.setText("");

                HashMap<String , Object> map = new HashMap<>();
                map.put("Name",name);
                map.put("Animal",animal);

                FirebaseDatabase.getInstance().getReference().child("animale").push().setValue(map);


            }
        });


        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        postListView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("animale");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    list.add(snapshot.getValue().toString());

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}