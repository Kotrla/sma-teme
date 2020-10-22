package com.example.lab_work_s3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private Button stergeBut;
    private Button addBut;
    private EditText edTxt;



    private RecyclerView Rv;
    private Adapter adapter;
    private List<Model> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Rv=findViewById(R.id.rv_test_list);

        initializeButtons();
        initialize();
        onClickers();
        setRecyclerView();
    }

    private void initialize(){
        modelList = new ArrayList<>();
        modelList.add(new Model("koti", "kotrla"));
        modelList.add(new Model("viger", "keta"));
        modelList.add(new Model("dragana", "mirkovic"));
        modelList.add(new Model("ceca", "raznatovic"));
        modelList.add(new Model("mile", "kitic"));
        modelList.add(new Model("anita", "milanovic"));
        modelList.add(new Model("adi", "morovan"));
        modelList.add(new Model("zac", "perna"));
    }

    private void onClickers(){

        stergeBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txt = edTxt.getText().toString();;
                sterge(txt);
                edTxt.setText("");
                setRecyclerView();
            }
        });

        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = edTxt.getText().toString();
                adauga(txt);
                edTxt.setText("");
                setRecyclerView();
            }
        });
    }

    private void setRecyclerView(){
        adapter = new Adapter(modelList);
        Rv.setLayoutManager(new LinearLayoutManager(this));
        Rv.setAdapter(adapter);
    }


    private void sterge(String s){

        int k = -1;
        String mate="";
        for(int i = 0; i < modelList.size(); i++){
            if(modelList.get(i).getName().equals(s)){
                k = i;
            }
        }

        if (k >= 0){
            modelList.remove(k);
        }
    }

    private void adauga(String s){
        modelList.add(new Model(s, " "));
    }

    private void initializeButtons(){


        stergeBut =findViewById(R.id.stergeButton);
        addBut = findViewById(R.id.adaugaButton);
        edTxt = findViewById(R.id.eText);
    }

}