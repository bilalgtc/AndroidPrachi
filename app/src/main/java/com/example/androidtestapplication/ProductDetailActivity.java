package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView backbtn;
    AppCompatButton green, black, blue, silver;
    boolean  click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        backbtn = findViewById(R.id.backbtn);
//        green= findViewById(R.id.greenbutton);
//        black= findViewById(R.id.blackbutton);
//        silver= findViewById(R.id.silverbutton);
//        blue= findViewById(R.id.bluebutton);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(ProductDetailActivity.this, MainActivity.class);
                startActivity(inext);
            }
        });

//        green.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if( click = true) {
//                    green.setBackgroundColor(Color.parseColor("#06AB8D"));
//                    green.setTextColor(Color.parseColor("#FFFEFE"));
//                } else{
//                    click = false;
//
//                }
//           }
//        });
//
//        black.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               if( click = true) {
//                   black.setBackgroundColor(Color.parseColor("#06AB8D"));
//                   black.setTextColor(Color.parseColor("#FFFEFE"));
//               } else{
//                   click = false;
//               }
//            }
//        });
//
//        silver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if( click = true) {
//                    silver.setBackgroundColor(Color.parseColor("#06AB8D"));
//                    silver.setTextColor(Color.parseColor("#FFFEFE"));
//                }else{
//                    click = false;
//                }
//            }
//        });
//
//        blue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if( click = true) {
//                    blue.setBackgroundColor(Color.parseColor("#06AB8D"));
//                    blue.setTextColor(Color.parseColor("#FFFEFE"));
//                }else{
//                    click =false;
//                }
//            }
//        });







    }
}