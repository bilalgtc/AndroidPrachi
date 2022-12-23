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

public class Signin extends AppCompatActivity {

    TextView signuptext;
    ImageView valid, eye;
    EditText et1, et2;
    DBHelper db;

    AppCompatButton signinbutton;


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



        //Password Visibility //
            eye.setImageResource(R.drawable.eye);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et2.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // If Password is visible then hide //
                    et2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eye.setImageResource(R.drawable.eye);
                }else{
                    et2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye.setImageResource(R.drawable.invisibleeye);
                }
            }
        });
// Password Visibility SetUp//


        // EditText String Validation //

        String name=et2.getText().toString();


        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inext = new Intent(Signin.this, Signup.class);
                startActivity(inext);

            }
        });

et1.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        Log.e("hh",""+charSequence.toString());
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
    if(password.length() < 8){
        Toast.makeText(Signin.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
    }else{

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

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(Signin.this, "Please enter Credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                   boolean check =  db.checkuser(email, password);
                   if(check == true){
                       Intent inext = new Intent(getApplicationContext(), MainActivity.class);
                       startActivity(inext);
                   }
                   else
                   {
                       Toast.makeText(Signin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                   }

                }

            }
        });
// Authenticate //


    }



    // Validation Part //

 //   private boolean validateemail() {
 //       String emailinput = et1.getText().toString();
//        if (!emailinput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailinput).matches()) {
//            valid.setImageResource(R.drawable.success);
//            return true;
//        } else {
//            Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
//            valid.setVisibility(View.INVISIBLE);
//            return false;
//        }
//
//return false;
//    }
//
//    private boolean validatepassword() {
//        int desiredinput = 8;
//        String passwordinput = et2.getText().toString();
//            if (passwordinput.length()>=8){
//               return true;
//            }else
//            {
//                return false;
//            }
//
//    }
}






































































































































































































































//    public final static boolean isValidEmail(EditText et1) {
//        String emailinput = et1.getText().toString();
//
//        return !emailinput.isEmpty(et1) && android.util.Patterns.EMAIL_ADDRESS.matcher(et1).matches();
//    }

//}



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






