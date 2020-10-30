package com.example.lab4tema;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.lab4tema.MainActivity.arr;
import static com.example.lab4tema.MainActivity.index;

public class Fragment1 extends Fragment {

    private TextView userT, pwT, emT, agT;
    private EditText userE, pwE, emE, agE;
    private Button changeB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        userT = view.findViewById(R.id.textView5);
        pwT=view.findViewById(R.id.textView6);
        emT=view.findViewById(R.id.textView7);
        agT=view.findViewById(R.id.textView8);
        userE=view.findViewById(R.id.editUs);
       pwE=view.findViewById(R.id.editPw);
       emE=view.findViewById(R.id.editEm);
       agE=view.findViewById(R.id.editAge);

       changeB=view.findViewById(R.id.changeButton);

       piedyLegendy(index);
        oncliker();
        return view;
    }




    public void oncliker(){
        changeB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int in2 = new Integer(agE.getText().toString());
                setter(userE.getText().toString(),pwE.getText().toString(),emE.getText().toString(),in2,index);
                userE.setText("");
                pwE.setText("");
                emE.setText("");
                agE.setText("");
                piedyLegendy(index);
            }
        });
    }



    public void piedyLegendy(int i){
        userT.setText(arr.get(i).getUsername());
        pwT.setText(arr.get(i).getPassword());
        emT.setText(arr.get(i).getEmail());

        int a = arr.get(i).getAge();
        agT.setText(String.valueOf(a));
    }



    public void setter(String us, String pw, String em, int a, int index){

        Korisnik n = new Korisnik(us,pw,em,a);
        arr.set(index, n);

    }


}