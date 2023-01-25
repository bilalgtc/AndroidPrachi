package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {

    SharedPreferences sp;
    private  static final  String SPNAME = "mypref";
    private static final String  KEYNAME = "email";
    private static final String  KEYPASSWORD = "password";
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user != null) {
                    Intent i = new Intent(Splash.this,
                            MainActivity.class);
                    startActivity(i);
                  finish();

                }else{
                    Intent intent = new Intent(Splash.this, Introduction.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1000);

    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        }
 //   }
}