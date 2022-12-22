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

public class IntroductionPage extends AppCompatActivity {

    ViewPager viewpager;
    DotsIndicator dot;
    ArrayList<Integer> arrayList = new ArrayList<>();
    AppCompatButton nextbutton;
    TextView skip;
    int position = 0;
    String[] text = {"Shop online any \ntime any where", "Get your order\nwithin 24 hours", "Exclusive reward\nand cashback" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introductionpage);

        viewpager = findViewById(R.id.viewpager);
        nextbutton = findViewById(R.id.nextbutton);
        //    startbutton = findViewById(R.id.towelcomescreenbutton);
        dot = findViewById(R.id.dotindicator);
        skip = findViewById(R.id.skipped);
        //  done= findViewById(R.id.done);

        arrayList.add(R.drawable.group1);
        arrayList.add(R.drawable.group2);
        arrayList.add(R.drawable.group3);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(IntroductionPage.this, LandingPage.class);
                startActivity(inext);
            }
        });


        MyAdapter myAdapter
                = new MyAdapter(IntroductionPage.this, arrayList, text);
        viewpager.setAdapter(myAdapter);
        dot.attachTo(viewpager);


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


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                position = viewpager.getCurrentItem();
                if (position < arrayList.size()) {

                    position++;
                    viewpager.setCurrentItem(position);

                }

                if (position == arrayList.size()) {
//                       skip.setText("Done");
//                       nextbutton.setText("Start");

                    Intent inext = new Intent(IntroductionPage.this, LandingPage.class);
                    startActivity(inext);
                }


            }
        });


    }


}