package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4, ed5;
    AppCompatButton signupbutton;

    TextView signintext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ed1 = findViewById(R.id.edittxt1signup);
        ed2 = findViewById(R.id.edittxt2signup);
        ed3 = findViewById(R.id.edittxt3signup);
        ed4 = findViewById(R.id.edittxt4signup);
        ed5 = findViewById(R.id.edittxt5signup);
       signupbutton = findViewById(R.id.signupbutton);
          DataBaseHelper database = new DataBaseHelper(this);

        signintext = findViewById(R.id.signinText);

        signintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inext = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(inext);

            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed1.getText().toString();
                String email = ed2.getText().toString();
                String phonenumber = ed3.getText().toString();
                String password = ed4.getText().toString();
                String confirmpassword = ed5.getText().toString();

                if(name.isEmpty() && email.isEmpty() && phonenumber.isEmpty() && password.isEmpty() && confirmpassword.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Field's are empty", Toast.LENGTH_SHORT).show();
                }else if(password.contentEquals(confirmpassword)){
                        database.registeruser(name, email, phonenumber, password);
                    Toast.makeText(SignupActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}