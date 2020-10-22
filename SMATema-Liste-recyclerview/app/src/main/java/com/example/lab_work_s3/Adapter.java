package com.example.lab_work_s3;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.lab_work_s3.ViewHolder.*;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Model> modelList;




    public Adapter(List<Model> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_example, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model newModel = modelList.get(position);
        holder.setValues(newModel.getName(), newModel.getFirstName());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
