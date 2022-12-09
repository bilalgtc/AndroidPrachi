package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class SplashActivity2 extends AppCompatActivity {

    ViewPager viewpager;
    DotsIndicator dot;
    ArrayList<Integer> arrayList = new ArrayList<>();
    AppCompatButton nextbutton;
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        viewpager = findViewById(R.id.viewpager);
            nextbutton = findViewById(R.id.nextbutton);
            dot = findViewById(R.id.dotindicator);
            skip = findViewById(R.id.skipped);

        arrayList.add(R.drawable.group1);
        arrayList.add(R.drawable.group2);
        arrayList.add(R.drawable.group3);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(SplashActivity2.this, WelcomeActivity.class);
                startActivity(inext);
            }
        });





        MyAdapter myAdapter
                = new MyAdapter(SplashActivity2.this, arrayList);
        viewpager.setAdapter(myAdapter);
            dot.attachTo(viewpager);
//        nextbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MyAdapter myAdapter1 = new MyAdapter(SplashActivity2.this, arrayList);
//             //   nextbutton.setAdapter(myAdapter1);
//            }
//        });




        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(SplashActivity2.this,WelcomeActivity.class);
                startActivity(inext);

            }
        });



    }
}