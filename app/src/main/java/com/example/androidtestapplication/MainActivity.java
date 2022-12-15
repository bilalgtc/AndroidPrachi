package com.example.androidtestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AppCompatButton addproductbutton;
    ArrayList<ModelClass> arrdesign = new ArrayList<>();
      DrawerLayout drawerLayout;
      NavigationView navigationView;
      Toolbar toolbar;
      ImageView closeimg;
      RecyclerView recyclerView;
      RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addproductbutton = findViewById(R.id.addproductbutton);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationdrawer);
     recyclerView = findViewById(R.id.recyclerview);
     layoutManager = new GridLayoutManager(this,2);


     // Add Product Button //
        addproductbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
        //Intext passing in Activity //
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(MainActivity.this, ProductDetailActivity.class);
                startActivity(inext);
            }
        });

                //Navigation Bar Setup start //

        //NavigationView navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        //navigationView.setItemIconTintList();
        toolbar = findViewById(R.id.toolbar);
        // TO SET TOOLBAR
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        closeimg = findViewById(R.id.closenavigation);
        //TO PERFORM OPEN CLOSE OPERATIONS
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,   R.string.open_drawer, R.string.close_drawer);

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

                             return  true;
                    }
                });

//    public void onclose(Boolean ){
//            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//                closeimg.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                    }
//                });
//            } else{
//                super.;
//            }
//            return true;
//        }
        // Navigation bar Set Up Done //


        // RecyclerView SetUp //


        arrdesign.add(new ModelClass(R.drawable.img1, "Imac 27 Inch 5k",
                "Applestore", "$999.99", R.drawable.pencil, R.drawable.dustbin));
        arrdesign.add(new ModelClass(R.drawable.img2, "Samsung z flip",
                "Samsung store", "$711.99", R.drawable.pencil, R.drawable.dustbin));
        arrdesign.add(new ModelClass(R.drawable.img3, "Flanell Uniqlo",
                "Uniqlo Store", "$86.00", R.drawable.pencil, R.drawable.dustbin));
        arrdesign.add(new ModelClass(R.drawable.img4, "Eyeglasses Gucci",
                "Gucci", "$211.00", R.drawable.pencil, R.drawable.dustbin));


        RecyclerModelAdapter adapter = new RecyclerModelAdapter(this, arrdesign);
        recyclerView.setAdapter(adapter);


    }




}