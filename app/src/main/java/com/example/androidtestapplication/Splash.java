package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    SharedPreferences sp;
    private  static final  String SPNAME = "mypref";
    private static final String  KEYNAME = "email";
    private static final String  KEYPASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sp = getSharedPreferences(SPNAME, MODE_PRIVATE);
                if(sp.contains(KEYNAME)){
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(Splash.this, Introduction.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1000);

    }
}