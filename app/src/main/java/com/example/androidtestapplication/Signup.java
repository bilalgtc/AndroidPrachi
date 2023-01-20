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

import com.example.androidtestapplication.Database.DBHelper;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText ed1, ed2, ed3, ed4, ed5;
    AppCompatButton signupbutton;
    DBHelper database;
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
        database = new DBHelper(this);

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
                database = new DBHelper(this);
                String name = ed1.getText().toString();
                String email = ed2.getText().toString();
                String phonenumber = ed3.getText().toString();
                String password = ed4.getText().toString();
                String confirmpassword = ed5.getText().toString();

                if (name.isEmpty() || email.isEmpty() || phonenumber.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
                    Toast.makeText(Signup.this, "Please fill the above field's properly", Toast.LENGTH_SHORT).show();

                }
                if (phonenumber.length() != 10) {
                    Toast.makeText(Signup.this, "Enter 10 Digit phone number", Toast.LENGTH_SHORT).show();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Signup.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirmpassword) && phonenumber.length() == 10) {
                        boolean uservalidation = database.uservalidation(email);
                        if (uservalidation == false) {
                            Boolean registraion = database.registeruser(name, email, phonenumber, password);
                            //  Toast.makeText(Signup.this, "Please fill the above field's properly", Toast.LENGTH_SHORT).show();
                            if (registraion == true) {


                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString(KEYNAME, ed2.getText().toString());
                                editor.putString(KEYPASSWORD, ed4.getText().toString());
                                editor.apply();
                                Toast.makeText(Signup.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent inext = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(inext);
                                finish();
                            } else {
                                Toast.makeText(Signup.this, "Registration Failed \n Try again", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signup.this, "User already exists \n Please Sign in", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Signup.this, "Password don't matches", Toast.LENGTH_SHORT).show();
                    }
                }
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