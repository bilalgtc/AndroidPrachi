package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SigninActivity extends AppCompatActivity {

    TextView signuptext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signuptext = findViewById(R.id.signuptext);
        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inext = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(inext);

            }
        });

    }
}