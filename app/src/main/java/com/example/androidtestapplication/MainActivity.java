package com.example.androidtestapplication;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidtestapplication.Adapter.RecyclerModelAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton addproductbutton;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView closenav;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addproductbutton.setOnClickListener(this);
        fragmentmanager();
        navigation();
        navigationclose();


    }


    public void init() {
        addproductbutton = findViewById(R.id.addproductbutton);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationdrawer);
        closenav = findViewById(R.id.closenavigation);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.drawericon);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addproductbutton: {
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    public void fragmentmanager() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.linearframelayout, new MainFragment()).addToBackStack(null).commit();
    }

    public void navigation() {


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);

                int id = item.getItemId();
                if (id == R.id.logout) {

                 //   sp = getSharedPreferences(SPNAME, MODE_PRIVATE);
                  //  SharedPreferences.Editor editor = sp.edit();
                  //  editor.clear();
                  //  editor.commit();
                    FirebaseAuth.getInstance().signOut();
                   // startActivity(new Intent(MainActivity.this, Signin.class));
                    Toast.makeText(MainActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                   // finish();
                  //  Intent intent = new Intent(MainActivity.this, Signin.class);
                  //  startActivity(intent);
                }
                return true;
            }
        });

    }

    public  void navigationclose(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer);
        View header = navigationView.getHeaderView(0);
        ImageView closenav = header.findViewById(R.id.closenavigation);
        closenav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
        super.onBackPressed();
        finish();

    }


}
