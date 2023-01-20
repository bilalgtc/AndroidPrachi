package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText ed1, ed2, ed3, ed4, ed5;
    AppCompatButton signupbutton;
    TextView signintext;
    ImageView valid1, valid2, valid3, eye, eye2;
    SharedPreferences sp;
    private static final String SPNAME = "mypref";
    private static final String KEYNAME = "email";
    private static final String KEYPASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        init();
        validation();
        eye.setOnClickListener(this);
        eye2.setOnClickListener(this);
        signupbutton.setOnClickListener(this);
        signintext.setOnClickListener(this);

    }

    public void init() {
        ed1 = findViewById(R.id.edittxt1signup);
        ed2 = findViewById(R.id.edittxt2signup);
        ed3 = findViewById(R.id.edittxt3signup);
        ed4 = findViewById(R.id.edittxt4signup);
        ed5 = findViewById(R.id.edittxt5signup);
        valid1 = findViewById(R.id.imageview1);
        valid2 = findViewById(R.id.imageview2);
        valid3 = findViewById(R.id.imageview3);
        eye = findViewById(R.id.imageview4);
        eye2 = findViewById(R.id.imageview5);
        signupbutton = findViewById(R.id.signupbutton);
        signintext = findViewById(R.id.signinText);
        eye.setImageResource(R.drawable.eye);
        eye2.setImageResource(R.drawable.eye);

        // Share Preference
        sp = getSharedPreferences(SPNAME, MODE_PRIVATE);
        // when open activity first check shared preferance data available  or not
        String email = sp.getString(KEYNAME, null);
        String password = sp.getString(KEYPASSWORD, null);

        if (email != null && password != null) {
            // If data is available then directly call on Mainactivity
            Intent inext = new Intent(Signup.this, MainActivity.class);
            startActivity(inext);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signinText: {
                Intent inext = new Intent(Signup.this, Signin.class);
                startActivity(inext);
                break;
            }
            case R.id.signupbutton: {

                break;
            }
            case R.id.imageview4: {
                if (ed4.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    // If Password is visible then hide //
                    ed4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eye.setImageResource(R.drawable.eye);
                } else {
                    ed4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye.setImageResource(R.drawable.invisibleeye);
                }
                break;
            }
            case R.id.imageview5: {
                if (ed5.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    // If Password is visible then hide //
                    ed5.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eye2.setImageResource(R.drawable.eye);
                } else {
                    ed5.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye2.setImageResource(R.drawable.invisibleeye);
                }
                break;
            }
        }
    }

    public void validation() {
        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("hh", "" + charSequence.toString());
                String name = ed1.getText().toString();
                if (!name.isEmpty()) {
                    valid1.setImageResource(R.drawable.success);
                    valid1.setVisibility(View.VISIBLE);

                } else {
                    valid1.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("hh", "" + charSequence.toString());
                String email = ed2.getText().toString();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    valid2.setImageResource(R.drawable.success);
                    valid2.setVisibility(View.VISIBLE);
                } else {

                    valid2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String phonenumber = ed3.getText().toString();
                if (phonenumber.length() == 10) {
                    valid3.setImageResource(R.drawable.success);
                    valid3.setVisibility(View.VISIBLE);
                } else {
                    valid3.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}