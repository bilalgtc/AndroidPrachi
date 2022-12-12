package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {

    TextView signuptext;
    ImageView valid, eye;
    EditText et1, et2;
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    AppCompatButton signupbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        et1 = findViewById(R.id.et1signin);
        et2 = findViewById(R.id.et2signin);
        valid = findViewById(R.id.validimg);
        eye = findViewById(R.id.showpassword);
        signuptext = findViewById(R.id.signuptext);
        signupbutton = findViewById(R.id.signinbutton);

        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inext = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(inext);

            }
        });

        et1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  validateemail(et1);
               // signingup();
            }
        });
    }

    private boolean validateemail(EditText et1){
        String emailinput = et1.getText().toString();

        if(!emailinput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailinput).matches()){
            valid.setVisibility(View.VISIBLE);
            return true;
        }else{
            Toast.makeText(getApplicationContext(),"Invalid", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
//    public final static boolean isValidEmail(EditText et1) {
//        String emailinput = et1.getText().toString();
//
//        return !emailinput.isEmpty(et1) && android.util.Patterns.EMAIL_ADDRESS.matcher(et1).matches();
//    }

}



//       String  email = signintxt.getText().toString();
//
//        if(email == emailpattern){
//            valid.setVisibility(View.VISIBLE);
//        }else{
//            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
//        }
//            int temp = 0;
//            if(temp == 0){
//                valid.setVisibility(View.VISIBLE);
//            }

//        et1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if(email.matches(emailpattern)){
//                    valid.setVisibility(View.VISIBLE);
//                }else{
//                    Toast.makeText(getApplicationContext(),"Invalid Input", Toast.LENGTH_SHORT);
//                }
//            }
//        });


//            et1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(et1.equals(emailpattern)){
//                        valid.setVisibility(View.VISIBLE);
//                    }else{
//                        Toast.makeText(getApplicationContext(),"Invalid Input", Toast.LENGTH_SHORT);
//                    }
//                }
//            });






