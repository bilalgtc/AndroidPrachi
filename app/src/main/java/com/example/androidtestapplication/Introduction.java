package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.androidtestapplication.Adapter.MyAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class Introduction extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewpager;
    DotsIndicator dot;
    ArrayList<Integer> arrayList = new ArrayList<>();
    AppCompatButton nextbutton;
    TextView skip;
    int position = 0;
    String[] text = {"Shop online any \ntime any where", "Get your order\nwithin 24 hours", "Exclusive reward\nand cashback"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.appdefault));
        oninit();

        skip.setOnClickListener(this);
        nextbutton.setOnClickListener(this);


        MyAdapter myAdapter
                = new MyAdapter(Introduction.this, arrayList, text);
        viewpager.setAdapter(myAdapter);
        dot.attachTo(viewpager);


        arrayList.add(R.drawable.group1);
        arrayList.add(R.drawable.group2);
        arrayList.add(R.drawable.group3);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        position++;
                        break;
                    case 1:
                        position++;
                        break;
                    case 2:
                        position++;
                        nextbutton.setText("Start");
                        skip.setText("Done");

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 3) {
                    nextbutton.setText("Start");
                    skip.setText("Done");
                }
                if (state == 2) {
                    nextbutton.setText("Next");
                    skip.setText("Skip");
                }
                if (state == 1) {
                    nextbutton.setText("Next");
                    skip.setText("Skip");
                }
            }
        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.skipped: {
                Intent inext = new Intent(Introduction.this, Landing.class);
                startActivity(inext);
                finish();
                break;
            }
            case R.id.nextbutton: {
                position = viewpager.getCurrentItem();
                if (position < arrayList.size()) {

                    position++;
                    viewpager.setCurrentItem(position);

                }

                if (position == arrayList.size()) {
                    Intent inext = new Intent(Introduction.this, Landing.class);
                    startActivity(inext);

                    finish();
                }
                break;
            }
        }
    }

    public void oninit() {
        viewpager = findViewById(R.id.viewpager);
        nextbutton = findViewById(R.id.nextbutton);
        dot = findViewById(R.id.dotindicator);
        skip = findViewById(R.id.skipped);
    }
}