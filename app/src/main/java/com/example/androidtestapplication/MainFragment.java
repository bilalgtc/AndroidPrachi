package com.example.androidtestapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidtestapplication.Adapter.RecyclerModelAdapter;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class MainFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ImageView delete, update;
    Adapter Myadapter;

     ArrayList<ModelClass> arrdesign = new ArrayList<>();

    // Inflater inflater;
    // ViewGroup container;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentManager fm = getFragmentManager();
        //   FragmentManager fm = getFragmentManager();
        //   FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTransaction ft = fm.beginTransaction();
        ft.commit();
        ft.addToBackStack(null);

        View view = inflater.inflate(R.layout.fragment_main, container, false);


        recyclerView = view.findViewById(R.id.recyclerview);
        layoutManager = new GridLayoutManager(getContext(), 2);
       // FirebaseRecyclerOptions<ModelClass> options = new FirebaseRecyclerOptions.Builder<ModelClass>().setQuery
         //       (FirebaseDatabase.getInstance().getReference("Products"),ModelClass.class).build();
        RecyclerModelAdapter adapter = new RecyclerModelAdapter(getContext(), arrdesign);
        recyclerView.setAdapter(adapter);
        //setupOnBackPressed();
        return view;



    }

    @Override
    public void onResume() {

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getFragmentManager().popBackStack();
             //   getActivity().finishAffinity();
            }
        };
        //  getActivity().finish();
        super.onResume();

    }

    // ((MainFragment) getActivity()).finish();


}







