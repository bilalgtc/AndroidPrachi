package com.example.androidtestapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Landing extends AppCompatActivity {

    TextView textView, textfacebook, textgoogle, signin;
    AppCompatButton nextbutton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);


        getWindow().setNavigationBarColor(getResources().getColor(R.color.appdefault));

        textView = findViewById(R.id.welcometxt);
        textfacebook = findViewById(R.id.textfacebook);
        textgoogle = findViewById(R.id.textgoogle);
        signin = findViewById(R.id.signinText);
        nextbutton= findViewById(R.id.nextbutton);

        String text = "Welcome \nto ShopClues";
        SpannableString st = new SpannableString(text);
        ForegroundColorSpan fcsyellow = new ForegroundColorSpan(Color.parseColor("#FFB74A"));
        st.setSpan(fcsyellow, 11,21, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(st);

        String text1 = "Continue with Facebook";
        SpannableString st1 = new SpannableString(text1);
        StyleSpan boldspan = new StyleSpan(Typeface.BOLD);
        st1.setSpan(boldspan, 14, 22,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textfacebook.setText(st1);
        String text2 = "Continue with Google";
        SpannableString st2 = new SpannableString(text2);
        StyleSpan boldspan2 = new StyleSpan(Typeface.BOLD);
        st2.setSpan(boldspan2, 14, 20,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textgoogle.setText(st2);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(Landing.this, Signin.class);
                startActivity(inext);
            }
        });

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext= new Intent(Landing.this, Signup.class);
                startActivity(inext);
            }
        });

    }
}