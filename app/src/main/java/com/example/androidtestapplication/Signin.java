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

public class Signin extends AppCompatActivity {

    TextView signuptext;
    ImageView valid, eye, checkbox1, tick;
    EditText et1, et2;
    DBHelper db;
    int a = 0;
    AppCompatButton signinbutton;
    private  static final  String SPNAME = "mypref";
    private static final String  KEYNAME = "email";
    private static final String  KEYPASSWORD = "password";
     SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        et1 = findViewById(R.id.et1signin);
        et2 = findViewById(R.id.et2signin);
        valid = findViewById(R.id.validimg);
        eye = findViewById(R.id.showpassword);
        signuptext = findViewById(R.id.signuptext);
        signinbutton = findViewById(R.id.signinbutton);
        checkbox1 = findViewById(R.id.checkbox);


        // Share Preference
        sp = getSharedPreferences(SPNAME, MODE_PRIVATE );
        // when open activity first check shared preferance data available  or not
        String email = sp.getString(KEYNAME, null);
        String password = sp.getString(KEYPASSWORD, null);

       if(email != null && password != null){
           // If data is available then directly call on Mainactivity
           Intent inext = new Intent(Signin.this, MainActivity.class);
           startActivity(inext);
       }



        //Password Visibility //
        eye.setImageResource(R.drawable.eye);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et2.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    // If Password is visible then hide //
                    et2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eye.setImageResource(R.drawable.eye);
                } else {
                    et2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye.setImageResource(R.drawable.invisibleeye);
                }
            }
        });
// Password Visibility SetUp//


        // EditText String Validation //

        String name = et2.getText().toString();


        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inext = new Intent(Signin.this, Signup.class);
                startActivity(inext);
                finish();


            }
        });

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Log.e("hh", "" + charSequence.toString());
                String emailinput = et1.getText().toString();

                if (!emailinput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailinput).matches()) {
                    valid.setImageResource(R.drawable.success);
                    valid.setVisibility(View.VISIBLE);
                } else {
                    valid.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = et2.getText().toString();
                if (password.length() < 8) {
                    Toast.makeText(Signin.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                } else {

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        // EditText String Validation SetUp //


        // Authentication Part //
        db = new DBHelper(this);
        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et1.getText().toString();
                String password = et2.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Signin.this, "Please enter Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = db.checkuser(email, password);
                    if (check == true) {

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(KEYNAME,et1.getText().toString());
                        editor.putString(KEYPASSWORD, et2.getText().toString());
                        editor.commit();
                        Intent inext = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(inext);
                        Toast.makeText(Signin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Signin.this, "Please enter valid Credentials ", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });
// Authenticate //

        //CHECKBOX SETUP //
        checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (a == 0) {
                    checkbox1.setImageResource(R.drawable.checkbox);
                    a++;
                } else {
                    checkbox1.setImageResource(R.drawable.tick);
                    a--;
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Signin.this, Landing.class);
        startActivity(intent);
        finish();
    }
}








