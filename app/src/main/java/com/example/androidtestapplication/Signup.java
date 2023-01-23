package com.example.androidtestapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;


public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText ed1, ed2, ed3, ed4, ed5;
    AppCompatButton signupbutton;
    TextView signintext;
    ImageView valid1, valid2, valid3, eye, eye2;
    SharedPreferences sp;
    String fullname, email, Phone, password, confirmpassword, UserId;
    private static final String SPNAME = "mypref";
    private static final String KEYNAME = "email";
    private static final String KEYPASSWORD = "password";
    private FirebaseAuth mAuth;
    FirebaseFirestore db;


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
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Firebase Firestore
         db = FirebaseFirestore.getInstance();

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
                fullname = ed1.getText().toString().trim();
                email = ed2.getText().toString().trim();
                Phone = ed3.getText().toString().trim();
                password = ed4.getText().toString().trim();
                confirmpassword = ed5.getText().toString().trim();
                if (!TextUtils.isEmpty(fullname)) {
                    if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        if (!TextUtils.isEmpty(Phone) && Phone.length() == 10) {
                            if (!TextUtils.isEmpty(password)) {
                                if (!TextUtils.isEmpty(confirmpassword)) {
                                    if (password.equals(confirmpassword)) {
                                        SignupUser();
                                        // calling method to add data to Firebase Firestore.

                                    } else {
                                        Toast.makeText(this, "Password dosen't matches", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(this, "Confirm password can't be empty", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this, "Please enter password \n Minimum 6 character's required", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "Please enter 10 digit phone number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
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


    private void SignupUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Signup.this, "Signin Successful", Toast.LENGTH_SHORT).show();
                UserId = mAuth.getCurrentUser().getUid();
                insert();

                Intent inext = new Intent(Signup.this, MainActivity.class);
                startActivity(inext);
                finish();

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signup.this, "Error while Signing Up \n Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void validation() {
        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Log.e("hh", "" + charSequence.toString());
                fullname = ed1.getText().toString();
                if (!fullname.isEmpty()) {
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
                email = ed2.getText().toString();
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
                Phone = ed3.getText().toString();
                if (Phone.length() == 10) {
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

    public  void insert(){


        DocumentReference documentReference = db.collection("Users").document(UserId);

        Map<String, Object> map = new HashMap<>();
        map.put("Full Name",fullname);
        map.put("Email Address",email);
        map.put("Phone Number",Phone);
        map.put("Password",password);

        documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Signup.this, "User Record Created Successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }


}