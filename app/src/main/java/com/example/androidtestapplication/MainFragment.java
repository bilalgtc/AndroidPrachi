package com.example.androidtestapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidtestapplication.Adapter.RecyclerModelAdapter;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class MainFragment extends Fragment {

    ArrayList<ModelClass> arrdesign = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    // Inflater inflater;
    // ViewGroup container;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        layoutManager = new GridLayoutManager(getContext(), 2);


        arrdesign.add(new ModelClass(R.drawable.img1, "Imac 27 Inch 5k",
                "Applestore", "$999.99"));
        arrdesign.add(new ModelClass(R.drawable.img2, "Samsung z flip",
                "Samsung store", "$711.99"));
        arrdesign.add(new ModelClass(R.drawable.img3, "Flanell Uniqlo",
                "Uniqlo Store", "$86.00"));
//      arrdesign.add(new ModelClass(R.drawable.img5, "Eyeglasses Gucci",
//                "Gucci", "$211.00"));
//        arrdesign.add(new ModelClass(R.drawable.img1, "Imac 27 Inch 5k",
//                "Applestore", "$999.99"));
//        arrdesign.add(new ModelClass(R.drawable.img5, "Samsung z flip",
//                "Samsung store", "$711.99"));
//        arrdesign.add(new ModelClass(R.drawable.img3, "Flanell Uniqlo",
//                "Uniqlo Store", "$86.00"));
//        arrdesign.add(new ModelClass(R.drawable.img4, "Eyeglasses Gucci",
//                "Gucci", "$211.00"));

        RecyclerModelAdapter adapter = new RecyclerModelAdapter(getContext(), arrdesign);
        recyclerView.setAdapter(adapter);


        return view;
    }
}