package com.example.lab4tema;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.lab4tema.MainActivity.arr;


public class Fragment3 extends Fragment {

    private RecyclerView Rv;
    private Adapter2 adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        Rv=view.findViewById(R.id.rv_test_list);
        adapter = new Adapter2(arr);
        Rv.setLayoutManager(new LinearLayoutManager(getContext()));
        Rv.setAdapter(adapter);

        return view;
    }
}