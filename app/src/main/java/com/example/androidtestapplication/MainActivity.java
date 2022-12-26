package com.example.androidtestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.androidtestapplication.Adapter.RecyclerModelAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    AppCompatButton addproductbutton;
    ArrayList<ModelClass> arrdesign = new ArrayList<>();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView closenav;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Inflater inflater;
    ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addproductbutton = findViewById(R.id.addproductbutton);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationdrawer);
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new GridLayoutManager(this, 2);


        // CloseNav Drawerlayout//


        // Add Product Button //
        addproductbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });

        //Navigation Bar Setup start //


        toolbar = findViewById(R.id.toolbar);
        // TO SET TOOLBAR
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        closenav = findViewById(R.id.closenavigation);
        //TO PERFORM OPEN CLOSE OPERATIONS
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        // Customized Icon

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.drawericon);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        // Navigation bar Set Up Done //


        // RecyclerView SetUp //


        arrdesign.add(new ModelClass(R.drawable.img1, "Imac 27 Inch 5k",
                "Applestore", "$999.99"));
        arrdesign.add(new ModelClass(R.drawable.img2, "Samsung z flip",
                "Samsung store", "$711.99"));
        arrdesign.add(new ModelClass(R.drawable.img3, "Flanell Uniqlo",
                "Uniqlo Store", "$86.00"));
//        arrdesign.add(new ModelClass(R.drawable.img5, "Eyeglasses Gucci",
//                "Gucci", "$211.00"));
//        arrdesign.add(new ModelClass(R.drawable.img1, "Imac 27 Inch 5k",
//                "Applestore", "$999.99"));
//        arrdesign.add(new ModelClass(R.drawable.img5, "Samsung z flip",
//                "Samsung store", "$711.99"));
//        arrdesign.add(new ModelClass(R.drawable.img3, "Flanell Uniqlo",
//                "Uniqlo Store", "$86.00"));
//        arrdesign.add(new ModelClass(R.drawable.img4, "Eyeglasses Gucci",
//                "Gucci", "$211.00"));


        RecyclerModelAdapter adapter = new RecyclerModelAdapter(this, arrdesign);
        recyclerView.setAdapter(adapter);


    }
}
