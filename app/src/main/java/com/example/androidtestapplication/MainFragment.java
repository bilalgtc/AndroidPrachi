package com.example.androidtestapplication;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidtestapplication.Adapter.RecyclerModelAdapter;
import com.example.androidtestapplication.Database.CRUD_DATA;
import com.example.androidtestapplication.Database.DataBaseHelper;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class MainFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ImageView delete, update;
   CRUD_DATA data;

    // Inflater inflater;
    // ViewGroup container;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        if (getFragmentManager().getBackStackEntryCount() > 1) {
//            getFragmentManager().popBackStack();
//        } else {
//         //   finish();
//        }

        FragmentManager fm = getFragmentManager();
     //   FragmentManager fm = getFragmentManager();
     //   FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTransaction ft = fm.beginTransaction();
        ft.commit();
        ft.addToBackStack(null);

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        data = new CRUD_DATA(getContext());
        ArrayList<ModelClass> arrdesign =  data.FetchData();


        recyclerView = view.findViewById(R.id.recyclerview);
        layoutManager = new GridLayoutManager(getContext(), 2);


//        arrdesign.add(new ModelClass(R.drawable.img1, "Imac 27 Inch 5k",
//                "Applestore", "$999.99"));
//        arrdesign.add(new ModelClass(R.drawable.img2, "Samsung z flip",
//                "Samsung store", "$711.99"));
//        arrdesign.add(new ModelClass(R.drawable.img3, "Flanell Uniqlo",
//                "Uniqlo Store", "$86.00"));
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
        //setupOnBackPressed();

        return view;
    }




}
//    MainFragment firstFragment = new MainFragment();
//    getSupportFragmentManager().beginTransaction()
//.replace(R.id.article_fragment, firstFragment)
//.addToBackStack(null).commit();
//
////    private void setupOnBackPressed() {
//        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                if(isEnabled()){
//                    Toast.makeText(requireContext(), "Back", Toast.LENGTH_SHORT).show();
//                    setEnabled(false);
//                    requireActivity().onBackPressed();
//                }
//            }
//        });




