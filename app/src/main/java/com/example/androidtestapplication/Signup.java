package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
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

public class Signup extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4, ed5;
    AppCompatButton signupbutton;
    DBHelper database;
    TextView signintext;
    ImageView valid1, valid2, valid3, eye, eye2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
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


        // Next Activity Inent Passing for Previous Activity //
        signintext = findViewById(R.id.signinText);


        // Previous Activity Intent //
        signintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inext = new Intent(Signup.this, Signin.class);
                startActivity(inext);

            }
        });
        //Password Visibility //

        eye.setImageResource(R.drawable.eye);
        eye2.setImageResource(R.drawable.eye);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed4.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // If Password is visible then hide //
                    ed4.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eye.setImageResource(R.drawable.eye);
                }else{
                    ed4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye.setImageResource(R.drawable.invisibleeye);
                }
            }
        });
        eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed5.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // If Password is visible then hide //
                    ed5.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eye2.setImageResource(R.drawable.eye);
                }else{
                    ed5.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye2.setImageResource(R.drawable.invisibleeye);
                }
            }
        });

// Password Visibility SetUp//


// View Validation //


        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("hh",""+charSequence.toString());
                String name = ed1.getText().toString();
                if(!name.isEmpty() ){
                    valid1.setImageResource(R.drawable.success);
                    valid1.setVisibility(View.VISIBLE);

                } else{
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
                Log.e("hh",""+charSequence.toString());
                String email = ed2.getText().toString();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    valid2.setImageResource(R.drawable.success);
                    valid2.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
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
                if(phonenumber.length() >9){
                    valid3.setImageResource(R.drawable.success);
                    valid3.setVisibility(View.VISIBLE);
                } else{
                    valid3.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


// View Validation SetUp //






        // SQLite Sign Up part start //
             database = new DBHelper(this);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed1.getText().toString();
                String email = ed2.getText().toString();
                String phonenumber = ed3.getText().toString();
                String password = ed4.getText().toString();
                String confirmpassword = ed5.getText().toString();

                if(name.isEmpty() || email.isEmpty() || phonenumber.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()){
                    Toast.makeText(Signup.this, "Please fill all the mentioned fields", Toast.LENGTH_SHORT).show();

                }else {
                    if (password.equals(confirmpassword)) {

                       boolean uservalidation =   database.uservalidation(email);
                       if(uservalidation == false){
                                 Boolean registraion = database.registeruser( name, email, phonenumber, password);
                                 if(registraion == true){
                                     Toast.makeText(Signup.this, "Registraion Successful", Toast.LENGTH_SHORT).show();
                                     Intent inext = new Intent(Signup.this, Signin.class);
                                       startActivity(inext);
                                 }
                                 else
                                 {
                                     Toast.makeText(Signup.this, "Registration Failed \n Try again", Toast.LENGTH_SHORT).show();
                                 }
                       }else{
                           Toast.makeText(Signup.this, "User already exists \n Please Sign in", Toast.LENGTH_SHORT).show();
                       }

                    } else {
                        Toast.makeText(Signup.this, "Password don't matches", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // SQLite Sign Up part  end//



    }

}