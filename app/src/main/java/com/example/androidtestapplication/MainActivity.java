package com.example.androidtestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
      DrawerLayout drawerLayout;
      NavigationView navigationView;
      Toolbar toolbar;
      ImageView closeimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationdrawer);
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


    }



}