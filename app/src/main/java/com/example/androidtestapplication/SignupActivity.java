package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    TextView signintext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signintext = findViewById(R.id.signinText);

        signintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inext = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(inext);

            }
        });

    }
}