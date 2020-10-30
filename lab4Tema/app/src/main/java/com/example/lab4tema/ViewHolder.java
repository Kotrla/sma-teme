package com.example.lab4tema;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView nameTv;
    private TextView firstnameTv;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        initializeViews();
    }

    private void initializeViews(){
        nameTv = itemView.findViewById(R.id.tv_row_name);
        firstnameTv = itemView.findViewById(R.id.tv_row_firstName);
    }

    public void setValues(String name, String firstName){
        nameTv.setText(name);
        firstnameTv.setText(firstName);
    }
}