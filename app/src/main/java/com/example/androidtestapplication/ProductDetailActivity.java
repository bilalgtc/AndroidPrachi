package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtestapplication.Adapter.RecyclerModelAdapter;
import com.example.androidtestapplication.Database.CRUD_DATA;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView backbtn, productimage;
    TextView textView1, textView2, textView3, textView4;
    AppCompatButton green, black, blue, silver;
    CRUD_DATA database;
    RecyclerModelAdapter adapter;
    public ArrayList<ModelClass> arrdesign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();
        backbtn.setOnClickListener(this);
        intentextras();

    }

    public void init() {
        productimage = findViewById(R.id.productdetailimage);
        backbtn = findViewById(R.id.backbtn);
        textView1 = findViewById(R.id.productdetailtv1);
        textView2 = findViewById(R.id.productdetailtv2);
        textView3 = findViewById(R.id.productdetailtv3);
        textView4 = findViewById(R.id.productdetailtextview);
        green = findViewById(R.id.greenbutton);
        black = findViewById(R.id.blackbutton);
        silver = findViewById(R.id.silverbutton);
        blue = findViewById(R.id.bluebutton);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backbtn: {
                Intent inext = new Intent(ProductDetailActivity.this, MainActivity.class);
                startActivity(inext);
                finish();
            }
        }
    }

    public void intentextras() {
        Intent i = getIntent();
        String name = i.getStringExtra("Name");
        String company = i.getStringExtra("Company");
        String price = i.getStringExtra("Price");
        String image = i.getStringExtra("Image");
        String COLOR = i.getStringExtra("COLOR");
        String Details = i.getStringExtra("Details");

        textView1.setText(name);
        textView2.setText(price);
        textView3.setText(company);
        textView4.setText(Details);

        productimage.setImageURI(Uri.parse(image));
        if (COLOR.equals("Green")) {

            green.setBackground(getDrawable(R.drawable.buttonclick));
            green.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#06AB8D")));
            green.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {
            green.setBackground(getDrawable(R.drawable.buttonclick));
            green.setTextColor(Color.parseColor("#FF000000"));
        }
        if (COLOR.equals("Black")) {

            black.setBackground(getDrawable(R.drawable.buttonclick));
            black.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#06AB8D")));
            blue.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {

            black.setBackground(getDrawable(R.drawable.buttonclick));
            black.setTextColor(Color.parseColor("#FF000000"));
        }
        if (COLOR.equals("Silver")) {

            silver.setBackground(getDrawable(R.drawable.buttonclick));
            silver.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#06AB8D")));
            silver.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {

            silver.setBackground(getDrawable(R.drawable.buttonclick));
            silver.setTextColor(Color.parseColor("#FF000000"));
        }
        if (COLOR.equals("Blue")) {
            blue.setBackground(getDrawable(R.drawable.buttonclick));
            blue.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#06AB8D")));
            blue.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else {

            blue.setBackground(getDrawable(R.drawable.buttonclick));
            blue.setTextColor(Color.parseColor("#FF000000"));
        }
    }
}